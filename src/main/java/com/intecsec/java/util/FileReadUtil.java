package com.intecsec.java.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

public class FileReadUtil {
    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static String readFileByBytes(String fileName) {
        InputStream in = null;
        String data = "";
        try {
            byte[] tempbytes = new byte[1024];
            int byteread = 0;
            in = new FileInputStream(fileName);          
            ByteArrayOutputStream memStream = new ByteArrayOutputStream();
            while ((byteread = in.read(tempbytes)) != -1) {
            	memStream.write(tempbytes, 0, byteread);
            }
            data = new String(memStream.toByteArray());
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                	e1.printStackTrace();
                }
            }
        }
        return data;
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static String readFileByChars(String fileName) {
        Reader reader = null;
        String result = "";
        try {
            char[] tempchars = new char[100];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            StringBuilder sb = new StringBuilder();            
            while ((charread = reader.read(tempchars)) != -1) {
            	sb.append(tempchars, 0, charread);
            }
            result = sb.toString();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        
        return result;
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String result = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                result += tempString;
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return result;
    }

    /**
     * 随机读取文件内容
     */
    public static String readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        String data = "";
        try {
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[100];
            int byteread = 0;
            
            // 将一次读取的字节数赋给byteread
            ByteArrayOutputStream memStream = new ByteArrayOutputStream();
            while ((byteread = randomFile.read(bytes)) != -1) {
            	memStream.write(bytes, 0, byteread);
            }
            data = new String(memStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                	e1.printStackTrace();
                }
            }
        }
        return data;
    }

    /**
     * 显示输入流中还剩的字节数
     */
    private static int showAvailableBytes(InputStream in) {
    	int bytes = 0;
        try {
            bytes = in.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static void main(String[] args) {
        String fileName = "E:\\point_time.txt";
        String str = FileReadUtil.readFileByBytes(fileName);
        String[] splitStr = str.split("\r\n");
        for(int i = 0; i< splitStr.length; i++) {
        	String[] datas = splitStr[i].split(" ");
        	String sql = "update user_order set point_status=2, point_time='";
        	sql += datas[1] + " " + datas[2] + "'";
        	sql += " where order_sn='" + datas[0] + "' and point_status=3;";
        	System.out.println(sql);
        }
        
    }
}