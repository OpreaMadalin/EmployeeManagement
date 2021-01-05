package com.company;

import com.company.service.AuthService;
import com.company.service.AuthServiceImpl;
import com.company.service.UserInteractionService;
import com.company.service.UserInteractionServiceImpl;


public class Main {

    public static void main(String[] args) {


        AuthService authService = new AuthServiceImpl();
        authService.authenticate();

        UserInteractionService userInteractionService = new UserInteractionServiceImpl();
        userInteractionService.initInteraction();


    }
}
