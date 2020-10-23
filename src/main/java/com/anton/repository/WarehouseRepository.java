package com.anton.repository;

import com.anton.model.Resin;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class WarehouseRepository {
    public ArrayList<Resin> getLeftovers() throws IOException, ParseException {
        ArrayList<Resin> leftovers = new ArrayList<>();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(new Date().toInstant().toString().substring(0,10));
        Date targetDate = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/leftovers.dat"))) {
            HashMap<Date, ArrayList<Resin>> leftoversMap = (HashMap<Date, ArrayList<Resin>>) ois.readObject();
            for(Date date1: leftoversMap.keySet()){
                if(date1.after(targetDate)&&(date1.before(date)||date1.equals(date))){
                    return leftoversMap.get(date1);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("должен передать пустой ArrayList" + leftovers);
        return leftovers;
    }

    public void serializeLeftovers(ArrayList<Resin> leftovers) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(new Date().toInstant().plusSeconds(86400).toString().substring(0,10));
        HashMap<Date, ArrayList<Resin>> leftoversMap = getMapOfLeftovers();
        leftoversMap.put(date, leftovers);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/leftovers.dat"))) {
            oos.writeObject(leftoversMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Date, ArrayList<Resin>> getMapOfLeftovers(){
        HashMap<Date, ArrayList<Resin>> leftoversMap = new HashMap<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/leftovers.dat"))) {
            leftoversMap = (HashMap<Date, ArrayList<Resin>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return leftoversMap;
    }
}
