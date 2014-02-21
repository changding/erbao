package org.search.bean.converter.config.process;

import org.search.bean.converter.ConvertException;

/**
 * @author chad
 * @description 将已有对象的字段转换成需要的某个对象
 *
 */
public interface Processor {
public Object process(Object destination)throws ConvertException;
}
