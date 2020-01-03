package org.maskframework.boot.generator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.maskframework.boot.generator.GeneratorConstants.CONFIG_INDEX;

/**
 * <p>
 * 生成控制器
 * </p>
 *
 * @author Mr.Yang
 * @since 2020/1/3
 */
@RestController
@RequestMapping(CONFIG_INDEX)
public class GeneratorController {
}
