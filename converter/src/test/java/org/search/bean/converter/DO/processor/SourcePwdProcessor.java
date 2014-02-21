package org.search.bean.converter.DO.processor;

import org.search.bean.converter.ConvertException;
import org.search.bean.converter.DO.Pwd;
import org.search.bean.converter.config.process.Processor;

public class SourcePwdProcessor implements Processor{

	@Override
	public Object process(Object destination)throws ConvertException {
		Pwd pwd= null;
		if(destination instanceof String){
			pwd = new Pwd(String.valueOf(destination));
		}
		return pwd;
	}

}
