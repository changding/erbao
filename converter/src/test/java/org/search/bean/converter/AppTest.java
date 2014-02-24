package org.search.bean.converter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.search.bean.converter.config.process.MappingConfigXmlParserDelegate;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	public void test_解析xml() {
		MappingConfigXmlParserDelegate.parse(this.getClass().getClassLoader().getResourceAsStream("convert.xml"));
	}
	public void test_解析空xml() {
		MappingConfigXmlParserDelegate.parse(this.getClass().getClassLoader().getResourceAsStream("empty.xml"));
	}
	public void test_解析空无属性xml() {
		MappingConfigXmlParserDelegate.parse(this.getClass().getClassLoader().getResourceAsStream("no-mapping.xml"));
	}
}
