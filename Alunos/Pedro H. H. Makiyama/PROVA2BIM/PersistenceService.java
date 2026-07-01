package com.pedrohhm.service;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pedrohhm.exception.PersistenceException;
import com.pedrohhm.model.UserData;

public class PersistenceService {

    private static final ObjectMapper objectMapper =
        new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private static final File USER_DATA_FILE = new File("userdata.json");

    public static UserData loadUserData() {

        try {

            if (!USER_DATA_FILE.exists()) {
                return new UserData();
            }

            return objectMapper.readValue(USER_DATA_FILE, UserData.class);

        } catch (IOException e) {

            throw new PersistenceException(
                "Não foi possível carregar os dados do usuário.",
                e
            );
        }
    }

    public static void saveUserData(UserData userData) {

        try {

            objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValue(USER_DATA_FILE, userData);

        } catch (IOException e) {

            throw new PersistenceException(
                "Não foi possível salvar os dados do usuário.",
                e
            );
        }
    }
}