package org.maskframework.boot.tools.support;

import java.io.OutputStream;

/**
 * A factory for creating MultiOutputStream objects.
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
public interface IMultiOutputStream {

	/**
	 * Builds the output stream.
	 *
	 * @param params the params
	 * @return the output stream
	 */
	OutputStream buildOutputStream(Integer... params);

}
