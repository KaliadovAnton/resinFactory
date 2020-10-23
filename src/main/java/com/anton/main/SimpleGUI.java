package com.anton.main;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class SimpleGUI extends JFrame {

    public SimpleGUI() throws FileNotFoundException {
        super("Resin app");
        this.setBounds(100,100,500,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5,2, 2,2));
        JLabel placeholder = new JLabel("write path to the book");
        container.add(placeholder);
        JTextField input = new JTextField("", 10);
        container.add(input);
        JLabel placeholder2 = new JLabel("write name of the sheet");
        container.add(placeholder2);
        JTextField input2 = new JTextField("", 10);
        container.add(input2);
        JButton button = new JButton("Make a table");
        JButton addReactor = new JButton("Make a new reactor");
        addReactor.addActionListener(new AddReactorButton());
        JButton reactorButton = new JButton("Reactors' maintenance info");
        JButton leftoversInfo = new JButton("Leftover resin ");
        leftoversInfo.addActionListener(new LeftoversButtonListener());
        JRadioButton radioButton = new JRadioButton("Table for UF resins");
        JRadioButton radioButton1 = new JRadioButton("Table for PF resins");
        JRadioButton radioButton2 = new JRadioButton("Table for IR resins");
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton);
        group.add(radioButton1);
        group.add(radioButton2);
        container.add(radioButton);
        radioButton.setSelected(true);
        container.add(radioButton1);
        container.add(radioButton2);
        ArrayList<JRadioButton> buttons = new ArrayList<>(Arrays.asList(radioButton, radioButton1, radioButton2));
        button.addActionListener(new ButtonEventListener(input, input2, buttons));
        reactorButton.addActionListener(new ReactorButtonEventListener());
        container.add(button);
        container.add(reactorButton);
        container.add(addReactor);
        container.add(leftoversInfo);
    }
}
