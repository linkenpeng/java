package com.intecsec.java.basic.complex.command;

import java.util.*;
import java.io.*;

public class JavaExec2 {
    public static void main(String args[]) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("javac");
            InputStream stderr = p.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("=======error==========");
            while ((line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("");
            int exitVal = p.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}