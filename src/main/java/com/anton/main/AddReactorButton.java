package com.anton.main;

import com.anton.service.ReactorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class AddReactorButton implements ActionListener {

    public JFrame getFrame() throws FileNotFoundException {
        JFrame frame = new JFrame();
        frame.setBounds(100,100,500,250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(10,1, 2,2));
        JButton button = new JButton("Add reactor");
        JLabel nameLabel = new JLabel("Reactor's name");
        JLabel typeLabel = new JLabel("Type's name");
        JTextField nameInput = new JTextField("",10);
        JTextField typeInput = new JTextField("",10);
        container.add(nameLabel);
        container.add(nameInput);
        container.add(typeLabel);
        container.add(typeInput);
        button.addActionListener(new SerializeReactorButtonListener(nameInput, typeInput));
        container.add(button);
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            getFrame();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}
