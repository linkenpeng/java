package com.intecsec.java.basic.datastructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-01 14:52
 **/
public class ReveaseApp {
    public static void main(String[] args) throws IOException {
        String input, output;

        while (true) {
            System.out.print("Enter a string: ");
            System.out.flush();

            input = getString();
            if(input.equals("")) {
                break;
            }

            Reveaser reveaser = new Reveaser(input);
            output = reveaser.doRev();
            System.out.println("Reversed: " + output);
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
}
