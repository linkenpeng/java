package com.intecsec.java.vo;

import com.intecsec.java.util.DateUtils;

import java.util.Date;

public class Car extends CarBase {
	private String brand;
	private String product;
	private int price;
	private double speed;

	private String proDate = "_" + DateUtils.formatDate(new Date(), "yyyyMM");

	public Car() {
		System.out.println("Car");
	}

	public Car(String name) {
		// 不显示调用 super(name) 则默认调用父类无参构造函数
		super(name);
		this.name = name;
		System.out.println(this.name);
	}
	
	public Car(String brand, String product, int price) {
		this.brand = brand;
		this.product = product;
		this.price = price;
	}
	
	public Car(String brand, String product, double speed) {
		this.brand = brand;
		this.product = product;
		this.speed = speed;
	}

	public String getProDate() {
		return proDate;
	}

	public void setProDate(String proDate) {
		this.proDate = proDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getPrice() {
		return price;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", product=" + product + ", price=" + price + ", speed=" + speed + "]";
	}	
}
