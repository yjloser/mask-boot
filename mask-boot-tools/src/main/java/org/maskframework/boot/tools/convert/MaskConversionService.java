package org.maskframework.boot.tools.convert;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.StringValueResolver;


/**
 * <p>
 *类型 转换 服务，添加了 IEnum 转换
 * </p>
 *
 * @author Mr.Yang
 * @since 2019/1/1
 */
public class MaskConversionService extends ApplicationConversionService {
	@Nullable
	private static volatile MaskConversionService SHARED_INSTANCE;

	public MaskConversionService() {
		this(null);
	}

	public MaskConversionService(@Nullable StringValueResolver embeddedValueResolver) {
		super(embeddedValueResolver);
		super.addConverter(new EnumToStringConverter());
		super.addConverter(new StringToEnumConverter());
	}

	/**
	 * Return a shared default application {@code ConversionService} instance, lazily
	 * building it once needed.
	 * <p>
	 * Note: This method actually returns an {@link MaskConversionService}
	 * instance. However, the {@code ConversionService} signature has been preserved for
	 * binary compatibility.
	 * @return the shared {@code BladeConversionService} instance (never{@code null})
	 */
	public static GenericConversionService getInstance() {
		MaskConversionService sharedInstance = MaskConversionService.SHARED_INSTANCE;
		if (sharedInstance == null) {
			synchronized (MaskConversionService.class) {
				sharedInstance = MaskConversionService.SHARED_INSTANCE;
				if (sharedInstance == null) {
					sharedInstance = new MaskConversionService();
					MaskConversionService.SHARED_INSTANCE = sharedInstance;
				}
			}
		}
		return sharedInstance;
	}

}
