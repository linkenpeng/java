package com.intecsec.java.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态工厂方法：直接调用某一个类的静态方法就可以返回Bean的实例
 * @author Peter
 *
 */
public class StaticCarFactory {
	
	private static Map<String, Car> cars = new HashMap<String, Car>();
	
	static {
		cars.put("audi", new Car("audi", 300000));
		cars.put("ford", new Car("ford", 400000));
	}
	
	// 静态工厂方法
	public static Car getCar(String name)  {
		return cars.get(name);
	}
}
