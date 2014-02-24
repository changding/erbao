package org.search.bean.converter.config.process;

/**
 * @author chad
 * @descritpion 校验什么原因失败,约定返回实现该接口的对象实例校验失败的原因
 *
 */
public interface Validable {
	public String isValidate();
}
