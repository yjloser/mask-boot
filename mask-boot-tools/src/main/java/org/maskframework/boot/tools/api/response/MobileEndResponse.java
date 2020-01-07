package org.maskframework.boot.tools.api.response;

import com.alibaba.fastjson.JSONObject;
import org.maskframework.boot.tools.api.AbstractResponse;
import org.maskframework.boot.tools.api.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 * 移动端响应
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/4/20
 */
public class MobileEndResponse extends AbstractResponse {

    private static final long serialVersionUID = 6635153930520851477L;

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(MobileEndResponse.class);

    /**
     * 默认返回成功
     *
     * @return JSONObject json类型
     * @author Mr.Yang
     * @date 2018/12/2
     */
    public static JSONObject responseMessage() {
        return JSONObject.parseObject(JSONObject.toJSONString(new R<>()));
    }

    /**
     * 默认返回成功
     *
     * @param response 响应对象
     * @return JSONObject json类型
     * @author Mr.Yang
     * @date 2018/12/2
     */
    public static void responseMessage(HttpServletResponse response) {
        responseMessage(new R<>(), response);
    }

    /**
     * 返回平台全局码
     *
     * @param r        返回对象
     * @param response 响应对象
     * @return JSONObject json类型
     * @author Mr.Yang
     * @date 2018/12/2
     */
    public static void responseMessage(R<Object> r, HttpServletResponse response) {
        //响应前台
        write(r, response);
    }

    public static void main(String[] args) {
        System.out.println(responseMessage());
    }
}
