package org.search.bean.converter.DO.processor;

import org.search.bean.converter.DO.Name;
import org.search.bean.converter.config.process.Processor;
import org.search.bean.converter.exception.ConvertException;

public class SourceNameProcessor implements Processor{
	@SuppressWarnings("unchecked")
	@Override
	public Name process(Object destination) throws ConvertException{
		Name name = null;
		if(destination instanceof String){
			 name = new Name(String.valueOf(destination));
		}
		return name;
	}

}
