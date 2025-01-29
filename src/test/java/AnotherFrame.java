package test.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnotherFrame extends JFrame {
    public AnotherFrame() {
        setTitle("Difference between Exit and Dispose Second Frame");
        setSize(430, 110);
        setLocation(0, 120);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnDispose = new JButton("Dispose");
        btnDispose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        btnDispose.setBounds(10, 10, 100, 50);
        getContentPane().add(btnDispose);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        btnExit.setBounds(120, 10, 100, 50);
        getContentPane().add(btnExit);

        setVisible(true);
    }
}
