package com.intecsec.java.basic.annotations;


import com.intecsec.java.basic.annotations.rect.Point;
import com.intecsec.java.basic.annotations.rect.Rectangle;

public class SourceLevelAnnotationDemo
{
   public static void main(String[] args)
   {
      Rectangle rect = new Rectangle(new Point(10, 10), 20, 30);
      //System.out.println(ToStringGenerator.toString(rect));
   }
}
