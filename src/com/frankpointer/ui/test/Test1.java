package com.frankpointer.ui.test;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Test1 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(603, 680);
        jFrame.setTitle("测试");

        // 设置界面居中
        jFrame.setLocationRelativeTo(null);

        // 默认布局为居中放置，修改布局方式
        jFrame.setLayout(null);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton jtb = new JButton("wo cao");
        jtb.setBounds(0,0,100,100);
        jtb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("鼠标点击");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("鼠标按下不释放");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("鼠标释放");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("鼠标划入");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("鼠标划出");
            }
        });
        jFrame.getContentPane().add(jtb);

        // 使用这个的时候不能使用 JButton
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("按下不送");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("按下松开");
            }
        });

        jFrame.setVisible(true);
    }
}
