package com.dealership.ui;

import com.dealership.models.Customer;
import com.dealership.models.CustomerCar;
import com.dealership.models.DealershipCar;
import com.dealership.models.Payment;
import com.dealership.services.CustomerService;

import java.math.BigDecimal;
import java.util.Scanner;

public class CustomerMenu {
    static Scanner scanner = new Scanner(System.in);
    static CustomerService customerService = new CustomerService();

    public static void main(String[] args) {
        String str = "";

        do {
            if (customerService.isLoggedIn()) {
                System.out.println("What would you like to do?\n[1]See all cars for sale\n[2]Look at my payments\n[3]Look at my car\n[q]exit");
                str = scanner.nextLine();
                switch (str) {
                    case "1":
                        seeAllDealerCar();
                        break;
                    case "2":
                        seeMyPayments();
                        break;
                    case "3":
                        seeMyCars();
                        break;
                }
            }
            else {
                System.out.println("What would you like to do?\n[1]Login\n[2]Create account\n[q]exit");
                str = scanner.nextLine();
                switch (str) {
                    case "1":
                        login();
                        break;
                    case "2":
                        createAccount();
                        break;
                }
            }
        }
        while (!str.equals("q"));
    }

    public static void login() {
        System.out.println("You are at login menu");
        System.out.println("Enter username");
        String str1 = scanner.nextLine();
        System.out.println("Enter password");
        String str2 = scanner.nextLine();

        if (customerService.login(str1, str2)) {
            System.out.println("You are logged in.");
        }
        else {
            System.out.println("Username or password wrong.");
        }
    }

    public static void createAccount() {
        System.out.println("You are at create account menu");
        System.out.println("Enter username");
        String str1 = scanner.nextLine();
        System.out.println("Enter password");
        String str2 = scanner.nextLine();

        if (customerService.createAccount(str1, str2)) {
            System.out.println("Your account has been created");
        }
        else {
            System.out.println("Something went wrong");
        }
    }

    public static void seeAllDealerCar() {
        DealershipCar[] dc = customerService.getAllCar();

        for (int i = 0; i < dc.length; i++) {
            System.out.println("[" + i + "] " + dc[i]);
        }
        System.out.println("Enter a car number to make an offer (or other key to exit this menu)");
        String str = scanner.nextLine();

        try {
            int i = Integer.parseInt(str);
            if (i >= 0 && i < dc.length) {
                System.out.println("How much would you like to offer?");
                str = scanner.nextLine();
                BigDecimal bd = new BigDecimal(str);
                if (customerService.makeAnOffer(dc[i], bd)) {
                    System.out.println("Offer submitted");
                }
                else {
                    System.out.println("Something Went Wrong");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("No offer made.");
        }
    }

    public static void seeMyPayments() {
        Payment[] payments = customerService.getAllPayment();
        if (payments == null) {
            System.out.println("No payment on file");
        }
        else {
            for (Payment payment : payments) {
                System.out.println(payment);
            }
        }
    }

    public static void seeMyCars() {
        CustomerCar[] customerCars = customerService.getAllMyCar();
        if (customerCars == null) {
            System.out.println("No car on file");
        }
        else {
            for (CustomerCar customerCar : customerCars) {
                System.out.println(customerCar);
            }
        }
    }

}
