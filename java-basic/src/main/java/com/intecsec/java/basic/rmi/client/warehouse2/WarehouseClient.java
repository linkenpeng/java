package com.intecsec.java.basic.rmi.client.warehouse2;

import java.rmi.*;
import java.util.*;
import javax.naming.*;

public class WarehouseClient
{
   public static void main(String[] args) throws NamingException, RemoteException
   {
      System.setProperty("java.security.policy", "client.policy");
      System.setSecurityManager(new SecurityManager());
      Context namingContext = new InitialContext();
      
      //开始查找RMI注册表上有哪些绑定的服务
      System.out.print("RMI 注册表绑定列表: ");
      NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost:8001/");
      while (e.hasMore())
         System.out.println(e.next().getName());
      
      //获取某一个地址上的服务类
      String url = "rmi://localhost:8001/warehouse2";      
      Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
      
      Scanner in = new Scanner(System.in);
      System.out.print("Enter keywords: ");
      List<String> keywords = Arrays.asList(in.nextLine().split("\\s+"));
      Product prod = centralWarehouse.getProduct(keywords);
      
      System.out.println(prod.getDescription() + ": " + prod.getPrice());
      System.out.println(prod.getLocation());
   }
}
