package com.anton.repository;

import com.anton.model.Reactor;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReactorRepository {
    private final ArrayList<Reactor> reactorArrayList = getReactors();

    public void addReactor(String name, String type){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/reactors.dat"))) {
            Reactor reactor = new Reactor(name, type);
            reactorArrayList.add(reactor);
            oos.writeObject(reactorArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serializeReactors(ArrayList<Reactor> reactors){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/reactors.dat"))) {
            oos.writeObject(reactors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Reactor> getReactors(){
        ArrayList<Reactor> reactors = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/reactors.dat"))) {
            reactors=(ArrayList<Reactor>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reactors;
    }

    public void toggleReactorUnderMaintenance(Reactor reactor){

        serializeReactors((ArrayList<Reactor>) reactorArrayList.stream().map(reactor1 -> {
            if (reactor.equals(reactor1)) {
                reactor1.setUnderMaintenance(!reactor.isUnderMaintenance());
            }
        return reactor1;}).collect(Collectors.toList()));
    }



    public ArrayList<Reactor> getReactorList(){
        return reactorArrayList;
    }
}
