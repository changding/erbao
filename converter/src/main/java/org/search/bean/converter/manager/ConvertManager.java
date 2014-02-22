package org.search.bean.converter.manager;

import java.util.List;

import org.search.bean.converter.config.BeanMappingConfiguration;
import org.search.bean.converter.config.MappingConfigHolder;
import org.search.bean.converter.config.process.MappingConfigXmlParserDelegate;
public class ConvertManager {
	private String[] initConfigLocations;//Ö»Ö§³Öclasspath
	private MappingConfigHolder configHolder = new MappingConfigHolder();
	public void init(){
		if(initConfigLocations != null && initConfigLocations.length>=1){		
			for(String path:initConfigLocations){				
				List<BeanMappingConfiguration>  configurationList = MappingConfigXmlParserDelegate.parse(ConvertManager.class.getClassLoader().getResourceAsStream(path));
		        if(configurationList != null && configurationList.size()>0){
		        	for(BeanMappingConfiguration beanMappingConfiguration:configurationList){
		        		configHolder.createMappingDefinition(beanMappingConfiguration);
		        	}
		        }
			}
		}
	}
	public String[] getInitConfigLocations() {
		return initConfigLocations;
	}
	public void setInitConfigLocations(String[] initConfigLocations) {
		this.initConfigLocations = initConfigLocations;
	}
	public MappingConfigHolder getConfigHolder() {
		return configHolder;
	}
	public void setConfigHolder(MappingConfigHolder configHolder) {
		this.configHolder = configHolder;
	}
	
}
