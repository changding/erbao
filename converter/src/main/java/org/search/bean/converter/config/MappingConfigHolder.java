package org.search.bean.converter.config;

import java.util.concurrent.ConcurrentHashMap;

import org.search.bean.converter.Exception.MappingDefinitionAlreadyExistException;

/**
 * @author chad
 * @description mapping相关配置的持有者
 * 
 */
public class MappingConfigHolder {
	private ConcurrentHashMap<String, BeanMappingConfiguration> mappingDefinitionMap = new ConcurrentHashMap<String, BeanMappingConfiguration>();

	public BeanMappingConfiguration getMappingDefinition(String id) {
		if (mappingDefinitionMap.containsKey(id)) {
			return mappingDefinitionMap.get(id);
		}
		return null;
	}
	public boolean createMappingDefinition(BeanMappingConfiguration beanMappingConfiguration) {
		if(mappingDefinitionMap.putIfAbsent(beanMappingConfiguration.getId(), beanMappingConfiguration) == beanMappingConfiguration){
			return true;
		}
		throw new MappingDefinitionAlreadyExistException(new RuntimeException(),beanMappingConfiguration.getId() +"is already exist");
	}
}