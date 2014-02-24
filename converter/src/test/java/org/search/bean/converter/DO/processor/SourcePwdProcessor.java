package org.search.bean.converter.DO.processor;

import org.search.bean.converter.DO.Pwd;
import org.search.bean.converter.config.process.Processor;
import org.search.bean.converter.exception.ConvertException;

public class SourcePwdProcessor implements Processor{

	@SuppressWarnings("unchecked")
	@Override
	public Pwd process(Object destination)throws ConvertException {
		Pwd pwd= null;
		if(destination instanceof String){
			pwd = new Pwd(String.valueOf(destination));
		}
		return pwd;
	}

}
