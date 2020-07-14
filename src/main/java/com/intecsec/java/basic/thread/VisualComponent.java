package com.intecsec.java.basic.thread;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description: 线程安全的列表
 *
 * CopyOnWriteArrayList : 线程安全的链表
 *
 * @author: peter.peng
 * @create: 2019-11-10 20:48
 **/
public class VisualComponent {
    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
    }

    public void addMouseListener(MouseListener mouseListener) {
        mouseListeners.add(mouseListener);
    }

    public void removeKeyListner(KeyListener keyListener) {
        keyListeners.remove(keyListener);
    }

    public void removeMouseLister(MouseListener mouseListener) {
        mouseListeners.remove(mouseListener);
    }
}
