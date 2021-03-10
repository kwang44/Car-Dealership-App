package com.dealership.models;

import java.math.BigDecimal;

public class Offer {
    private String username;
    private String vin;
    private BigDecimal offerAmount;
    private int payments;

    public Offer(String username, String vin, BigDecimal offerAmount, int payments) {
        this.username = username;
        this.vin = vin;
        this.offerAmount = offerAmount;
        this.payments = payments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public BigDecimal getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(BigDecimal offerAmount) {
        this.offerAmount = offerAmount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "user: " + username + "\tVin: " + vin + "\tofferAmount: " + offerAmount + "\tpayments: " + payments;
    }
}
