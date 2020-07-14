package com.intecsec.java.basic.datastructure.array;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-01-17 15:10
 **/
public class ClassPersonArrayApp {

    public static void main(String[] args) {
        ClassPersonArray arr;
        arr = new ClassPersonArray(100);

        arr.inseart("Evans", "Patty", 24);
        arr.inseart("Smith", "Doc", 59);
        arr.inseart("Smith", "Lorraine", 37);
        arr.inseart("Smith", "Paul", 37);
        arr.inseart("Yee", "Tom", 43);
        arr.inseart("Hashimoto", "Sato", 21);
        arr.inseart("Stimson", "Henry", 29);
        arr.inseart("Velasquez", "Jose", 72);
        arr.inseart("Vang", "Minh", 22);
        arr.inseart("Creswell", "Lucinda", 18);

        arr.displayA();

        // search
        String searchName = "Yee";
        Person found = arr.find(searchName);
        if(found != null) {
            System.out.println("Found: " + searchName);
            found.displayPerson();
        } else {
            System.out.println("Can't found: " + searchName);
        }

        System.out.println(" delete Hashimoto, Stimson, Vang");

        // del
        arr.delete("Hashimoto");
        arr.delete("Stimson");
        arr.delete("Vang");

        arr.displayA();
    }
}
