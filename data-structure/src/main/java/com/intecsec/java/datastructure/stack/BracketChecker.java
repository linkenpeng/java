package com.intecsec.java.datastructure.stack;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-01 14:46
 **/
public class BracketChecker {
    private String input;
    private String output;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        int stackSize = input.length();
        StackXChar stackXChar = new StackXChar(stackSize);

        for(int j = 0; j < stackSize; j++) {
            char ch = input.charAt(j);
            switch (ch) {
                case '{':
                case '(':
                case '[':
                    stackXChar.push(ch);
                    break;
                case '}':
                case ')':
                case ']':
                    if(!stackXChar.isEmpty()) {
                        char chx = stackXChar.pop();
                        if( (ch == '}' && chx != '{')
                                || (ch == ')' && chx != '(')
                                || (ch == ']' && chx != '[')) {
                            System.out.println("Error: " + ch + " at " + j);
                        }
                    } else {
                        System.out.println("Error: " + ch + " at " + j);
                    }
                    break;
                    default:
                        break;
            }
        }

        if(!stackXChar.isEmpty()) {
            System.out.println("Error: missing right delimiter");
        }
    }
}
