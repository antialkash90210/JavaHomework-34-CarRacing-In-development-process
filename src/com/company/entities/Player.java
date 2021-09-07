package com.company.entities;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Car> cars;
    private int currentCarId;

    public Player(String name) {
        this.name = name;
        cars = new ArrayList<>();
        currentCarId = 0;
    }

    public void addCar(Car car) {
        currentCarId++;
        car.setId(currentCarId);

        cars.add(car);
    }

    public ArrayList<Car> getCar() {
        return cars;
    }

    public Car getCarById(int id) throws Exception {

        for (int i = 0; i < cars.size(); i++) {
            Car currentCar = cars.get(i);

            if (currentCar.getId() == id) {
                return currentCar;
            }
        }

        throw new Exception("user not found");
    }

    public String getPlayerName() {
        return name;
    }

    public ArrayList<Integer> getAllCarsIds(){
        ArrayList<Integer> ids = new ArrayList<>();

        for (int i = 0; i < cars.size(); i++) {
            ids.add(cars.get(i).getId());
        }

        return ids;
    }
}