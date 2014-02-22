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
 * @description ��ת����,֧�ֶ���֮��Ļ�ת,���ǿ������ֲ�ͬ,���Ͳ�ͬ
 *
 */
public class ConverterHelper {
	private static final Log log = LogFactory.getLog(ConverterHelper.class);
	private static ConvertManager converterManager = new ConvertManager();
	static{
		converterManager.setInitConfigLocations(new String[]{""});
		converterManager.init();
	}
	/**
	 * @param <T>
	 * @param source
	 * @param requiredType
	 * @return ��Ŀ�����ת��Ϊ����һ������,֧�ֱ�������ͬ�����Ͳ�ͬ,������BeanUtils��beanCopies
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
	static <T> T  convert(String id,Object source, Class<T> requiredType) throws ConvertException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException, IllegalArgumentException, NoSuchMethodException{
		Object destinationObj = null;
		BeanMappingConfiguration  mappingConfiguration = converterManager.getConfigHolder().getMappingDefinition(id);
		if(mappingConfiguration == null){
			throw new ConvertException("can find id"); 
		}
		
		String sourceName = mappingConfiguration.getSourceClass();
		String destName = mappingConfiguration.getDestinationClass();
		
		Class<?> sourceClazz = ConverterHelper.class.getClassLoader().loadClass(sourceName);
		Class<?> destClazz  = ConverterHelper.class.getClassLoader().loadClass(destName);
		
		destinationObj = destClazz.newInstance();
		//���û��ʹ������,Ĭ��������ȫ��ͬ
		if(CollectionUtils.isEmpty(mappingConfiguration.getPropList())){
			BeanUtils.copyProperties(destinationObj, source);
		}else{//�������
			
			for(PropertyConfiguration configuration:mappingConfiguration.getPropList()){
				String sourceFiledName = configuration.getSourceFieldName();
				String destFiledName = configuration.getDestinationFieldName();
				Field destField = destClazz.getField(destFiledName);
				destField.setAccessible(true);
				Object sourceObjValue = BeanUtils.getProperty(source,sourceFiledName);
				if(configuration.getProcessor() == null){//������ͬ
					destField.set(destinationObj,sourceObjValue);
				}else{//����ͬ
					destField.set(destinationObj,configuration.getProcessor().process(sourceObjValue));
				}
			}
		}
		
		
		return (T)destinationObj;
	}
}
