package com.anton.model;

import java.util.ArrayList;

public class Warehouse {
    private static Warehouse warehouse;
    private ArrayList<Resin> leftovers;

    private Warehouse(ArrayList<Resin> leftovers) {
        this.leftovers = leftovers;
    }

    public void setLeftovers(ArrayList<Resin> leftovers) {
        this.leftovers = leftovers;
    }

    public ArrayList<Resin> getLeftovers() {
        return leftovers;
    }

    public static Warehouse getInstance(ArrayList<Resin> leftovers){
        if(warehouse == null){
            warehouse = new Warehouse(leftovers);
            return warehouse;
        }
        return warehouse;
    }
}
