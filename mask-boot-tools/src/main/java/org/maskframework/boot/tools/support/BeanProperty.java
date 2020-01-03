package org.maskframework.boot.tools.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Bean属性
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
@Getter
@AllArgsConstructor
public class BeanProperty {
    private final String name;
    private final Class<?> type;
}
