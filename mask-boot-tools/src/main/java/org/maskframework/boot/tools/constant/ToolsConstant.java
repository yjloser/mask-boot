package org.maskframework.boot.tools.constant;


/**
 * <p>
 * 系统常量
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
public interface ToolsConstant {

    /**
     * 数据
     */
    String FIELD_DATA = "data";

    /**
     * 当前页
     */
    String FIELD_CURRENT = "current";


    /**
     * 当前页面
     */
    String FIELD_CURRENTPAGE = "current";

    /**
     * 本地回环
     */
    String LOCALHOST = "0:0:0:0:0:0:0:1";

    /**
     * 0
     */
    int NUM_ZERO = 0;

    /**
     * 1
     */
    int NUM_ONE = 1;

    /**
     * 2
     */
    int NUM_TWO = 2;

    /**
     * 3
     */
    int NUM_THREE = 3;

    /**
     * 4
     */
    int NUM_FOUR = 4;

    /**
     * 5
     */
    int NUM_FIVE = 5;
    /**
     * 6
     */
    int NUM_SIX = 6;

    /**
     * 7
     */
    int NUM_SEVEN = 7;

    /**
     * 8
     */
    int NUM_EIGHT = 8;

    /**
     * 9
     */
    int NUM_NINE = 9;

    /**
     * 10
     */
    int NUM_TEN = 10;

    /**
     * 30
     */
    int NUM_THIRTH = 30;

    /**
     * 200
     */
    int TWO_HUNDRED = 200;

    /**
     * 系统
     */
    String OS_NAME = "os.name";

    /**
     * WINDOWS
     */
    String WINDOWS = "windows";

    /**
     * file文件
     */
    String FIELD_FILE = "file";

    /**
     * get方法
     */
    String FIELD_GET_METHOD = "get";

    /**
     * 返回码
     */
    String FIELD_CODE = "code";
    /**
     * 返回信息
     */
    String FIELD_MSG = "msg";

    /**
     * id
     */
    String FIELD_ID = "id";

    /**
     * 详情
     */
    String FIELD_GET = "get";

    /**
     * 新增
     */
    String FIELD_INSERT = "insert";

    /**
     * 更新
     */
    String FIELD_UPDATE = "update";

    /**
     * 删除
     */
    String FIELD_DELETE = "delete";

    /**
     * 列表
     */
    String FIELD_LIST = "list";

    /**
     * 校验状态
     */
    String FIELD_CHECK_STATUS = "check_status";

    /**
     * 数据库类型
     */
    String FIELD_ORACLE = "oracle";

    /**
     * 未命名
     */
    String UNKNOWN = "unknown";

    /**
     * 编码
     */
    String UTF_8 = "UTF-8";

    /**
     * contentType
     */
    String CONTENT_TYPE_NAME = "Content-type";

    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json;charset=utf-8";

    /**
     * 角色前缀
     */
    String SECURITY_ROLE_PREFIX = "ROLE_";

    /**
     * 主键字段名
     */
    String DB_PRIMARY_KEY = "id";

    /**
     * 业务状态[1:正常]
     */
    int DB_STATUS_NORMAL = 1;


    /**
     * 删除状态[0:正常,1:删除]
     */
    int DB_NOT_DELETED = 0;
    int DB_IS_DELETED = 1;

    /**
     * 用户锁定状态
     */
    int DB_ADMIN_NON_LOCKED = 0;
    int DB_ADMIN_LOCKED = 1;

    /**
     * 管理员对应的租户ID
     */
    String ADMIN_TENANT_ID = "000000";

    /**
     * 日志默认状态
     */
    String LOG_NORMAL_TYPE = "1";

    /**
     * 默认为空消息
     */
    String DEFAULT_NULL_MESSAGE = "暂无承载数据";
    /**
     * 默认成功消息
     */
    String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    /**
     * 默认失败消息
     */
    String DEFAULT_FAILURE_MESSAGE = "操作失败";
    /**
     * 默认未授权消息
     */
    String DEFAULT_UNAUTHORIZED_MESSAGE = "签名认证失败";


    /**
     * 数据库类型
     */
    String FIELD_DATABASE_TYPE = "databaseType";


    /**
     * 类型
     */
    String FIELD_TYPE = "type";

    /**
     * 备注
     */
    String FIELD_REMARK = "remark";

    /**
     * 实体
     */
    String FIELD_ITEM = "item";

    /**
     * 集合
     */
    String FIELD_ITEMS = "items";

    /**
     * 开始页面
     **/
    String FIELD_BIGENPAGE = "bigen";

    /**
     * 停止页面
     **/
    String FIELD_ENDPAGE = "end";
    /**
     * 每页条数
     */
    String FIELD_SIZE = "size";
}
