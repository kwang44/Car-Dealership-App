package com.dealership.models.car;

public class Car {
    // vin should be unique for each car
    String year;
    String make;
    String model;
    String vin;

    public Car(String year, String make, String model, String vin) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Car{" +
                "year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
