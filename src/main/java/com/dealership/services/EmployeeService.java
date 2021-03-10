package com.dealership.services;

import com.dealership.db.JDBCFactory;
import com.dealership.models.*;

import java.math.BigDecimal;

public class EmployeeService {
    Employee employee;

    public boolean isLoggedIn() {
        return employee!=null;
    }

    public boolean login(String username, String password) {
        Employee c = (Employee) JDBCFactory.daoFactory(Employee.class).get(username);
        if (c != null && password.equals(c.getPassword())) {
            employee = c;
            return true;
        }
        return false;
    }

    public boolean addCar(DealershipCar car){
        int i = JDBCFactory.daoFactory(DealershipCar.class).save(car);
        return i == 1;
    }

    public Offer[] getAllOffers() {
        Offer[] offers = (Offer[]) JDBCFactory.daoFactory(Offer.class).getAll();
        return offers;
    }

    public void rejectOffer(Offer offer) {
        JDBCFactory.daoFactory(Offer.class).remove(offer);
    }

    public void acceptOffer(Offer offer) {
        Payment payment = new Payment(offer.getUsername(),
                offer.getVin(),
                offer.getOfferAmount(),
                new BigDecimal("0.00"),
                offer.getOfferAmount().divide(BigDecimal.valueOf(offer.getPayments())),
                offer.getPayments());
        JDBCFactory.daoFactory(Payment.class).save(payment);

        offer.setUsername(null);
        JDBCFactory.daoFactory(Offer.class).remove(offer);
        JDBCFactory.daoFactory(DealershipCar.class).remove(offer.getVin());

    }

    public Payment[] getPayments() {
        return (Payment[]) JDBCFactory.daoFactory(Payment.class).getAll();
    }

    public DealershipCar[] getAllCar() {
        DealershipCar[] dc = (DealershipCar[]) JDBCFactory.daoFactory(DealershipCar.class).getAll();
        return dc;
    }
    public void removeCar(DealershipCar dealershipCar) {
        JDBCFactory.daoFactory(DealershipCar.class).remove(dealershipCar.getVin());
        JDBCFactory.daoFactory(Offer.class).remove(new Offer(null, dealershipCar.getVin(), null, 0));
    }
}
