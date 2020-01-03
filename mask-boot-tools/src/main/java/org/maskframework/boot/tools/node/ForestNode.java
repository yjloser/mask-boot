package org.maskframework.boot.tools.node;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 *森林节点类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ForestNode extends BaseNode {

	/**
	 * 节点内容
	 */
	private Object content;

	public ForestNode(Integer id, Integer parentId, Object content) {
		this.id = id;
		this.parentId = parentId;
		this.content = content;
	}

}
