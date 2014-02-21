package org.search.bean.converter.config;

import java.util.List;


import org.search.bean.converter.config.process.Processor;
import org.search.bean.converter.config.process.Validator;

/**
 * @author chad
 * @description 转换属性的对应关系
 *
 */
public class PropertyConfiguration implements Validator{
	private String sourceFieldName;
	private String destinationFieldName;
	private Processor processor;
	public String getSourceFieldName() {
		return sourceFieldName;
	}
	public void setSourceFieldName(String sourceFieldName) {
		this.sourceFieldName = sourceFieldName;
	}
	public String getDestinationFieldName() {
		return destinationFieldName;
	}
	public void setDestinationFieldName(String destinationFieldName) {
		this.destinationFieldName = destinationFieldName;
	}

	public Processor getProcessor() {
		return processor;
	}
	public void setProcessor(Processor processor) {
		this.processor = processor;
	}
	@Override
	public boolean isValidate() {
		return false;
	} 

}
