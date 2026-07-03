package com.pedrohhm;

import com.pedrohhm.controller.TvSeriesController;
import com.pedrohhm.controller.UserController;
import com.pedrohhm.model.TvSeriesLists;
import com.pedrohhm.model.UserData;
import com.pedrohhm.service.PersistenceService;
import com.pedrohhm.service.TvMazeApiService;
import com.pedrohhm.view.MainInterface;
import com.pedrohhm.view.RegistrationInterface;

public class Main {
    public static void main(String[] args) {
       
        UserData userData = PersistenceService.loadUserData();

        UserController userController = new UserController(userData);

        TvMazeApiService apiService = new TvMazeApiService();

        TvSeriesLists tvSeriesLists = new TvSeriesLists(userData);
        
        TvSeriesController tvSeriesController = new TvSeriesController(apiService, tvSeriesLists, userController);

        if (!userController.isRegistered()) {

                new RegistrationInterface(
                        userController,
                        tvSeriesController)
                        .show();

                return;
        }

        new MainInterface(
            userController,
            tvSeriesController)
            .show();
    }
}