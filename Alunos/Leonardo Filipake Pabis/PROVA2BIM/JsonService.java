package app.service;

import app.model.Usuario;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class JsonService {

    private static final String PASTA_DADOS =
            System.getProperty("user.dir") + File.separator + "dados";

    private static final String ARQUIVO =
            PASTA_DADOS + File.separator + "usuario.json";

    private final ObjectMapper mapper;

    public JsonService() {
        mapper = new ObjectMapper();
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false
        );
    }

    public void salvar(Usuario usuario) {

        if (usuario == null) {
            return;
        }

        try {

            Path pasta = Path.of(PASTA_DADOS);
            Path arquivo = Path.of(ARQUIVO);
            Path temporario = Path.of(ARQUIVO + ".tmp");

            Files.createDirectories(pasta);

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(
                            temporario.toFile(),
                            usuario
                    );

            Files.move(
                    temporario,
                    arquivo,
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (Exception e) {

            System.out.println(
                    "Erro ao salvar o JSON em " + ARQUIVO + ": " + e.getMessage()
            );
        }
    }

    public Usuario carregar() {

        try {

            File arquivo =
                    new File(ARQUIVO);

            if (arquivo.exists() && arquivo.isFile()) {

                return mapper.readValue(
                        arquivo,
                        Usuario.class
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro ao carregar o JSON em " + ARQUIVO + ": " + e.getMessage()
            );
        }

        return null;
    }

    public String getCaminhoArquivo() {
        return ARQUIVO;
    }
}
