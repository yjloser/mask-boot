package org.maskframework.boot.tools.api;

import java.io.Serializable;

/**
 * <p>
 *业务代码接口
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
public interface IResultCode extends Serializable {

	/**
	 * 消息
	 *
	 * @return String
	 */
	String getMessage();

	/**
	 * 状态码
	 *
	 * @return int
	 */
	int getCode();

}
