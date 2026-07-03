package com.example.service;

import java.io.File;
import java.io.IOException;

import com.example.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PersistenciaService {

    private static final String ARQUIVO = "usuario.json";

    private ObjectMapper mapper;

    public PersistenciaService() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void salvarUsuario(Usuario usuario) {
        try {
            mapper.writeValue(new File(ARQUIVO), usuario);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public Usuario carregarUsuario() {
        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return null;
        }

        try {
            Usuario usuario = mapper.readValue(arquivo, Usuario.class);

            if (usuario.getNome() == null || usuario.getNome().isBlank()) {
                usuario.setNome("Usuário");
            }

            if (usuario.getFavoritos() == null) {
                usuario.setFavoritos(new java.util.ArrayList<>());
            }

            if (usuario.getAssistidas() == null) {
                usuario.setAssistidas(new java.util.ArrayList<>());
            }

            if (usuario.getDesejoAssistir() == null) {
                usuario.setDesejoAssistir(new java.util.ArrayList<>());
            }

            return usuario;

        } catch (IOException e) {
            System.out.println("Erro ao carregar usuário. Criando novo usuário.");
            return null;
        }
    }
}