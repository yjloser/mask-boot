package org.maskframework.boot.tools.api.request;

import com.alibaba.fastjson.JSONObject;
import org.maskframework.boot.tools.api.AbstractRequest;
import org.maskframework.boot.tools.api.response.DesktopEndResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import static org.maskframework.boot.tools.constant.ToolsConstant.FIELD_CHECK_STATUS;
import static org.maskframework.boot.tools.constant.ToolsConstant.FIELD_GET_METHOD;
import static org.maskframework.boot.tools.utils.OkHttpUtil.parseBody;
import static org.maskframework.boot.tools.utils.OkHttpUtil.parseQueryString;
import static org.maskframework.boot.tools.utils.StringUtil.isEmpty;


/**
 * <p>
 * 桌面端请求
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/4/20
 */
public class DesktopEndRequest extends AbstractRequest<DesktopEndResponse> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(DesktopEndRequest.class);

    /**
     * 从request中获取请求信息
     *
     * @param request 解析信息
     * @return 返回处理的信息
     * @author Mr.Yang
     * @date 2018/12/2
     */
    public static JSONObject requestMessage(HttpServletRequest request) {
        return requestMessage(request, null);
    }


    /**
     * 从request中获取请求信息
     *
     * @param request 解析信息
     * @param fields  必填字段
     * @return 返回处理的信息
     * @author Mr.Yang
     * @date 2018/12/2
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
        //是否为空
        try {
            if (!params.isEmpty() && fields != null) {
                // 校验请求参数
                for (String key : fields) {
                    // 判断是否存在该字段、字段是否为空
                    if (!params.containsKey(key) || isEmpty(params.getString(key))) {
                        params.put(FIELD_CHECK_STATUS, false);
                        return params;
                    }
                }
            }
            //处理分页页面
            checkPage(params);
            return params;
        } catch (Exception e) {
            logger.error("请求信息转换为JSON格式报错", e);
            e.printStackTrace();
            return null;
        }
    }
}
