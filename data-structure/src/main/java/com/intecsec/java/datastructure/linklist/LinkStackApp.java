package com.intecsec.java.datastructure.linklist;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-02-11 11:07
 **/
public class LinkStackApp {

    public static void main(String[] args) {
        LinkStatck theStack = new LinkStatck();

        theStack.push(22, 2.99);
        theStack.push(44, 4.99);


        theStack.displayStack();

        theStack.push(66, 6.99);
        theStack.push(88, 8.99);

        theStack.displayStack();

        theStack.pop();
        theStack.pop();

        theStack.displayStack();

    }
}
