package com.dealership.models;

import java.math.BigDecimal;

public class DealershipCar extends Car{

    BigDecimal msrp;

    public DealershipCar(String year, String make, String model, String vin, BigDecimal msrp) {
        super(year, make, model, vin);
        this.msrp = msrp;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }

    @Override
    public String toString() {
        return "Year: " + year + "\tMake: " + make + "\tModel: " + model + "\tVin: " + vin + "\tMSRP: " + msrp;
    }
}
