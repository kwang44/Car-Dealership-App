package com.dealership.ui;

import com.dealership.models.DealershipCar;
import com.dealership.models.Offer;
import com.dealership.models.Payment;
import com.dealership.services.CustomerService;
import com.dealership.services.EmployeeService;

import java.math.BigDecimal;
import java.util.Scanner;

public class EmployeeMenu {
    static Scanner scanner = new Scanner(System.in);
    static EmployeeService employeeService = new EmployeeService();

    public static void main(String[] args) {
        String str = "";

        do {
            if (employeeService.isLoggedIn()) {
                System.out.println("What would you like to do?\n[1]Add car\n[2]Look at offers\n[3]Remove car from lot\n[4]See payments\n[q]exit");
                str = scanner.nextLine();
                switch (str) {
                    case "1":
                        addCar();
                        break;
                    case "2":
                        seeOffer();
                        break;
                    case "3":
                        removeCar();
                        break;
                    case "4":
                        seePayments();
                        break;
                }
            }
            else {
                System.out.println("Enter username:");
                String str1 = scanner.nextLine();
                System.out.println("Enter password:");
                String str2 = scanner.nextLine();
                employeeService.login(str1, str2);
            }
        }
        while (!str.equals("q"));
    }

    public static void addCar() {
        System.out.println("Enter year");
        String year = scanner.nextLine();
        System.out.println("Enter make");
        String make = scanner.nextLine();
        System.out.println("Enter model");
        String model = scanner.nextLine();
        System.out.println("Enter vin");
        String vin = scanner.nextLine();
        System.out.println("Enter msrp");
        String msrp = scanner.nextLine();

        DealershipCar car = new DealershipCar(year,make,model,vin,new BigDecimal(msrp));

        employeeService.addCar(car);
    }

    public static void seeOffer() {
        Offer[] offers = employeeService.getAllOffers();
        if (offers == null) {
            System.out.println("No offers right now");
        }
        else {
            for (int i = 0; i < offers.length; i++) {
                System.out.println("[" + i + "] " + offers[i]);
            }
            System.out.println("Enter a offer number to take an action");
            String str = scanner.nextLine();
            try {
                int i = Integer.parseInt(str);
                if (i >= 0 && i < offers.length) {
                    System.out.println("[1]Accept\n[2]Decline\n[other key]quit");
                    str = scanner.nextLine();
                    switch (str) {
                        case "1":
                            employeeService.acceptOffer(offers[i]);
                        case "2":
                            employeeService.rejectOffer(offers[i]);
                    }
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
    public static void seePayments() {
        Payment[] payments = employeeService.getPayments();
        if (payments == null) {
            System.out.println("No payment to see");
        }
        else {
            for(Payment payment : payments) {
                System.out.println(payment);
            }
        }
    }

    public static void removeCar() {
        DealershipCar[] dealershipCars = employeeService.getAllCar();
        if (dealershipCars == null) {
            System.out.println("No car to see");
            return;
        }

        for (int i = 0; i < dealershipCars.length; i++) {
            System.out.println("[" + i + "] " + dealershipCars[i]);
        }
        System.out.println("Enter a car number to remove");
        String str = scanner.nextLine();

        try {
            int i = Integer.parseInt(str);
            if (i >= 0 && i < dealershipCars.length) {
                employeeService.removeCar(dealershipCars[i]);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

}
