package com.tvtracker.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tvtracker.model.UserData;

import java.io.*;
import java.nio.file.*;

/**
 * Serviço de persistência responsável por salvar e carregar os dados do usuário
 * em formato JSON no diretório local da aplicação.
 */
public class DataPersistenceService {

    // Arquivo de dados salvo na pasta do usuário do sistema operacional
    private static final String DATA_DIR = System.getProperty("user.home") + File.separator + ".tvtracker";
    private static final String DATA_FILE = DATA_DIR + File.separator + "userdata.json";

    // Gson configurado com pretty-print para o JSON ficar legível se inspecionado
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Salva os dados do usuário em disco no formato JSON.
     * Cria o diretório automaticamente se não existir.
     */
    public void save(UserData userData) throws IOException {
        // Garante que o diretório de dados existe antes de tentar salvar
        Path dir = Paths.get(DATA_DIR);
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        String json = gson.toJson(userData);

        // Usa UTF-8 explicitamente para suportar caracteres especiais nos nomes
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(DATA_FILE), "UTF-8"))) {
            writer.write(json);
        }
    }

    /**
     * Carrega os dados do usuário do arquivo JSON em disco.
     * Retorna um UserData vazio e novo se o arquivo não existir (primeira execução).
     */
    public UserData load() throws IOException {
        Path filePath = Paths.get(DATA_FILE);

        // Primeira execução: arquivo ainda não existe, retorna dados padrão
        if (!Files.exists(filePath)) {
            return new UserData();
        }

        try (Reader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(DATA_FILE), "UTF-8"))) {
            UserData data = gson.fromJson(reader, UserData.class);
            // Garante que os campos não fiquem nulos após desserialização
            return data != null ? data : new UserData();
        }
    }

    // Informa se já existe um arquivo de dados salvo (para detectar usuário novo)
    public boolean dataFileExists() {
        return Files.exists(Paths.get(DATA_FILE));
    }

    // Apaga o arquivo de dados do disco para permitir reset completo
    public void deleteData() throws IOException {
        Files.deleteIfExists(Paths.get(DATA_FILE));
    }
}
