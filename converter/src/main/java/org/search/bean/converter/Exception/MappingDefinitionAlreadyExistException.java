package org.search.bean.converter.exception;

/**
 * @author chad
 * @description 定义的映射已经存在exception
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
