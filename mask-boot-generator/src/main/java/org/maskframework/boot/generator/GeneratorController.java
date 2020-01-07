package org.maskframework.boot.generator;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.maskframework.boot.generator.ConvertHandler.convertTableToEntity;
import static org.maskframework.boot.generator.GeneratorConstants.FIELD_GENERATOR;
import static org.maskframework.boot.generator.GeneratorConstants.TABLE_TO_ENTITY;
import static org.maskframework.boot.generator.GeneratorEnum.CONVERT_ERROR;
import static org.maskframework.boot.generator.GeneratorEnum.PROCESS_SUCCESS;

/**
 * <p>
 * 生成控制器
 * </p>
 *
 * @author Mr.Yang
 * @since 2020/1/3
 */
@RestController
@RequestMapping(FIELD_GENERATOR)
public class GeneratorController {

    /**
     * 从数据库表转换为实体等（service、entity、controller、mapper）
     *
     * @param convert 请求的参数 json格式
     * @return 返回是否创建成功
     * @author Mr.Yang
     * @date 2018/11/29 0029
     */
    @RequestMapping(TABLE_TO_ENTITY)
    public String tableToEntity(@RequestParam(required = false) String convert) {
        //如果传入的字段不为空，则表示不是通过接口传值
        if (!StringUtils.isEmpty(convert)) {
            //处理自动转换工具
            return convertTableToEntity(convert) ? PROCESS_SUCCESS.getKey()
                    : CONVERT_ERROR.getKey();
        }
        return null;
    }
}
