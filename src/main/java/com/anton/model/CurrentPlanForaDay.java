package com.anton.model;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrentPlanForaDay {

    private final HashMap<Reactor, ArrayList<Resin>> currentPlan;

    public CurrentPlanForaDay(HashMap<Reactor, ArrayList<Resin>> currentPlan) {
        this.currentPlan = currentPlan;
    }

    public HashMap<Reactor, ArrayList<Resin>> getCurrentPlan() {
        return currentPlan;
    }
}
