package org.search.bean.converter.Exception;

public class ParserException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public ParserException(String msg){
		super(msg);
	}
	public ParserException(Exception e,String msg){
		super(msg, e);
	}
	public ParserException(Exception e){
		super(e);
	}
}
