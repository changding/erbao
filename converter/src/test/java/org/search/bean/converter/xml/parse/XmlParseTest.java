package org.search.bean.converter.xml.parse;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.search.bean.converter.config.BeanMappingConfiguration;
import org.search.bean.converter.config.process.MappingConfigXmlParserDelegate;

/**
 * ����xml�Ĳ���
 */
public class XmlParseTest extends TestCase {
	public void test_����xml() {
		List<BeanMappingConfiguration> l = MappingConfigXmlParserDelegate.parse(this.getClass().getClassLoader().getResourceAsStream("convert.xml"));
		Assert.assertNotNull(l);
		Assert.assertEquals(true, l.size()==1);
	}
	public void test_������xml() {
		List<BeanMappingConfiguration> l = MappingConfigXmlParserDelegate.parse(this.getClass().getClassLoader().getResourceAsStream("empty.xml"));
		Assert.assertNotNull(l);
		Assert.assertEquals(true, l.size()==0);
	}
	public void test_������������xml() {
		List<BeanMappingConfiguration> l = MappingConfigXmlParserDelegate.parse(this.getClass().getClassLoader().getResourceAsStream("no-mapping.xml"));
		Assert.assertNotNull(l);
		Assert.assertEquals(true, l.size()==1);
	}
}
