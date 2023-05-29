package com.intecsec.java.basic.rmi.server.warehouse2;

import java.rmi.*;
import java.util.*;


public interface Warehouse extends Remote
{  
   double getPrice(String description) throws RemoteException;
   Product getProduct(List<String> keywords) throws RemoteException;
}
