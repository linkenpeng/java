package com.intecsec.java.tx;

public interface BookShopDao {
	double getBookPrice(int bookId);
	void updateBookStop(int bookId, int number);
	void updateUserAccount(double money, int uid);
}
