package com.dealership.services;

import com.dealership.db.JDBCFactory;
import com.dealership.models.*;

import java.math.BigDecimal;

public class CustomerService {
    Customer customer;

    public boolean isLoggedIn() {
        return customer!=null;
    }

    public boolean createAccount(String username, String password) {
        int i = JDBCFactory.daoFactory(Customer.class).save(new Customer(username, password));
        return i == 1;
    }

    public boolean login(String username, String password) {
        Customer c = (Customer) JDBCFactory.daoFactory(Customer.class).get(username);
        if (c != null && password.equals(c.getPassword())) {
            customer = c;
            return true;
        }
        return false;
    }

    public DealershipCar[] getAllCar() {
        DealershipCar[] dc = (DealershipCar[]) JDBCFactory.daoFactory(DealershipCar.class).getAll();
        return dc;
    }

    public boolean makeAnOffer(DealershipCar dc, BigDecimal amount) {
        Offer offer = new Offer(customer.getUsername(), dc.getVin(), amount, 24);
        int i = JDBCFactory.daoFactory(Offer.class).insertOrUpdate(offer);
        return i == 1;
    }

    public Payment[] getAllPayment() {
        Payment payment = new Payment(customer.getUsername(), null, null, null, null, 0);
        Payment[] payments = (Payment[]) JDBCFactory.daoFactory(Payment.class).getAll(payment);
        return payments;
    }

    public CustomerCar[] getAllMyCar() {
        CustomerCar[] customerCars = (CustomerCar[]) JDBCFactory.daoFactory(CustomerCar.class).getAll(customer);
        return customerCars;
    }





}
