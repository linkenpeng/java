package com.intecsec.java.basic.rmi.client.warehouse1;
import java.rmi.*;

/**
 * 接口类
 * 
 */
public interface Warehouse extends Remote
{  
   double getPrice(String description) throws RemoteException;
}
