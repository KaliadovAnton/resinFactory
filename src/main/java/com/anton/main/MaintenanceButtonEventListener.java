package com.anton.main;

import com.anton.model.Reactor;
import com.anton.service.ReactorService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintenanceButtonEventListener implements ActionListener {
    private final Reactor reactor;
    private final ReactorService service = ReactorService.getSingleReactorService();

    public MaintenanceButtonEventListener(Reactor reactor) {
        this.reactor=reactor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        reactor.setUnderMaintenance(true);
        service.serializeReactorList(service.getReactors());
        JOptionPane.showMessageDialog(null,"Reactor " + reactor.getName() + " is under maintenance now","result",JOptionPane.PLAIN_MESSAGE);
    }
}
