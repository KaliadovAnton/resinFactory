package com.anton.main;

import com.anton.service.ReactorService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerializeReactorButtonListener implements ActionListener {
    private final ReactorService service = ReactorService.getSingleReactorService();
    private final JTextField name;
    private final JTextField type;

    public SerializeReactorButtonListener(JTextField nameInput, JTextField typeInput) {
        this.name=nameInput;
        this.type=typeInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        service.addReactor(name.getText(), type.getText());
        System.out.println();
        JOptionPane.showMessageDialog(null,"Reactor " + name.getText() + " has been added","result",JOptionPane.PLAIN_MESSAGE);
    }
}
