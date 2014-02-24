package org.search.bean.converter.DO.processor;

import org.search.bean.converter.DO.Name;
import org.search.bean.converter.Exception.ConvertException;
import org.search.bean.converter.config.process.Processor;

public class SourceNameProcessor implements Processor{

	@Override
	public Object process(Object destination) throws ConvertException{
		Name name = null;
		if(destination instanceof String){
			 name = new Name(String.valueOf(destination));
		}
		return name;
	}

}
