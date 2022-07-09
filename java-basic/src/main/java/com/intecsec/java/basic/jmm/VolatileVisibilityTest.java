package com.intecsec.java.basic.jmm;

/**
 * @description:
 *
 * Volatile缓存可见性实现原理
 * 底层实现主要是通过汇编lock前缀指令，它会锁定这块内存区域的缓存（缓存行锁定）并回写到主内存
 * IA-32和Intel 64 架构软件开发者手册对lock指令的解释
 * 1）会将当前处理器缓存行的数据立即写回到系统内存
 * 2）这个写回内存的操作会引起其他CPU里缓存了该内存地址的数据无效（MESI协议）
 * 3）提供内存屏障功能，使lock前后指令不能重排序
 *
 *
 *
 * 查看运行代码的汇编指令 hsdis
 *    -server 表示jvm以C2编译器编译
 *    -Xcomp 以编译模式运行，默认为mix
 *    -XX:+TraceClassLoading 打印类加载日志
 *    -XX:+PrintAssembly 打印汇编代码，需要hsdis支持
 *    -XX:+UnlockDiagnosticVMOptions
 *    -XX:CompileCommand=compileonly,*VolatileVisibilityTest.prepareData
 *
 * @author: peter.peng
 * @create: 2022-07-07 22:38
 **/
public class VolatileVisibilityTest {

    private static volatile boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("waiting data...");
            while (!initFlag) {

            }
            System.out.println("============success");
        }).start();

        Thread.sleep(2000);

        new Thread(() -> prepareData()).start();
    }


    public static void prepareData() {
        System.out.println("prepare data...");
        initFlag = true;
        System.out.println("prepare data end...");
    }

}
