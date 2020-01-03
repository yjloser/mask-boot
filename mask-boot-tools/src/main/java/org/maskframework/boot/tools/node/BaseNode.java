package org.maskframework.boot.tools.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *节点基类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
@Data
public class BaseNode implements INode {

	/**
	 * 主键ID
	 */
	protected Integer id;

	/**
	 * 父节点ID
	 */
	protected Integer parentId;

	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected List<INode> children = new ArrayList<>();

}
