package com.company.entities;

import com.company.entities.supportings.Point;
import com.company.util.SettingsHelper;

public class Car {
    private int id;//Номер автомобиля

    private Point point;
    private char skin;

    private String manufacturer;//Марка
    private String model;//Модель
    private int velocity;//Ускорение в м/с2
    private int maxSpeed;//Максимальная скорость
    private int currentSpeed = 0;//Текущая скорость
    private int runDistance = 0;//Пройденная дистанция в заезде
    private int runTime = 0;//Время заезда
    private String driver;//Гонщик


    public Car() {
        SettingsHelper.loadFromFile();
    }

    //Конструктор класса Car с входными параметрами элемента класса Car
    public Car(Car car) {
        this.id = car.id;
        this.manufacturer = car.manufacturer;
        this.model = car.model;
        this.velocity = car.velocity;
        this.maxSpeed = car.maxSpeed;
        this.driver = car.driver;
        this.currentSpeed = 0;
        this.runDistance = 0;
        this.runTime = 0;
    }

    //Конструктор класса Car с входными параметрами
    public Car(int id, String manufacturer, String model, int velocity, int maxSpeed, String driver) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.velocity = velocity;
        this.maxSpeed = maxSpeed;
        this.driver = driver;
    }


    //region Getter

    public int getId() {
        return this.id;
    }

    public String getDriver() {
        return this.driver;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public String getModel() {
        return this.model;
    }

    public int getVelocity() {
        return this.velocity;
    }

    public int getCurrentSpeed() {
        return this.currentSpeed;
    }

    public int getRunDistance() {
        return this.runDistance;
    }

    public int getRunTime() {
        return this.runTime;
    }

    public Point getPoint() {
        return point;
    }

    public char getSkin() {
        return skin;
    }

    //endregion

    //region Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void setRunDistance(int runDistance) {
        this.runDistance = runDistance;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    //endregion

}