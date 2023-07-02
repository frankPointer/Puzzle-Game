package com.frankpointer.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PuzzleGameUi extends JFrame {
    final int IMAGE_WIDTH = 105;
    final int IMAGE_HEIGHT = 105;
    final int NUMBER_OF_ROWS = 4;
    final int NUMBER_OF_COLUMNS = 4;

    // 用来存储图片序号的二维数组
    int[][] data = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
    // 用来储存 空白图片的坐标
    int x;
    int y;

    // 统计步数
    int stepCount = 0;

    public PuzzleGameUi() {
        // 初始化界面
        initJFrame();

        // 初始化菜单
        initMenuBar();
        // 初始化二维数组
        initData();
        // 添加图片
        initImage();
        // 移动图片
        movePicture();
        // 显示窗口
        setVisible(true);
    }

    private void movePicture() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (isVictory()) {
                    return;
                }
                if (e.getKeyCode() == 32) {
                    // 去除图片
                    getContentPane().removeAll();

                    JLabel image = new JLabel(new ImageIcon("image/animal/animal3/all.jpg"));
                    image.setBounds(83,134,IMAGE_WIDTH*NUMBER_OF_COLUMNS, IMAGE_HEIGHT *NUMBER_OF_ROWS);
                    getContentPane().add(image);

                    // 添加背景图片
                    JLabel backgroundLabel = new JLabel(new ImageIcon("image/background.png"));
                    backgroundLabel.setBounds(40,40,508,560);
                    getContentPane().add(backgroundLabel);

                    // 刷新
                    getContentPane().repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (isVictory()) {
                    return;
                }

                // 左：37 上：38 右：39 下：40
                int keyCode = e.getKeyCode();
                if (keyCode == 37) { // 左
                    // 此时空白图片处于最右边，其右边没有图片可以向左移动
                    if (y == 3) {
                        return;
                    }
                    data[x][y] = data[x][y+1];
                    data[x][y+1] = 0;

                    // 向左移动之后 y 的值加一
                    y++;

                    // 步数加一
                    stepCount++;

                    // 重新加载图片
                    initImage();
                } else if (keyCode == 38) { // 上
                    if (x == 3) {
                        return;
                    }
                    data[x][y] = data[x+1][y];
                    data[x+1][y] = 0;
                    x++;

                    // 步数加一
                    stepCount++;

                    initImage();
                } else if (keyCode == 39) { // 右
                    if (y == 0) {
                        return;
                    }
                    data[x][y] = data[x][y-1];
                    data[x][y-1] = 0;
                    y--;

                    // 步数加一
                    stepCount++;

                    initImage();
                } else if (keyCode == 40) { // 下
                    if (x == 0) {
                        return;
                    }
                    data[x][y] = data[x-1][y];
                    data[x-1][y] = 0;
                    x--;

                    // 步数加一
                    stepCount++;

                    initImage();
                } else if (keyCode == 32) { // 按空格查看完整图片
                    initImage();
                } else if (keyCode == 87) { // 按 w 一键通过
                    data = new int[][] {
                        {1,2,3,4},
                        {5,6,7,8},
                        {9,10,11,12},
                        {13,14,15,0}
                    };
                    initImage();
                }
            }
        });
    }

    /**
     * 此方法目的是生成一个 0-15 的随机二维数组
     * 添加图片的时候使用此二维数组
     */
    private void initData() {
        int[] tempArr = new int[16];
        for (int i = 0; i < tempArr.length; i++) {
            tempArr[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int randomIndex = random.nextInt(tempArr.length);
            int number = tempArr[randomIndex];
            tempArr[randomIndex] = tempArr[i];
            tempArr[i] = number;
        }
        for (int i = 0; i < tempArr.length; i++) {
            // 记录空白图片的位置
            if (tempArr[i] == 0) {
                x = i/NUMBER_OF_ROWS;
                y = i%NUMBER_OF_COLUMNS;
            }
            data[i/NUMBER_OF_ROWS][i%NUMBER_OF_COLUMNS] = tempArr[i];
        }
    }

    /**
     * 初始化窗口界面
     */
    private void initJFrame() {
        setSize(603, 680);
        setTitle("拼图1.0");

        // 设置界面居中
        setLocationRelativeTo(null);

        // 默认布局为居中放置，修改布局方式
        setLayout(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * 初始化状态栏
     */
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // 创建两个菜单
        JMenu functionMenu = new JMenu("功能");
        JMenu aboutMenu = new JMenu("关于");

        // 创建菜单里面的功能
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem gameOverItem = new JMenuItem("退出游戏");
        // 添加监听

        // 监听“重新游戏”
        replayItem.addActionListener(e -> {
            // 计步器清零
            stepCount = 0;
            initData();
            initImage();
        });

        // 监听“结束游戏”
        gameOverItem.addActionListener(e -> System.exit(0));

        // 关于里面的 item
        JMenuItem authorItem = new JMenuItem("作者");

        // 监听“作者”
        authorItem.addActionListener(e -> {
            // 设置对话框属于谁、标题、是否为模态
            // 模态：不关闭没法进行其他的事情，反之就是非模态
            JDialog jDialog = new JDialog(this,"关于",true);
            JLabel image =  new JLabel(new ImageIcon("image/ikun.jpeg"));
            image.setSize(300,300);
            jDialog.getContentPane().add(image);
            jDialog.setSize(400,400);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);
        });
        // 将每个组件添加到一起
        functionMenu.add(replayItem);
        functionMenu.add(reLoginItem);
        functionMenu.add(gameOverItem);
        aboutMenu.add(authorItem);
        menuBar.add(functionMenu);
        menuBar.add(aboutMenu);
        setJMenuBar(menuBar);
    }

    /**
     * 初始化图片组件
     * 添加图片的时候，最先添加的图片处于图层的最上面，后来的处于下面
     */
    private void initImage() {
        // 每次加载图片之前，先把之前加载的图片给清除了
        getContentPane().removeAll();

        // 添加步数标签
        JLabel stepLabel = new JLabel("步数：" + stepCount + "步");
        stepLabel.setBounds(40,20,100,20);
        getContentPane().add(stepLabel);

        // 判断是否胜利
        if (isVictory()) {
            JLabel winImage = new JLabel(new ImageIcon("image/win.png"));
            winImage.setBounds(200,300,197,73);
            getContentPane().add(winImage);
        }
        // 使用循环添加所有图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 创建图片的对象
                int num = data[i][j];
                JLabel imageLabel = new JLabel(new ImageIcon("image\\animal\\animal3\\" + num + ".jpg"));
                // 指定图片添加的位置
                imageLabel.setBounds(j * IMAGE_WIDTH + 83, i* IMAGE_HEIGHT + 134, IMAGE_WIDTH, IMAGE_HEIGHT);
                // 给标签添加边框
                imageLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                // 添加 JLabel
                getContentPane().add(imageLabel);
            }
        }

        // 添加游戏的背景图片
        JLabel backgroundLabel = new JLabel(new ImageIcon("image/background.png"));
        backgroundLabel.setBounds(40,40,508,560);
        getContentPane().add(backgroundLabel);
        // 刷新
        getContentPane().repaint();
    }

    private boolean isVictory() {
        int[][] win = new int[][] {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}
        };
        // 判断是否完成
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
