package com.intecsec.java.basic.jmm;

/**
 * @description: JMM数据原子操作
 * JMM: Java Memory Model
 * read(读取): 从主内存读取数据
 * load(载入): 将主内存读取到的数据写入工作内存
 * use(使用): 从工作内存读取数据来计算
 * assign(赋值): 将计算好的值重新赋值到工作内存中
 * store(存储): 将工作内存数据写入主内存
 * write(写入): 将store过去的变量赋值给主内存中的变量
 * lock(锁定): 将主内存变量加锁，标识为线程独占状态
 * unlock(解锁): 将主内存变量解锁，解锁后其他线程可以锁定该变量
 *
 *
 * 缓存总线 缓存一致性协议（MESI）
 * 多个cpu从主内存读取同一个数据到各自的高速缓存，当其中某个cpu修改了缓存里的数据，该数据会马上同步回主内存，
 * 其他cpu通过总线嗅探机制可以感知到数据的变化从而将自己缓存里的数据失效
 *
 * 缓存加锁
 * 缓存锁的核心机制是基于缓存一致性协议来实现的，一个处理器的缓存回写到内存会导致其他处理器的缓存无效，
 * IA-32和Intel 64 处理器使用MESI实现缓存一致性协议
 *
 * @author: peter.peng
 * @create: 2022-07-07 23:21
 **/
public class JmmTest {
    public static void main(String[] args) {

    }
}
