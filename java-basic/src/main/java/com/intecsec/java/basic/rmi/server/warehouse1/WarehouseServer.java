package com.intecsec.java.basic.rmi.server.warehouse1;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import javax.naming.*;

/**
 * 产生WarehouseImpl对象，并进行注册在8001端口，对外提供服务
 * 
 */
public class WarehouseServer
{
   public static void main(String[] args) throws Exception
   {
      System.out.println("产生服务器对象");
      WarehouseImpl centralWarehouse = new WarehouseImpl();

      System.out.println("将服务器对象绑定在8001端口，对外提供服务");
      LocateRegistry.createRegistry(8001);//定义端口号
      Naming.rebind("rmi://127.0.0.1:8001/warehouse1", centralWarehouse);

      System.out.println("等待客户端的调用");
   }
}
