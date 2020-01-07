package org.maskframework.boot.tools.init;

import com.alibaba.fastjson.JSONArray;
import org.maskframework.boot.tools.annotation.AclManage;
import org.maskframework.boot.tools.api.AclVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 检索配置
 * </p>
 *
 * @author Mr.Yang
 * @since 2020/1/7
 */
@Configuration
public class GeneralInjection {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(GeneralInjection.class);

    /**
     * 获取所有url链接
     */
    public static Map<String, AclVo> listUrl = new HashMap<>();

    /**
     * 容器上下文
     */
    @Autowired
    WebApplicationContext applicationContext;

    /**
     * 环境
     */
    @Autowired
    Environment environment;

    /**
     * 初始化检索所有requestMapping
     *
     * @author Mr.Yang
     * @date 2018/12/7 0007
     */
    @PostConstruct
    @Order(-1)
    public void retrievalRequestMapping() throws InterruptedException {
        //获取使用RequestMapping注解方法
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            boolean flag = true;
            AclVo aclVo = new AclVo();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            // 方法名
            String methodName = method.getMethod().getName();
            //过滤不展示处理的方法
            String[] excludeUlr = {"tableToEntity", "error"};
            for (String url : excludeUlr) {
                //如果开头以过滤的，则默认不展示
                if (methodName.startsWith(url)) {
                    flag = false;
                    break;
                }
            }
            //不是过滤数组则可以加入展示
            if (flag) {
                PatternsRequestCondition p = info.getPatternsCondition();
                aclVo.setMethod(methodName);
                String url = JSONArray.toJSONString(p.getPatterns());
                // 一个方法可能对应多个url
                aclVo.setUrl(url.substring(2, url.length() - 2));
                // 类名
                aclVo.setClassName(method.getMethod().getDeclaringClass().getName());
                //反射获取自定义注解
                AclManage loginManage = method.getMethodAnnotation(AclManage.class);
                //获取名称
                if (loginManage != null) {
                    aclVo.setName(loginManage.value());
                }
                RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
                for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                    aclVo.setType(requestMethod.toString());
                }
                listUrl.put(aclVo.getUrl(), aclVo);
            }
        }
    }
}
