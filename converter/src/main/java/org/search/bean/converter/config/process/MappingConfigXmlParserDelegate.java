package org.search.bean.converter.config.process;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.search.bean.converter.Exception.ParserException;
import org.search.bean.converter.config.BeanMappingConfiguration;
import org.search.bean.converter.config.PropertyConfiguration;

import com.google.common.collect.Lists;

/**
 * @author chad
 * @description 解析转换的配置文件的核心类
 *
 */
public class MappingConfigXmlParserDelegate {
	private static Log logger = LogFactory.getLog(MappingConfigXmlParserDelegate.class);
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
			Document document = reader.read(is);
			Element elements = document.getRootElement();
			for (Iterator iter = elements.elementIterator(); iter.hasNext();) {

				BeanMappingConfiguration configuration = new BeanMappingConfiguration();
				Element element = (Element) iter.next();
				// 处理属性
				List attrList = element.attributes();
				if (attrList.size() != 3) {
					throw new ParserException("miss attribute");
				}
				for (int i = 0; i < attrList.size(); i++) {
					Object attr = attrList.get(i);
					if (attr instanceof Attribute) {
						String value = ((Attribute) attr).getValue();
						String name = ((Attribute) attr).getName();
						if (ID.equals(name)) {
							configuration.setId(value);
						} else if (SOURCE_CLASS.equals(name)) {
							configuration.setSourceClass(value);
						} else if (DEST_CLASS.equals(name)) {
							configuration.setDestinationClass(value);
						}
					}
				}
				if(StringUtils.isNotBlank(configuration.isValidate())){
					throw new ParserException(configuration.isValidate());
				}

				// 处理值
				List<PropertyConfiguration> propList = Lists.newArrayList();
				for (Iterator j = element.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					PropertyConfiguration  propConf = getPropConf(node.attributes());
					if(StringUtils.isNotBlank(propConf.isValidate())){
						throw new ParserException(propConf.isValidate());
					}
					propList.add(propConf);
					configuration.setPropList(propList);
				}
				configList.add(configuration);
			}
		} catch (Exception e) {
			logger.error("parse the xml fail",e);
			throw new ParserException(e);
		}
		return configList;
	}

	private static PropertyConfiguration getPropConf(List attrList) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		PropertyConfiguration pConf = new PropertyConfiguration();
		for (int i = 0; i < attrList.size(); i++) {
			Object attr = attrList.get(i);
			if (attr instanceof Attribute) {
				String value = ((Attribute) attr).getValue();
				String name = ((Attribute) attr).getName();
				if (SOURCE_FIELD.equals(name)) {
					pConf.setSourceFieldName(value);
				} else if (DEST_FIELD.equals(name)) {
					pConf.setDestinationFieldName(value);
				} else if (CONVERT_PROCESSOR.equals(name)) {
					Class<?> clazz = MappingConfigXmlParserDelegate.class
							.getClassLoader().loadClass(value);
					if (Processor.class.isAssignableFrom(clazz)) {
						pConf.setProcessor((Processor) clazz.newInstance());
					}
				}
			}
		}
		return pConf;
	}
}
