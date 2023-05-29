package com.intecsec.java.basic.rmi.server.warehouse1;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;


public class WarehouseImpl extends UnicastRemoteObject implements Warehouse
{
   private Map<String, Double> prices;

   public WarehouseImpl() throws RemoteException
   {
	  //物品列表
      prices = new HashMap<>();
      prices.put("面包机", 24.95);
      prices.put("微波炉", 49.95);
   }

   public double getPrice(String description) throws RemoteException
   {
      Double price = prices.get(description);
      return price == null ? 0 : price;
   }
}
