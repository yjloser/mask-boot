package org.maskframework.boot.tools.node;

import java.util.List;


/**
 * <p>
 * 节点接口
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
public interface INode {

    /**
     * 主键
     *
     * @return Integer
     */
    Integer getId();

    /**
     * 父主键
     *
     * @return Integer
     */
    Integer getParentId();

    /**
     * 子孙节点
     *
     * @return List
     */
    List<INode> getChildren();

}
