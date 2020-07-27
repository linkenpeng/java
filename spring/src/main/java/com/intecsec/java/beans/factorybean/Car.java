package com.intecsec.java.beans.factorybean;

public class Car {
	private String brand;
	private String name;
	private double price;
	
	public Car(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", name=" + name + ", price=" + price + "]";
	}	
	
}
