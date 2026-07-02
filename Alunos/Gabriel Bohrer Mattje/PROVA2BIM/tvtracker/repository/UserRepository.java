package com.tvtracker.repository;

import com.tvtracker.model.UserProfile;
import com.tvtracker.util.JsonSerializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Repositório responsável por persistir e carregar o perfil do usuário em JSON.
 */
public class UserRepository {

    private static final String DATA_DIR = System.getProperty("user.home") + "/.tvtracker";
    private static final String DATA_FILE = DATA_DIR + "/userdata.json";

    /**
     * Salva o perfil do usuário em disco.
     */
    public void save(UserProfile profile) throws IOException {
        Path dir = Paths.get(DATA_DIR);
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        String json = JsonSerializer.serializeUserProfile(profile);
        Path filePath = Paths.get(DATA_FILE);
        Files.writeString(filePath, json, StandardCharsets.UTF_8);
    }

    /**
     * Carrega o perfil do usuário do disco.
     * Retorna um perfil vazio se não encontrar o arquivo.
     */
    public UserProfile load() {
        Path filePath = Paths.get(DATA_FILE);
        if (!Files.exists(filePath)) {
            return new UserProfile();
        }

        try {
            String json = Files.readString(filePath, StandardCharsets.UTF_8);
            return JsonSerializer.deserializeUserProfile(json);
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do usuário: " + e.getMessage());
            return new UserProfile();
        }
    }

    /**
     * Verifica se já existe um arquivo de dados salvo.
     */
    public boolean hasExistingData() {
        Path filePath = Paths.get(DATA_FILE);
        return Files.exists(filePath);
    }

    /**
     * Retorna o caminho do arquivo de dados.
     */
    public String getDataFilePath() {
        return DATA_FILE;
    }
}
