package org.search.bean.converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.search.bean.converter.config.BeanMappingConfiguration;
import org.search.bean.converter.config.PropertyConfiguration;
import org.search.bean.converter.manager.ConvertManager;

/**
 * @author chad
 * @description 类转换器,支持对象之间的互转,但是可能名字不同,类型不同
 * 
 */
public class ConverterHelper {
	private static final Log log = LogFactory.getLog(ConverterHelper.class);
	private static ConvertManager converterManager;


	public static ConvertManager getConverterManager() {
		return converterManager;
	}


	public static void setConverterManager(ConvertManager converterManager) {
		ConverterHelper.converterManager = converterManager;
	}


	/**
	 * @param <T>
	 * @param source
	 * @param requiredType
	 * @return 将目标对象转换为另外一个对象,支持变量名不同和类型不同,区别于BeanUtils和beanCopies
	 * @throws ConvertException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convert(String id, Object source, Class<T> requiredType)throws ConvertException{
		Object destinationObj = null;
		BeanMappingConfiguration mappingConfiguration = converterManager
				.getConfigHolder().getMappingDefinition(id);
		if (mappingConfiguration == null) {
			throw new ConvertException("can find id");
		}

		String destName = mappingConfiguration.getDestinationClass();
        try{        	
        	Class<?> destClazz = ConverterHelper.class.getClassLoader().loadClass(
        			destName);
        	
        	destinationObj = destClazz.newInstance();
        	BeanUtils.copyProperties(destinationObj, source);
        	// 如果没有使用配置,默认属性完全相同
        	if (CollectionUtils.isNotEmpty(mappingConfiguration.getPropList())) {
        		for (PropertyConfiguration configuration : mappingConfiguration
        				.getPropList()) {
        			String sourceFiledName = configuration.getSourceFieldName();
        			String destFiledName = configuration.getDestinationFieldName();
        			Field destField = destClazz.getDeclaredField(destFiledName);
        			destField.setAccessible(true);
        			Object sourceObjValue = BeanUtils.getProperty(source,
        					sourceFiledName);
        			if (configuration.getProcessor() == null) {// 对象相同
        				destField.set(destinationObj, sourceObjValue);
        			} else {// 对象不同
        				destField.set(destinationObj, configuration.getProcessor()
        						.process(sourceObjValue));
        			}
        			
        		}
        	}
        }catch(Exception e){
        	log.error("convert ocurr exception",e);
        	throw new ConvertException(e.getMessage());
        }


		return (T) destinationObj;
	}
}
