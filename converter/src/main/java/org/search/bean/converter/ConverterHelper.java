package org.search.bean.converter;

/**
 * @author chad
 * @description 类转换器,支持对象之间的互转,但是可能名字不同,类型不同
 *
 */
public class ConverterHelper {
	/**
	 * @param <T>
	 * @param source
	 * @param requiredType
	 * @return 将目标对象转换为另外一个对象,支持变量名不同和类型不同,区别于BeanUtils和beanCopies
	 */
	@SuppressWarnings("unchecked")
	public static <T> T  convert(Object source, Class<T> requiredType,String id){
		Object destinationObj = null;
		return (T)destinationObj;
	}
}
