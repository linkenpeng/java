package com.intecsec.java.basic.jvm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class MyClassLoader extends ClassLoader {
   private int key = 3; 
   
   public Class<?> findClass(String name) throws ClassNotFoundException
   {
      try
      {
    	  byte[] classBytes = null;
          //读取Hello.caesar文件，得到所有字节流
          classBytes = loadClassBytes(name);
          //调用defineClass方法产生一个类，并在VM中注册
          Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
          if (cl == null) throw new ClassNotFoundException(name);
          return cl;
      }
      catch (IOException e)
      {
         throw new ClassNotFoundException(name);
      }
   }

   /**
    * Loads and decrypt the class file bytes.
    * @param name the class name
    * @return an array with the class file bytes
    */
   private byte[] loadClassBytes(String name) throws IOException
   {
      String cname = "java-basic/target/classes/com/intecsec/java/basic/jvm/HelloMeta.class";
      byte[] bytes = Files.readAllBytes(Paths.get(cname));      
      return bytes;
   }
}

