package com.example.swing.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderStatusPage {
    private JFrame frame;
    private List<String> orders;
    private JLabel[] statusLabels; // 用于更新状态的标签

    // 构造函数，接受订单数据
    public OrderStatusPage(List<String> orders) {
        this.orders = orders;
    }

    public void show() {
        frame = new JFrame("Order Status");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel orderStatusLabel = new JLabel("Order Status", SwingConstants.CENTER);
        orderStatusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(orderStatusLabel, BorderLayout.NORTH);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // 定义颜色
        Color pendingColor = new Color(87, 154, 242);
        Color completedColor = new Color(130, 213, 184);

        // 动态生成订单内容
        statusLabels = new JLabel[orders.size()]; // 初始化状态标签数组

        for (int i = 0; i < orders.size(); i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            statusPanel.add(new JLabel(orders.get(i)), gbc); // 显示订单名称

            gbc.gridx = 1;
            statusLabels[i] = new JLabel("Pending", SwingConstants.CENTER); // 默认状态为Pending
            statusLabels[i].setOpaque(true);
            statusLabels[i].setBackground(pendingColor); // Pending的背景颜色
            statusLabels[i].setForeground(Color.WHITE);
            statusPanel.add(statusLabels[i], gbc);
        }

        JScrollPane scrollPane = new JScrollPane(statusPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 创建 Refresh Status 按钮并添加事件
        JButton refreshButton = new JButton("Refresh Status");
        refreshButton.addActionListener(e -> refreshStatus()); // 按钮点击时更新状态
        panel.add(refreshButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    // 更新订单状态的方法
    private void refreshStatus() {
        // 这里可以根据实际需求更新订单状态，下面我们随机改变订单状态
        for (int i = 0; i < orders.size(); i++) {
            String currentStatus = statusLabels[i].getText();
            if (currentStatus.equals("Pending")) {
                statusLabels[i].setText("Completed");
                statusLabels[i].setBackground(new Color(130, 213, 184)); // 设置Completed的颜色
            } else {
                statusLabels[i].setText("Pending");
                statusLabels[i].setBackground(new Color(87, 154, 242)); // 设置Pending的颜色
            }
        }
    }
}

// package com.example.swing.view;

// import javax.swing.*;
// import java.awt.*;
// import java.util.List;

// public class OrderStatusPage {
//     private JFrame frame;
//     private List<String> orders;
//     private JLabel[] statusLabels; // 用于更新状态的标签

//     // 构造函数，接受订单数据
//     public OrderStatusPage(List<String> orders) {
//         this.orders = orders;
//     }

//     public void show() {
//         frame = new JFrame("Order Status");
//         frame.setSize(800, 600);
//         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//         JPanel panel = new JPanel();
//         panel.setLayout(new BorderLayout());

//         JLabel orderStatusLabel = new JLabel("Order Status", SwingConstants.CENTER);
//         orderStatusLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         panel.add(orderStatusLabel, BorderLayout.NORTH);

//         JPanel statusPanel = new JPanel();
//         statusPanel.setLayout(new GridBagLayout());

//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.fill = GridBagConstraints.HORIZONTAL;
//         gbc.insets = new Insets(10, 10, 10, 10);

//         // 定义颜色
//         Color pendingColor = new Color(87, 154, 242);

//         statusLabels = new JLabel[orders.size()]; // 初始化状态标签数组

//         // 动态生成订单内容
//         for (int i = 0; i < orders.size(); i++) {
//             gbc.gridx = 0;
//             gbc.gridy = i;
//             statusPanel.add(new JLabel(orders.get(i)), gbc); // 显示订单名称

//             gbc.gridx = 1;
//             JLabel statusLabel = new JLabel("Pending", SwingConstants.CENTER); // 默认状态为Pending
//             statusLabel.setOpaque(true);
//             statusLabel.setBackground(pendingColor); // Pending的背景颜色
//             statusLabel.setForeground(Color.WHITE);
//             statusPanel.add(statusLabel, gbc);
//         }

//         JScrollPane scrollPane = new JScrollPane(statusPanel);
//         panel.add(scrollPane, BorderLayout.CENTER);

//         // 创建 Refresh Status 按钮并添加事件
//         JButton refreshButton = new JButton("Refresh Status");
//         refreshButton.addActionListener(e -> refreshStatus()); // 按钮点击时更新状态
//         panel.add(refreshButton, BorderLayout.SOUTH);

//         frame.add(panel);
//         frame.setVisible(true);
//     }

//     // 更新订单状态的方法
//     private void refreshStatus() {
//         // 这里可以根据实际需求更新订单状态，下面我们随机改变订单状态
//         for (int i = 0; i < orders.size(); i++) {
//             String currentStatus = statusLabels[i].getText();
//             if (currentStatus.equals("Pending")) {
//                 statusLabels[i].setText("Completed");
//                 statusLabels[i].setBackground(new Color(130, 213, 184)); // 设置Completed的颜色
//             } else {
//                 statusLabels[i].setText("Pending");
//                 statusLabels[i].setBackground(new Color(87, 154, 242)); // 设置Pending的颜色
//             }
//         }
//     }
// }
// // package com.example.swing.view;

// // import javax.swing.*;
// // import java.awt.*;

// // public class OrderStatusPage {
// // private JFrame frame;

// // public void show() {
// // frame = new JFrame("Order Status");
// // frame.setSize(800, 600);
// // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

// // JPanel panel = new JPanel();
// // panel.setLayout(new BorderLayout());

// // JLabel orderStatusLabel = new JLabel("Order Status", SwingConstants.CENTER);
// // orderStatusLabel.setFont(new Font("Arial", Font.BOLD, 24));
// // panel.add(orderStatusLabel, BorderLayout.NORTH);

// // JPanel statusPanel = new JPanel();
// // statusPanel.setLayout(new GridBagLayout());

// // GridBagConstraints gbc = new GridBagConstraints();
// // gbc.fill = GridBagConstraints.HORIZONTAL;
// // gbc.insets = new Insets(10, 10, 10, 10);

// // // 定义颜色
// // Color pendingColor = new Color(87, 154, 242);
// // Color completedColor = new Color(130, 213, 184);

// // // 添加状态标签
// // for (int i = 0; i < 5; i++) {
// // gbc.gridx = 0;
// // gbc.gridy = i;
// // statusPanel.add(new JLabel("Dish " + (i + 1)), gbc);

// // gbc.gridx = 1;
// // JLabel statusLabel = new JLabel(i % 2 == 0 ? "Pending" : "Completed",
// // SwingConstants.CENTER);
// // statusLabel.setOpaque(true); // 启用背景填充
// // statusLabel.setBackground(i % 2 == 0 ? pendingColor : completedColor);
// // statusLabel.setForeground(Color.WHITE); // 设置前景色为白色以提高可读性
// // statusPanel.add(statusLabel, gbc);
// // }

// // JScrollPane scrollPane = new JScrollPane(statusPanel);
// // panel.add(scrollPane, BorderLayout.CENTER);

// // JButton refreshButton = new JButton("Refresh Status");
// // panel.add(refreshButton, BorderLayout.SOUTH);

// // frame.add(panel);
// // frame.setVisible(true);
// // }
// // }