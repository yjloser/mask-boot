package org.maskframework.boot.tools.api.request;

import com.alibaba.fastjson.JSONObject;
import org.maskframework.boot.tools.api.AbstractRequest;
import org.maskframework.boot.tools.api.response.MobileEndResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static org.maskframework.boot.tools.constant.ToolsConstant.FIELD_CHECK_STATUS;
import static org.maskframework.boot.tools.constant.ToolsConstant.FIELD_GET_METHOD;
import static org.maskframework.boot.tools.utils.Func.isEmpty;
import static org.maskframework.boot.tools.utils.OkHttpUtil.parseBody;
import static org.maskframework.boot.tools.utils.OkHttpUtil.parseQueryString;


/**
 * <p>
 * 移动端请求
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/4/20
 */
public class MobileEndRequest extends AbstractRequest<MobileEndResponse> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(MobileEndRequest.class);

    /**
     * 解析请求数据
     *
     * @param request 请求类型
     * @return 返回解析得到的字符串
     * @author Mr.Yang
     * @date 2018/7/7
     */
    public static JSONObject requestMessage(HttpServletRequest request) {
        return requestMessage(request, null);
    }


    /**
     * 解析请求数据
     *
     * @param request 请求类型
     * @param fields  字符串数组-->校验请求参数
     * @return 返回解析得到的字符串
     * @author Mr.Yang
     * @date 2018/7/7
     */
    public static JSONObject requestMessage(HttpServletRequest request, String... fields) {
        //获取请求方式
        String method = request.getMethod();
        JSONObject params;
        //get方法
        if (FIELD_GET_METHOD.equals(method)) {
            params = parseQueryString(request.getQueryString());
        } else {
            params = parseBody(request);
        }
        if (fields != null) {
            // 校验请求参数
            for (String key : fields) {
                // 判断是否存在该字段、字段是否为空
                if (!params.containsKey(key) || isEmpty(params.getString(key))) {
                    // 请求参数缺失
                    params.put(FIELD_CHECK_STATUS, false);
                    return params;
                }
            }
        }
        // 分页操作
        checkPage(params);
        logger.debug("{}|{}|{}|{}", 10000, getIpAddr(request), request.getRequestURI(), params);
        return params;
    }
}
