package com.weather.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ApiConfig {

    private static final String ENV_FILE = ".env";
    private static final String KEY_NAME = "VISUALCROSSING_API_KEY";

    // Carrega a chave na ordem:
    // 1. Variavel de ambiente do sistema (export VISUALCROSSING_API_KEY=...)
    // 2. Arquivo .env na raiz do projeto (VISUALCROSSING_API_KEY=suachave)
    public static String getApiKey() {
        String fromEnv = System.getenv(KEY_NAME);
        if (fromEnv != null && !fromEnv.isEmpty()) {
            return fromEnv;
        }

        File envFile = new File(ENV_FILE);
        if (envFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(envFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith(KEY_NAME + "=")) {
                        return line.substring(KEY_NAME.length() + 1).trim();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Erro ao ler o arquivo .env: " + e.getMessage());
            }
        }

        throw new RuntimeException(
            "Chave da API nao encontrada.\n" +
            "Crie um arquivo .env na raiz do projeto com o conteudo:\n" +
            KEY_NAME + "=sua_chave_aqui"
        );
    }
}
