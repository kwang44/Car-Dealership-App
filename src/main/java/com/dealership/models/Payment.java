package com.dealership.models;

import java.math.BigDecimal;

public class Payment {
    private String username;
    private String vin;
    private BigDecimal totalAmount;
    private BigDecimal paymentMade;
    private BigDecimal monthlyPaymentAmount;
    private int monthlyPaymentLeft;

    public Payment(String username, String vin, BigDecimal totalAmount, BigDecimal paymentMade, BigDecimal monthlyPaymentAmount, int monthlyPaymentLeft) {
        this.username = username;
        this.vin = vin;
        this.totalAmount = totalAmount;
        this.paymentMade = paymentMade;
        this.monthlyPaymentAmount = monthlyPaymentAmount;
        this.monthlyPaymentLeft = monthlyPaymentLeft;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaymentMade() {
        return paymentMade;
    }

    public void setPaymentMade(BigDecimal paymentMade) {
        this.paymentMade = paymentMade;
    }

    public BigDecimal getMonthlyPaymentAmount() {
        return monthlyPaymentAmount;
    }

    public void setMonthlyPaymentAmount(BigDecimal monthlyPaymentAmount) {
        this.monthlyPaymentAmount = monthlyPaymentAmount;
    }

    public int getMonthlyPaymentLeft() {
        return monthlyPaymentLeft;
    }

    public void setMonthlyPaymentLeft(int monthlyPaymentLeft) {
        this.monthlyPaymentLeft = monthlyPaymentLeft;
    }

    @Override
    public String toString() {
        return "Amount paid: " + paymentMade + "\tTotal amount: " + totalAmount + "\tMonthly Payment Amount: " + monthlyPaymentAmount + "\tMonthly Payment Left: " + monthlyPaymentLeft;
    }
}
