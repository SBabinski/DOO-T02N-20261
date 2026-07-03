package com.pedrohhm.controller;

import com.pedrohhm.exception.InvalidUsernameException;
import com.pedrohhm.model.UserData;
import com.pedrohhm.service.PersistenceService;

public class UserController {

    private final UserData userData;

    // CONSTRUCTOR \\

    public UserController(UserData userData) {

        this.userData = userData;
    }

    // REGISTER \\

    public void registerUser(String username) {

        validateUsername(username);

        userData.setUsername(username.trim());

        PersistenceService.saveUserData(userData);
    }

    public boolean isRegistered() {

        return userData.getUsername() != null
                && !userData.getUsername().isBlank();
    }

    public void save() {

        PersistenceService.saveUserData(userData);
    }

    // USERNAME \\
    
    public void changeUsername(String newUsername) {

        validateUsername(newUsername);

        userData.setUsername(newUsername.trim());

        PersistenceService.saveUserData(userData);
    }

    private void validateUsername(String username) {

        if (username == null) {
            throw new InvalidUsernameException(
                "Digite um nome de usuário."
            );
        }

        username = username.trim();

        if (username.isEmpty()) {
            throw new InvalidUsernameException(
                "Digite um nome de usuário."
            );
        }

        if (username.length() > 30) {
            throw new InvalidUsernameException(
                "O nome de usuário deve ter no máximo 30 caracteres."
            );
        }
    }

    // GETTERS \\

    public String getUsername() {

        return userData.getUsername();
    }

    public UserData getUserData() {

        return userData;
    }
}

