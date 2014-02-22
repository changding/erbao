package org.search.bean.converter.config.process;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.search.bean.converter.config.BeanMappingConfiguration;
import org.search.bean.converter.config.PropertyConfiguration;
import org.search.bean.converter.exception.ParserException;
import org.search.bean.converter.config.process.Processor;
import com.google.common.collect.Lists;

/**
 * @author chad
 * @description 转换器的解析
 *
 */
public class MappingConfigXmlParserDelegate {
	public static final String ID = "id";// 唯一码
	public static final String ROOT_CONFIG = "config";
	public static final String MAP_CONFIG = "mapping-config";
	public static final String PROP_CONFIG = "property-config";
	public static final String SOURCE_CLASS = "sourceClass";// 唯一码
	public static final String DEST_CLASS = "destinationClass";
	public static final String SOURCE_FIELD = "sourceFieldName";
	public static final String DEST_FIELD = "destinationFieldName";
	public static final String CONVERT_PROCESSOR = "processor";

	public static List<BeanMappingConfiguration> parse(InputStream is) {
		List<BeanMappingConfiguration> configList = Lists.newArrayList();
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(MappingConfigXmlParserDelegate.class.getClassLoader().getResourceAsStream("convert.xml"));
			Element elements = document.getRootElement();
			for (Iterator iter = elements.elementIterator(); iter.hasNext();) {
				
				BeanMappingConfiguration configuration = new BeanMappingConfiguration();
				Element element = (Element) iter.next();
				//处理属性
				List attrList = element.attributes();
 				if(attrList.size() !=3){
 					throw new ParserException("miss attribute");
 				}
				for(int i=0;i<attrList.size();i++){
					Object attr = attrList.get(i);
					if(attr instanceof Attribute){
						String value = ((Attribute) attr).getValue();
						String name = ((Attribute) attr).getName();
						if(ID.equals(name)){
							configuration.setId(value);
						}else if(SOURCE_CLASS.equals(name)){
							configuration.setSourceClass(value);
						}else if(DEST_CLASS.equals(name)){
							configuration.setDestinationClass(value);
						}
					}
				}
				
				//处理值
				List<PropertyConfiguration> propList = Lists.newArrayList();
				for (Iterator j = element.elementIterator(); j.hasNext();) {
					PropertyConfiguration pConf = new PropertyConfiguration();
					Element node = (Element) j.next();
					attrList = node.attributes();
				
					for(int i=0;i<attrList.size();i++){
						Object attr = attrList.get(i);
						if(attr instanceof Attribute){
							String value = ((Attribute) attr).getValue();
							String name = ((Attribute) attr).getName();
							if(SOURCE_FIELD.equals(name)){
								pConf.setSourceFieldName(value);
							}else if(DEST_FIELD.equals(name)){
								pConf.setDestinationFieldName(value);
							}else if(CONVERT_PROCESSOR.equals(name)){
								Class<?> clazz = MappingConfigXmlParserDelegate.class.getClassLoader().loadClass(value);
								if(Processor.class.isAssignableFrom(clazz)){									
									pConf.setProcessor((Processor)clazz.newInstance());
								}
							}
						}
					}

					propList.add(pConf);
					configuration.setPropList(propList);
				}
				configList.add(configuration);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return configList;
	}

}
