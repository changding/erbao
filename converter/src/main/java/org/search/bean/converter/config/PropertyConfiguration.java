package org.search.bean.converter.config;

import org.apache.commons.lang3.StringUtils;
import org.search.bean.converter.config.process.Processor;
import org.search.bean.converter.config.process.Validable;

/**
 * @author chad
 * @description 转换属性的对应关系
 *
 */
public class PropertyConfiguration implements Validable{
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
	public String isValidate() {
	if(StringUtils.isBlank(sourceFieldName)){
		return "sourceFieldName is null";
	}else if(StringUtils.isBlank(destinationFieldName)){
		return "destinationFieldName is null";
	}
	return StringUtils.EMPTY;
	}


}
