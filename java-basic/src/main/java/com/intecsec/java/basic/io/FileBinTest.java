package com.intecsec.java.basic.io;

import java.io.*;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-10 06:59
 **/
public class FileBinTest {
    public static void main(String[] args) {
        String filePath = "/tmp/test.dat";
        //write(filePath);
        read2(filePath);
    }

    public static void write(String filePath) {
        FileOutputStream outputStream = null;
        DataOutputStream dataOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            outputStream = new FileOutputStream(filePath);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            dataOutputStream = new DataOutputStream(bufferedOutputStream);
            dataOutputStream.writeUTF("a");
            dataOutputStream.writeInt(10);
            dataOutputStream.writeLong(1000);
            dataOutputStream.writeUTF("b");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // JDK 1.7+
    public static void write2(String filePath) {
        try(DataOutputStream dataOutputStream = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(filePath)))
        ) {
            dataOutputStream.writeUTF("a");
            dataOutputStream.writeInt(10);
            dataOutputStream.writeLong(1000);
            dataOutputStream.writeUTF("b");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void read(String filePath) {
        FileInputStream inputStream = null;
        DataInputStream dataInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            bufferedInputStream = new BufferedInputStream(inputStream);
            dataInputStream = new DataInputStream(bufferedInputStream);
            dataInputStream.readUTF();
            dataInputStream.readInt();
            dataInputStream.readLong();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void read2(String filePath) {
        try(DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readLong());
            System.out.println(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
