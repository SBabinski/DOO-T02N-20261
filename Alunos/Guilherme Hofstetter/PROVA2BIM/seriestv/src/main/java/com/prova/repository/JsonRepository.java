package com.prova.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prova.model.BibliotecaUsuario;

import java.io.File;
import java.io.IOException;

public class JsonRepository {

    private final File arquivo = new File("dados_usuario.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public void salvar(BibliotecaUsuario biblioteca) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, biblioteca);
    }

    public BibliotecaUsuario carregar() throws IOException {
        if (!arquivo.exists()) {
            return null;
        }

        return mapper.readValue(arquivo, BibliotecaUsuario.class);
    }

    public boolean existeArquivo() {
        return arquivo.exists();
    }
}