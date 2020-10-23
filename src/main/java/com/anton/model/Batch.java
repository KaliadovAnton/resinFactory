package com.anton.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Batch {
    private static long id;
    private final Resin resin;
    private Date date;
    private final Reactor reactor;

    public Batch(Resin resin, Reactor reactor) throws ParseException {
        this.resin = resin;
        this.reactor=reactor;
        this.date=new Date();
        id++;
    }

    public Reactor getReactor() {
        return reactor;
    }

    public long getId() {
        return id;
    }

    public Resin getResin() {
        return resin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
