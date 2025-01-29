package main.Ui;

import test.java.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Uitest extends JFrame {
    public Uitest() {
        setSize(400, 300);
        setLocationRelativeTo(null); // 창을 가운데로 띄움
        setDefaultCloseOperation(EXIT_ON_CLOSE); // X버튼을 눌러서 창을 닫았을시 프로그램 종료
        setTitle ( "title" );

        JButton exitBtn = new JButton("exit");

        exitBtn.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          System.exit(0);
                                      }
                                  }
        );
        exitBtn.setSize(100, 50);
        add(exitBtn);

        JButton disposeBtn = new JButton("dispose");
        disposeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
