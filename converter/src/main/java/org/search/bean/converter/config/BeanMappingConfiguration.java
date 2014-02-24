package org.search.bean.converter.config;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.search.bean.converter.config.process.Validable;


/**
 * @author chad
 * @description 代表一个转换的配置实例
 *
 */
public class BeanMappingConfiguration implements Validable{
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
	public String isValidate() {
		if(StringUtils.isEmpty(id)){
			return "id is null";
		}
		if(StringUtils.isEmpty(sourceClass)){
			return "sourceClass is null";
		}
		if(StringUtils.isEmpty(destinationClass)){
			return "destinationClass is null";
		}
		return StringUtils.EMPTY;
	}

}
