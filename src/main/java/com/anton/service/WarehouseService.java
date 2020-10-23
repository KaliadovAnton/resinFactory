package com.anton.service;

import com.anton.model.Resin;
import com.anton.repository.WarehouseRepository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WarehouseService {
    private final WarehouseRepository repository = new WarehouseRepository();

    public ArrayList<Resin> getLeftovers() throws IOException, ParseException {
        return repository.getLeftovers();
    }

    public void saveLeftovers(ArrayList<Resin> leftovers) throws ParseException {
        repository.serializeLeftovers(leftovers);
    }
    public ArrayList<Resin> getLeftoversMap() throws IOException, ParseException {
        return repository.getMapOfLeftovers().get(new SimpleDateFormat("yyyy-MM-dd").parse(new Date().toInstant().plusSeconds(86400).toString().substring(0,10)));
    }
}
