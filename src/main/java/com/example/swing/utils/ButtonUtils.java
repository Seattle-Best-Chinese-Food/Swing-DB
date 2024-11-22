package com.example.swing.utils;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

public class ButtonUtils {
     // Static method to create and configure a button
    public static JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(65, 25));
        button.setForeground(color);
        return button;
    }
}
