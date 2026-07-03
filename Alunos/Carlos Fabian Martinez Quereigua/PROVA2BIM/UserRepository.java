package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.User;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class UserRepository {

    private static final String ARQUIVO = "usuario.json";

    private final ObjectMapper objectMapper;

    public UserRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void salvarUsuario(User usuario) {

        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(ARQUIVO), usuario);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar usuário.", e);
        }
    }

    public User carregarUsuario() {

        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return null;
        }

        try {
            return objectMapper.readValue(arquivo, User.class);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar usuário.", e);
        }
    }

    public void excluirUsuario(){
        File arquivo = new File(ARQUIVO);

        if (arquivo.delete()) {
            System.out.println("Arquivo apagado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Não foi possivel fazer a exclusão, verifique se esse arquivo existe.",
                    "Aviso",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}