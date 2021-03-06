package org.maskframework.boot.tools.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

import static org.maskframework.boot.tools.constant.ToolsConstant.*;


/**
 * <p>
 * Http请求工具类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
@Slf4j
public class OkHttpUtil {

    public static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static MediaType XML = MediaType.parse("application/xml; charset=utf-8");

    /**
     * GET
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return String
     */
    public static String get(String url, Map<String, String> queries) {
        return get(url, null, queries);
    }

    /**
     * GET
     *
     * @param url     请求的url
     * @param header  请求头
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return String
     */
    public static String get(String url, Map<String, String> header, Map<String, String> queries) {
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            sb.append("?clientId=blade");
            queries.forEach((k, v) -> sb.append("&").append(k).append("=").append(v));
        }

        Request.Builder builder = new Request.Builder();

        if (header != null && header.keySet().size() > 0) {
            header.forEach(builder::addHeader);
        }

        Request request = builder.url(sb.toString()).build();
        return getBody(request);
    }

    /**
     * POST
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return String
     */
    public static String post(String url, Map<String, String> params) {
        return post(url, null, params);
    }

    /**
     * POST
     *
     * @param url    请求的url
     * @param header 请求头
     * @param params post form 提交的参数
     * @return String
     */
    public static String post(String url, Map<String, String> header, Map<String, String> params) {
        FormBody.Builder formBuilder = new FormBody.Builder().add("clientId", "blade");
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            params.forEach(formBuilder::add);
        }

        Request.Builder builder = new Request.Builder();

        if (header != null && header.keySet().size() > 0) {
            header.forEach(builder::addHeader);
        }

        Request request = builder.url(url).post(formBuilder.build()).build();
        return getBody(request);
    }

    /**
     * POST请求发送JSON数据
     *
     * @param url  请求的url
     * @param json 请求的json串
     * @return String
     */
    public static String postJson(String url, String json) {
        return postJson(url, null, json);
    }

    /**
     * POST请求发送JSON数据
     *
     * @param url    请求的url
     * @param header 请求头
     * @param json   请求的json串
     * @return String
     */
    public static String postJson(String url, Map<String, String> header, String json) {
        return postContent(url, header, json, JSON);
    }

    /**
     * POST请求发送xml数据
     *
     * @param url 请求的url
     * @param xml 请求的xml串
     * @return String
     */
    public static String postXml(String url, String xml) {
        return postXml(url, null, xml);
    }

    /**
     * POST请求发送xml数据
     *
     * @param url    请求的url
     * @param header 请求头
     * @param xml    请求的xml串
     * @return String
     */
    public static String postXml(String url, Map<String, String> header, String xml) {
        return postContent(url, header, xml, XML);
    }

    /**
     * 发送POST请求
     *
     * @param url       请求的url
     * @param header    请求头
     * @param content   请求内容
     * @param mediaType 请求类型
     * @return String
     */
    public static String postContent(String url, Map<String, String> header, String content, MediaType mediaType) {
        RequestBody requestBody = RequestBody.create(mediaType, content);
        Request.Builder builder = new Request.Builder();

        if (header != null && header.keySet().size() > 0) {
            header.forEach(builder::addHeader);
        }
        Request request = builder.url(url).post(requestBody).build();
        return getBody(request);
    }

    /**
     * 获取body
     *
     * @param request request
     * @return String
     */
    private static String getBody(Request request) {
        String responseBody = "";
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return Objects.requireNonNull(response.body()).string();
            }
        } catch (Exception e) {
            log.error("okhttp3 post error >> ex = {}", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * 解析get请求
     *
     * @param queryString 请求参数
     * @return 返回json
     * @author Mr.Yang
     * @date 2020/1/6 10:53
     */
    public static JSONObject parseQueryString(String queryString) {
        if (StringUtil.isEmptyEnhance(queryString)) {
            return null;
        }
        int index = queryString.indexOf("?");
        if (index > NUM_ZERO) {
            queryString = queryString.substring(index + NUM_ONE);
        }
        JSONObject argJson = new JSONObject();
        String[] queryArr = queryString.split("&");
        for (String string : queryArr) {
            String[] keyAndValue = string.split("=", NUM_TWO);
            if (keyAndValue.length != NUM_TWO) {
                argJson.put(keyAndValue[NUM_ZERO], null);
            } else {
                argJson.put(keyAndValue[NUM_ZERO], keyAndValue[NUM_ONE]);
            }
        }
        return argJson;
    }

    /**
     * 解析post请求
     *
     * @param request 请求信息
     * @return 返回json
     * @author Mr.Yang
     * @date 2020/1/6 11:06
     */
    public static JSONObject parseBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try (InputStream inputStream = request.getInputStream()) {
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > NUM_ZERO) {
                    stringBuilder.append(charBuffer, NUM_ZERO, bytesRead);
                }
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.getStackTrace();
                }
            }
        }
        return JSONObject.parseObject(stringBuilder.toString());
    }
}
