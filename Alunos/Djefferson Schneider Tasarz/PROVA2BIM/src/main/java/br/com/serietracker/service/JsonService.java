package br.com.serietracker.service;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serietracker.model.Usuario;

public class JsonService {

    private static final String PASTA =
        "usuarios";

    private final ObjectMapper mapper =
            new ObjectMapper();

            public void salvar(Usuario usuario) {

                try {
            
                    File pasta =
                            new File(PASTA);
            
                    if (!pasta.exists()) {
            
                        pasta.mkdir();
                    }
            
                    File arquivo =
                            new File(
                                    pasta,
                                    usuario.getApelido() + ".json"
                            );
            
                    mapper.writerWithDefaultPrettyPrinter()
                            .writeValue(
                                    arquivo,
                                    usuario
                            );
            
                } catch (Exception e) {
            
                    e.printStackTrace();
                }
            }

            public Usuario carregar(String nome) {

                try {
            
                    File arquivo =
                            new File(
                                    PASTA + "/" + nome + ".json"
                            );
            
                    if (arquivo.exists()) {
            
                        return mapper.readValue(
                                arquivo,
                                Usuario.class
                        );
                    }
            
                } catch (Exception e) {
            
                    e.printStackTrace();
                }
            
                return null;
            }
}