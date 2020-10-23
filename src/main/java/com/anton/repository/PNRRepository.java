package com.anton.repository;

import com.anton.model.PNR;

import java.io.*;
import java.util.ArrayList;

public class PNRRepository {

    public ArrayList<PNR> getPNRs() {
        ArrayList<PNR> serializedPNRs = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/pnrs.dat"))){
            serializedPNRs = (ArrayList<PNR>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serializedPNRs;
    }

    private void addPNRs(ArrayList<PNR> pnrs) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/pnrs.dat"))) {
            oos.writeObject(pnrs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPNR(PNR pnr){
        ArrayList<PNR> pnrs = getPNRs();
        pnrs.add(pnr);
        addPNRs(pnrs);
    }
}
