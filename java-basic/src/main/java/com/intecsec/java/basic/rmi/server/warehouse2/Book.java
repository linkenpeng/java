package com.intecsec.java.basic.rmi.server.warehouse2;

public class Book extends Product
{
   private String isbn;

   public Book(String title, String isbn, double price)
   {
      super(title, price);
      this.isbn = isbn;
   }
   
   public String getDescription()
   {
      return super.getDescription() + " " + isbn;
   }  
}
