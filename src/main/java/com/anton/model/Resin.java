package com.anton.model;

import java.io.Serializable;

public class Resin implements Serializable {
    private double amount;
    private final String name;
    private final String type;
    private int durationInSeconds = 28800;


    public Resin(double amount, String name, String type) {
        this.amount = amount;
        this.name = name;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    @Override
    public String toString() {
        return "Resin{" +
                "amount=" + amount +
                ", name='" + name + '\'' +
                '}';
    }
}
