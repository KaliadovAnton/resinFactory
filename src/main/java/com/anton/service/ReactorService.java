package com.anton.service;

import com.anton.model.Reactor;
import com.anton.repository.ReactorRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReactorService {
    private final ReactorRepository repository = new ReactorRepository();
    private static ReactorService service;

    private ReactorService(){
    }

    public void toggleMaintenance(Reactor reactor) {
        repository.toggleReactorUnderMaintenance(reactor);
    }

    public void addReactor(String name, String type){
        repository.addReactor(name, type);
    }

    public ArrayList<Reactor> getReactors(){
        return repository.getReactorList();
    }

    public static ReactorService getSingleReactorService(){
        if (service==null){
            service = new ReactorService();
            System.out.println("hello");
        }
        return service;
    }

    public void serializeReactorList(ArrayList<Reactor> reactors){
        repository.serializeReactors(reactors);
    }

    public ArrayList<Reactor> listOfSpecificReactors(String resinType) {
        return (ArrayList<Reactor>) getReactors().stream().filter(reactor -> reactor.getType().equals(resinType)).collect(Collectors.toList());
    }
}
