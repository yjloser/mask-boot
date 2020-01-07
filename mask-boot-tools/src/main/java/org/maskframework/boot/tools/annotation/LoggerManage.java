package org.maskframework.boot.tools.annotation;

import java.lang.annotation.*;


/**
 * <p>
 * 日志拦截操作
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/4/20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {

    /**
     * 服务名
     */
    String value();

    /**
     * 日志登记
     */
    LoggerEnum level() default LoggerEnum.DEBUG;

}
