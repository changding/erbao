package org.search.bean.converter.config.process;

import org.search.bean.converter.ConvertException;

/**
 * @author chad
 * @description �����ж�����ֶ�ת������Ҫ��ĳ������
 *
 */
public interface Processor {
public Object process(Object destination)throws ConvertException;
}
