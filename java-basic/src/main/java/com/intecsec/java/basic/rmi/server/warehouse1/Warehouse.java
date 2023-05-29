package com.intecsec.java.basic.rmi.server.warehouse1;
import java.rmi.*;


public interface Warehouse extends Remote
{  
   double getPrice(String description) throws RemoteException;
}
