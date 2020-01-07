package org.maskframework.boot.tools.api;

import lombok.Data;

/**
 * <p>
 * acl权限
 * </p>
 *
 * @author Mr.Yang
 * @since 2020/1/7
 */
@Data
public class AclVo {

    /**
     * 路径
     */
    private String url;
    /**
     * 模块名称
     */
    private String name;
    /**
     * 全限定路径
     */
    private String className;
    /**
     * 方法
     */
    private String method;
    /**
     * 类型
     */
    private String type;
}
