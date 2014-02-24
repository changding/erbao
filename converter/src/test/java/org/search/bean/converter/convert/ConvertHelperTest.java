package org.search.bean.converter.convert;

import java.lang.reflect.InvocationTargetException;

import org.search.bean.converter.ConvertException;
import org.search.bean.converter.ConverterHelper;
import org.search.bean.converter.DO.Destination;
import org.search.bean.converter.DO.Source;
import org.search.bean.converter.DO.Source1;
import org.search.bean.converter.DO.Source2;
import org.search.bean.converter.manager.ConvertManager;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author chad
 * @description 转换测试
 *
 */
public class ConvertHelperTest extends TestCase{
	
	public void setUp(){
		ConvertManager cm = new ConvertManager();
		cm.setInitConfigLocations(initConfigLocations)
		ConverterHelper.setConverterManager(converterManager)
	}
public void test_类型转换_带processor() throws SecurityException, IllegalArgumentException, ConvertException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException{
	Source source = new Source();
	source.setName("name");
	source.setPwd("pwd");
	Destination dest = ConverterHelper.convert("sourceToDestination.1", source, Destination.class);
	Assert.assertNotNull(dest);
	Assert.assertEquals("name", dest.getName1().getName());
	Assert.assertEquals("pwd",  dest.getPwd2().getPwd());
}
public void test_类型转换_不带processor() throws SecurityException, IllegalArgumentException, ConvertException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException{
	Source source = new Source();
	source.setName("name");
	source.setPwd("pwd");
	Source1 dest = ConverterHelper.convert("sourceToDestination.2", source, Source1.class);
	Assert.assertNotNull(dest);
	Assert.assertEquals("name", dest.getName());
	Assert.assertEquals("pwd",  dest.getPwd());
	
}
public void test_类型转换_部分直接映射_部分需要转换() throws SecurityException, IllegalArgumentException, ConvertException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException{
	Source source = new Source();
	source.setName("name");
	source.setPwd("pwd");
	Source2 dest = ConverterHelper.convert("sourceToDestination.3", source, Source2.class);
	Assert.assertNotNull(dest);
	Assert.assertEquals("name", dest.getName());
	Assert.assertEquals("pwd",  dest.getPwd1().getPwd());
}
}
