package com.intecsec.java.basic.example.testcard.coin.part.imp;


import com.intecsec.java.basic.example.testcard.coin.part.type.Disk;

/**
 * WestDigital 西部数据硬盘类
 * @author Tom
 *
 */
public class WestDigital extends Disk {

	public WestDigital(String name, double price, int volume) {
		super(name, price, volume);
	}

	public void work() {
		System.out.println("This is westdigital disk working");
	}
	
}

