package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.UserData;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Responsável por salvar e carregar os dados do usuário em formato JSON.
 * O arquivo é gravado no diretório de execução do programa.
 */
public class PersistenceService {

    private static final String NOME_ARQUIVO = "userData.json";
    private final Gson gson;

    public PersistenceService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Salva os dados do usuário no arquivo JSON.
     *
     * @param userData Dados a serem salvos.
     * @throws IOException Se não for possível escrever no arquivo.
     */
    public void salvar(UserData userData) throws IOException {
        try (Writer writer = new OutputStreamWriter(
                new FileOutputStream(NOME_ARQUIVO), StandardCharsets.UTF_8)) {
            gson.toJson(userData, writer);
        }
    }

    /**
     * Carrega os dados do arquivo JSON.
     *
     * @return O UserData carregado, ou {@code null} se o arquivo não existir.
     * @throws IOException Se o arquivo existir mas não puder ser lido/parseado.
     */
    public UserData carregar() throws IOException {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            return null;
        }
        try (Reader reader = new InputStreamReader(
                new FileInputStream(arquivo), StandardCharsets.UTF_8)) {
            UserData ud = gson.fromJson(reader, UserData.class);
            return ud;
        }
    }

    /** Retorna o caminho absoluto do arquivo de dados. */
    public String getCaminhoArquivo() {
        return new File(NOME_ARQUIVO).getAbsolutePath();
    }
}
