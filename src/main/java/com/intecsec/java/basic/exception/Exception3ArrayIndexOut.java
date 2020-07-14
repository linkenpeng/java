package com.intecsec.java.basic.exception;

import java.util.logging.Logger;

public class Exception3ArrayIndexOut {
	private static Logger logger = Logger.getLogger("Exception3ArrayIndexOut");
	public static void main(String[] args) {
		try {
			int[] arr = {1,2,3};
			System.out.println(arr[5]);
		} catch (Exception e) {
			logger.severe(e.toString());
			e.printStackTrace();
		}
	}
}
