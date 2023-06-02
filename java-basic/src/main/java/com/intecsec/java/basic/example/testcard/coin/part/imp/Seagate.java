package com.intecsec.java.basic.example.testcard.coin.part.imp;


import com.intecsec.java.basic.example.testcard.coin.part.type.Disk;

/**
 * Seagate 希捷硬盘类
 * @author Tom
 *
 */

public class Seagate extends Disk {

	public Seagate(String name, double price, int volume) {
		super(name, price, volume);
	}

	public void work() {
		System.out.println("This is seagate disk working");
	}
	
}
