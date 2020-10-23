package com.anton.model;

import java.util.ArrayList;

public class PNR {
    private static long id;
    private final ArrayList<Batch> batches;

    public PNR(ArrayList<Batch> batches) {
        this.batches = batches;
        id++;
    }

    public long getId() {
        return id;
    }

    public ArrayList<Batch> getBatches() {
        return batches;
    }

    public void addBatch(Batch batch){
        batches.add(batch);
    }

}
