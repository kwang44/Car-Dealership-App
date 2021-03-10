package com.dealership.models;

public class CustomerCar extends Car{
    //customer is the owner of the car
    private String customerUsername;

    public CustomerCar(String year, String make, String model, String vin, String customerUsername) {
        super(year, make, model, vin);
        this.customerUsername = customerUsername;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    @Override
    public String toString() {
        return "CustomerCar{" +
                "year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", vin='" + vin + '\'' +
                ", customerUsername='" + customerUsername + '\'' +
                '}';
    }
}
