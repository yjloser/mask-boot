package org.maskframework.boot.tools.node;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 树型节点类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TreeNode extends BaseNode {

	private String title;

	private Integer key;

	private Integer value;

}
