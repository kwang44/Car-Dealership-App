package com.dealership.db;

public class JDBCFactory {

    public static GenericDao daoFactory(Class c) {
        switch (c.getName()){
            case "com.dealership.models.Customer":
                return CustomerJDBC.getInstance();
            case "com.dealership.models.Employee":
                return EmployeeJDBC.getInstance();
            case "com.dealership.models.DealershipCar":
                return DealershipCarJDBC.getInstance();
            case "com.dealership.models.Offer":
                return OfferJDBC.getInstance();
            case "com.dealership.models.CustomerCar":
                return CustomerCarJDBC.getInstance();
            case "com.dealership.models.Payment":
                return PaymentJDBC.getInstance();
        }
        return null;
    }
}
