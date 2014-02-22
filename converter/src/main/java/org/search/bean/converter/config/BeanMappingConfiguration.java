package org.search.bean.converter.config;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.search.bean.converter.config.process.Validable;

public class BeanMappingConfiguration implements Validable{
	private String id;
	private String sourceClass;
	private String destinationClass;
	List<PropertyConfiguration> propList;

	public String getSourceClass() {
		return sourceClass;
	}

	public void setSourceClass(String sourceClass) {
		this.sourceClass = sourceClass;
	}

	public String getDestinationClass() {
		return destinationClass;
	}

	public void setDestinationClass(String destinationClass) {
		this.destinationClass = destinationClass;
	}

	public List<PropertyConfiguration> getPropList() {
		return propList;
	}

	public void setPropList(List<PropertyConfiguration> propList) {
		this.propList = propList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String isValidate() {
if(StringUtils.isBlank(id)){
	return "id is blank";
}else if(StringUtils.isBlank(sourceClass)){
	return "sourceClass is blank";
}else if(StringUtils.isBlank(destinationClass)){
	return "destinationClass is blank ";
}
		return null;
	}


}
