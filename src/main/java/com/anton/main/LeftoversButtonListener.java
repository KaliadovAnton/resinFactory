package com.anton.main;

import com.anton.model.Resin;
import com.anton.service.WarehouseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class LeftoversButtonListener implements ActionListener {
    private final WarehouseService service = new WarehouseService();
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(100,100,500,500);
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(20,2, 2,2));
        try {
            for(Resin resin :service.getLeftoversMap()){
                JLabel resinInfo = new JLabel(resin.getName() + " " + resin.getAmount());
                container.add(resinInfo);
            }
        } catch (IOException | ParseException ioException) {
            ioException.printStackTrace();
        }
    }
}
