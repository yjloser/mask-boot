package org.maskframework.boot.tools.utils;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import static org.maskframework.boot.tools.constant.ToolsConstant.NUM_ONE;
import static org.maskframework.boot.tools.constant.ToolsConstant.NUM_ZERO;
import static org.maskframework.boot.tools.utils.FileUtil.getFilePath;
import static org.maskframework.boot.tools.utils.FileUtil.readAsStringBuilder;


/**
 * <p>
 * 解析请求工具类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/4/20
 */
@Component
@Data
public class AnalyzeUtil {

    private static Logger logger = LoggerFactory.getLogger(AnalyzeUtil.class);

    /**
     * 邮件信息
     */
    public static String emailContent;

    /**
     * 返回码缓存在内存中
     */
    public static Map<String, String> codeMap = new ConcurrentHashMap<>();

    /**
     * 检索信息
     */
    public static Map<Object, Object> searchMap = new ConcurrentHashMap<>();

    /**
     * 默认加载的properties文件
     */
    private static final String[] DEFAULT_PROPERTIES_URL = {"config/responseMessage.properties", "config/email.txt"};

    static {
        loadFile(DEFAULT_PROPERTIES_URL[0]);
        loadHtml(DEFAULT_PROPERTIES_URL[1]);
    }

    /**
     * 返回响应信息
     *
     * @param code 响应码
     * @return 返回响应信息
     * @author Mr.Yang
     * @date 2019/2/27 0027
     */
    public static String getCodeValue(String code) {
        if (codeMap.size() == 0) {
            loadFile(DEFAULT_PROPERTIES_URL[0]);
            loadHtml(DEFAULT_PROPERTIES_URL[1]);
        }
        return StringUtils.isEmpty(codeMap.get(code)) ? "" : codeMap.get(code);
    }

    /**
     * 定时执行返回码
     *
     * @author Mr.Yang
     * @date 2018/12/2
     */
    @Scheduled(fixedRate = 30000)
    public void scheduledCode() {
        //文件是否存在
        if (new File(getFilePath() + DEFAULT_PROPERTIES_URL[NUM_ZERO]).exists()) {
            //加载文件
            loadFile(DEFAULT_PROPERTIES_URL[NUM_ZERO]);
        }
        //文件是否存在
        if (new File(getFilePath() + DEFAULT_PROPERTIES_URL[NUM_ONE]).exists()) {
            //加载文件
            loadHtml(DEFAULT_PROPERTIES_URL[NUM_ONE]);
        }
    }

    /**
     * 加载配置文件信息
     *
     * @param fileUrl 文件路径
     * @author Mr.Yang
     * @date 2018/12/2
     */
    private static void loadFile(String fileUrl) {
        //文件全路径
        if (new File(getFilePath() + fileUrl).exists()) {
            try {
                //从指定路径加载信息
                Properties properties =
                        PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource(fileUrl), StandardCharsets.UTF_8));
                //清空
                codeMap.clear();
                //将返返回码放入map中
                for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                    codeMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
                }
            } catch (Exception e) {
                logger.error("Exception when load" + fileUrl, e);
            }
        }
    }

    /**
     * 加载邮箱内容信息
     *
     * @param fileUrl 文件路径
     * @author Mr.Yang
     * @date 2018/12/2
     */
    private static void loadHtml(String fileUrl) {
        //文件全路径
        if (new File(getFilePath() + fileUrl).exists()) {
            try {
                emailContent = String.valueOf(readAsStringBuilder(getFilePath() + fileUrl));
            } catch (Exception e) {
                logger.error("Exception when load" + fileUrl, e);
            }
        }
    }
}
