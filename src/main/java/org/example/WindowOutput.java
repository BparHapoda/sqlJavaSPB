package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class WindowOutput extends JFrame {
    public WindowOutput (){
        setTitle("SQLDatabase");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300,100,600,600);
        setVisible(true);
        JButton jb1 = new JButton("Вывести базу данных");
        JButton jb2 = new JButton("button2");
        JButton jb3 = new JButton("button3");
        JButton jb4 = new JButton("button4");
        add(jb1, BorderLayout.NORTH);
        add(jb2, BorderLayout.WEST);
        add(jb3, BorderLayout.EAST);
        add(jb4, BorderLayout.SOUTH);
        TextField textField = new TextField("введи текст",5);
        add(textField);
        TextArea textArea=new TextArea();
        add(textArea);
       textArea.setEditable(false);
       jb1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String query = "SELECT * FROM customers";
               try {
                   print(textArea,DataBaseQuery.selectQuery(query));
               } catch (SQLException ex) {
                   throw new RuntimeException(ex);
               }
           }
       });

    }
    public void print (TextArea textArea,String answer) {

        textArea.setText(answer);


    }
}
