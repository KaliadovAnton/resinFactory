package com.anton.model;

import java.util.ArrayList;
import java.util.List;

public class Plan {
    private final List<Resin> plannedResins;

    public Plan(List<Resin> plan){
         this.plannedResins = plan;
    }

    public List<Resin> getPlannedResins() {
        return plannedResins;
    }
}
