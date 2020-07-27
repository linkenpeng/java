package com.intecsec.java.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法：实例工厂方法，即需要创建工厂本身，再调用工厂方法的
 * @author Peter
 *
 */
public class InstanceCarFactory {
	private Map<String, Car> cars = null;
	
	public InstanceCarFactory() {
		cars = new HashMap<String, Car>();
		cars.put("audi", new Car("audi", 300000));
		cars.put("ford", new Car("ford", 400000));
	}
	
	public Car getCar(String name) {
		return cars.get(name);
	}
}
