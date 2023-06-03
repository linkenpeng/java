package com.intecsec.java.basic.generic.genericclass;

public class FloatInterval {
	private float lower;
	private float upper;
	
	public FloatInterval(float lower, float upper)
	{
		this.lower = lower;
		this.upper = upper;
	}

	public float getLower() {
		return lower;
	}

	public void setLower(float lower) {
		this.lower = lower;
	}

	public float getUpper() {
		return upper;
	}

	public void setUpper(float upper) {
		this.upper = upper;
	}	
}
