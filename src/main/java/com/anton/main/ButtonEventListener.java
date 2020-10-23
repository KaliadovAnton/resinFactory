package com.anton.main;

import com.anton.service.TableService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ButtonEventListener implements ActionListener {
    private final JTextField input;
    private final JTextField input2;
    private final ArrayList<JRadioButton> buttons;
    public ButtonEventListener(JTextField input, JTextField input2, ArrayList<JRadioButton> buttons) {
        this.input = input;
        this.input2 = input2;
        this.buttons = buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String workbookName = input.getText();
        String sheetName = input2.getText();
        String resinType = getResinType();
        try {
            TableService service = new TableService(workbookName, sheetName, resinType);
            service.createATable();
        } catch (IOException | ParseException ioException) {
            ioException.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Выполнено построение таблицы для "+resinType,"result",JOptionPane.PLAIN_MESSAGE);
    }

    private String getResinType(){
        String type = "";
        for(int i = 0; i<buttons.size();i++){
            if(buttons.get(i).isSelected()){
                switch (i){
                    case 0:
                        type="UFMUF";
                        break;
                    case 1:
                        type="PF";
                        break;
                    case 2:
                        type="IR";
                        break;
                }
                break;
            }
        }
        return type;
    }
}
