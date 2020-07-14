package com.intecsec.java.basic.thread;

/**
 * @description: 多线程得不到预期结果
 * @author: peter.peng
 * @create: 2019-04-22 15:21
 **/
public class ThreadAdd {

    public static void main(String[] args) {

        ClassAdd add = new ClassAdd();

        for (int i = 0; i < 5; i++) {
            //开启5个新的线程并启动
            new NewThread(add).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("最后的值为" + add.num);
    }
}

//线程类NewThread 对数字进行操作
class NewThread extends Thread {

    private ClassAdd classAdd;

    public NewThread(ClassAdd classAdd) {
        this.classAdd = classAdd;
    }

    @Override
    public void run() {
        classAdd.add();
    }

}

//类ClassAdd 给数字加1
class ClassAdd {

    public int num = 0;

    public void add() {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num += 1;
    }
}
