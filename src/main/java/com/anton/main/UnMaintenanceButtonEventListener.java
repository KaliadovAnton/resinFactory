package com.anton.main;

import com.anton.model.Reactor;
import com.anton.service.ReactorService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnMaintenanceButtonEventListener implements ActionListener {
    private final Reactor reactor;
    private final ReactorService service = ReactorService.getSingleReactorService();

    public UnMaintenanceButtonEventListener(Reactor reactor) {
        this.reactor = reactor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        reactor.setUnderMaintenance(false);
        service.serializeReactorList(service.getReactors());
        JOptionPane.showMessageDialog(null,"Reactor " + reactor.getName() + " is not under maintenance now","result",JOptionPane.PLAIN_MESSAGE);
    }
}
