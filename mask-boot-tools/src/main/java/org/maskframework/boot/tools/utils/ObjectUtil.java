package org.maskframework.boot.tools.utils;

import org.springframework.lang.Nullable;


/**
 * <p>
 *对象工具类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
public class ObjectUtil extends org.springframework.util.ObjectUtils {

	/**
	 * 判断元素不为空
	 * @param obj object
	 * @return boolean
	 */
	public static boolean isNotEmpty(@Nullable Object obj) {
		return !ObjectUtil.isEmpty(obj);
	}

}
