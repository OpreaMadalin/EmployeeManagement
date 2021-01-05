package com.company.service;

import com.company.util.Constants;

import java.util.Scanner;

public class AuthServiceImpl implements AuthService {
    @Override
    public void authenticate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");

        String userName = scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();

        if (Constants.USERNAME.equals(userName) && Constants.PASSWORD.equals(password)) {
            System.out.println("You are authenticated!");
        } else {
            System.out.println("Incorrect credentials!");
            authenticate();
        }

    }
}
