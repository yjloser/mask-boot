package org.maskframework.boot.tools.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 反射注解
 * </p>
 *
 * @author Mr.Yang
 * @since 2020/1/7
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReflectManage {
}
