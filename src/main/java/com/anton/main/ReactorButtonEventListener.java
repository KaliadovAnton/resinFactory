package com.anton.main;

import com.anton.model.Reactor;
import com.anton.service.ReactorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReactorButtonEventListener implements ActionListener {

    private final ReactorService service = ReactorService.getSingleReactorService();

    public ReactorButtonEventListener() throws FileNotFoundException {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            getFrame();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private JFrame getFrame() throws IOException {
        JFrame frame = new JFrame();
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(10,1, 2,2));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(dimension.width/2, dimension.height/2,500, 300 );
        for(Reactor reactor: service.getReactors()){
            String maintenance="";
            if (reactor.isUnderMaintenance()){
             maintenance="is under maintenance";
            }else{
                maintenance="is working";
            }
            JLabel label1 = new JLabel(String.format("Reactor %s  %s",reactor.getName(), maintenance));
            JButton button1 = new JButton("Set on maintenance");
            JButton button2 = new JButton("Set off maintenance");
            button1.addActionListener(new MaintenanceButtonEventListener(reactor));
            button2.addActionListener(new UnMaintenanceButtonEventListener(reactor));
            container.add(label1);
            container.add(button1);
            container.add(button2);
        }
        return frame;
    }
}
