package org.search.bean.converter.config.process;

import org.search.bean.converter.Exception.ConvertException;

/**
 * @author chad
 * @description �����ж�����ֶ�ת������Ҫ��ĳ������
 *
 */
public interface Processor {
public <T> T process(Object destination)throws ConvertException;
}
