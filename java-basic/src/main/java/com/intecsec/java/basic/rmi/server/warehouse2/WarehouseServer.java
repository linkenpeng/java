package com.intecsec.java.basic.rmi.server.warehouse2;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import javax.naming.*;

public class WarehouseServer
{
   public static void main(String[] args) throws Exception
   {
      System.setProperty("java.security.policy", "server.policy");
      System.setSecurityManager(new SecurityManager());
      
      System.out.println("Constructing server implementation...");
      WarehouseImpl backupWarehouse = new WarehouseImpl(null);
      WarehouseImpl centralWarehouse = new WarehouseImpl(backupWarehouse);
      
      centralWarehouse.add("toaster", new Product("Blackwell Toaster", 23.95));
      backupWarehouse.add("java", new Book("Core Java vol. 2", "0132354799", 44.95));

      System.out.println("Binding server implementation to registry...");
//      Context namingContext = new InitialContext();
//      namingContext.bind("rmi:central_warehouse", centralWarehouse);
      LocateRegistry.createRegistry(8001);//定义端口号
      Naming.rebind("rmi://127.0.0.1:8001/warehouse2", centralWarehouse);

      System.out.println("Waiting for invocations from clients...");
   }
}
