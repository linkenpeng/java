package com.intecsec.java.basic.rmi.client.warehouse1;
import java.rmi.*;
import java.util.*;
import javax.naming.*;


public class WarehouseClient
{
   public static void main(String[] args) throws NamingException, RemoteException
   {
      Context namingContext = new InitialContext();
      
      //开始查找RMI注册表上有哪些绑定的服务
      System.out.print("RMI 注册表绑定列表: ");
      Enumeration<NameClassPair> e = namingContext.list("rmi://127.0.0.1:8001/");
      while (e.hasMoreElements())
         System.out.println(e.nextElement().getName());
      
      //获取某一个地址上的服务类
      String url = "rmi://127.0.0.1:8001/warehouse1";      
      Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
      //System.out.println(centralWarehouse.getClass().getName());
      
      //输入参数  取得结果
      String descr = "面包机";
      double price = centralWarehouse.getPrice(descr);
      System.out.println(descr + ": " + price);
   }
}
