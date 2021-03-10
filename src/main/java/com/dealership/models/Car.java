package com.dealership.models;

public class Car {
    // vin should be unique for each car
    protected String year;
    protected String make;
    protected String model;
    protected String vin;

    public Car(String year, String make, String model, String vin) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.vin = vin;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
            return "Year: " + year + "\tMake: " + make + "\tModel: " + model + "\tVin: " + vin;
    }
}