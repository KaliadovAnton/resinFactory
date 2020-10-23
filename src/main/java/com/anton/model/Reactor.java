package com.anton.model;

import java.io.Serializable;

public class Reactor implements Serializable {
    private final String name;
    private boolean isBusy;
    private boolean isUnderMaintenance;
    private final String type;

    public Reactor(String name, String type) {
        this.name = name;
        this.isBusy = false;
        this.isUnderMaintenance=false;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        isUnderMaintenance = underMaintenance;
    }

    public boolean isUnderMaintenance(){
        return isUnderMaintenance;
    }

    @Override
    public String toString() {
        return "Reactor{" +
                "name='" + name + '\'' +
                ", isBusy=" + isBusy +"" +
                ", isUnderMaintenance="+ isUnderMaintenance+
                '}';
    }
}
