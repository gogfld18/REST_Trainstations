package com.example.ex07_trainmanagment.bl;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

public class TrainDatabaseStatic {
    private static TrainDatabaseStatic theInstance;
    private List<Train> trainList;

    private TrainDatabaseStatic(){
        trainList = new ArrayList<>();

        trainList.add(new Train(0, new ArrayList<String>(Arrays.asList("Kaindorf", "Lebring", "Wildon", "Werndorf","Feldkirchen","Don Bosco", "Graz")),"S-Bahn"));
        trainList.add(new Train(1,new ArrayList<>(Arrays.asList("Kaindorf","Don Bosco","Graz")),"REX"));
        trainList.add(new Train(2, new ArrayList<>(Arrays.asList("Leibnitz","Graz")),"Intercity"));
    }

    public static synchronized TrainDatabaseStatic getInstance(){
        if(theInstance == null){
            theInstance = new TrainDatabaseStatic();
        }
        return theInstance;
    }

    public List<Train> getTrainList(){
        return trainList;
    }

    public void addStation(int id, String station) {
        Optional<Train> opTr = findTrainById(id);

        if(opTr.isPresent()){

            opTr.get().getStations().add(station);
        }else {
            throw new NoSuchElementException();
        }
    }

    public void addTrain(Train t){
        Optional<Train> opTr = findTrainById(t.getId());

        if(!opTr.isPresent()){
            trainList.add(t);
        }
        else{
            throw new KeyAlreadyExistsException();
        }
    }

    public Train getById(int id){
        Optional<Train> opTr = findTrainById(id);

        if(opTr.isPresent()){
            return opTr.get();
        }
        throw new NoSuchElementException();
    }

    private Optional<Train> findTrainById(int id){
       return trainList.stream().filter(t -> t.getId() == id).findFirst();
    }
}
