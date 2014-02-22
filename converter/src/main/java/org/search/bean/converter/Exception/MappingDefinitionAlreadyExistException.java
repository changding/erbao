package org.search.bean.converter.exception;

/**
 * @author chad
 * @description �����ӳ���Ѿ�����exception
 *
 */
public class MappingDefinitionAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public MappingDefinitionAlreadyExistException(String msg){
		super(msg);
	}
	public MappingDefinitionAlreadyExistException(Exception e,String msg){
		super(msg, e);
	}
	public MappingDefinitionAlreadyExistException(Exception e){
		super(e);
	}
}
