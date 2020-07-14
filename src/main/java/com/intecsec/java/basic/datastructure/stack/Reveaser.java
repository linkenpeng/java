package com.intecsec.java.basic.datastructure.stack;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-01 14:46
 **/
public class Reveaser {
    private String input;
    private String output;

    public Reveaser(String in) {
        input = in;
    }

    public String doRev() {
        int stackSize = input.length();
        StackXChar stackXChar = new StackXChar(stackSize);

        for(int j = 0; j < stackSize; j++) {
            char ch = input.charAt(j);
            stackXChar.push(ch);
        }

        String output = "";
        while (!stackXChar.isEmpty()) {
            char ch = stackXChar.pop();
            output += ch;
        }

        return output;
    }
}
