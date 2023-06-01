package com.intecsec.java.basic.sugar.tryresource;

public class MyTryWithResourceTest {

	public static void main(String[] args) {
		
		//将会自动调用conn的close方法
		try(MyConnection conn = new MyConnection())
		{
			conn.sendData();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}

}

class MyConnection implements AutoCloseable {
	 public void sendData() throws Exception {
		 System.out.println("Send Data....");
	 }
	 
	 public void close() throws Exception {
		 System.out.println("Close....");
	 }
}
