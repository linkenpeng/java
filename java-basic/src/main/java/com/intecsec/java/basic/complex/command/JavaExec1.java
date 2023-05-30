package com.intecsec.java.basic.complex.command;

import java.util.*;
import java.io.*;

public class JavaExec1 {
    public static void main(String args[]) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("javac");
            int exitVal = p.exitValue(); // 进程还没结束，容易爆发异常!
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}