package com.frankpointer.ui.test;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Test2 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(603, 680);
        jFrame.setTitle("测试");

        // 设置界面居中
        jFrame.setLocationRelativeTo(null);

        // 默认布局为居中放置，修改布局方式
        jFrame.setLayout(null);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("按下不送");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("按下松开");
                // 这里 keyCode 的值不是 ascii
                int keyCode = e.getKeyCode();
                System.out.println("keyCode = " + keyCode);
            }
        });

        jFrame.setVisible(true);
    }
}
