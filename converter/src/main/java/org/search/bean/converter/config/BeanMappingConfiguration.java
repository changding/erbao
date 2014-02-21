package org.search.bean.converter.config;

import java.util.List;

import org.search.bean.converter.config.process.Validator;

public class BeanMappingConfiguration implements Validator{
	public String id;
	public String sourceClass;
	public String destinationClass;
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
	public boolean isValidate() {
		return false;
	}

}
