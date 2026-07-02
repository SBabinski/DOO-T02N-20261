package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Usuario;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonManager {
    private static final Path PASTA_DADOS = Path.of("dados");
    private static final Path ARQUIVO_USUARIO = PASTA_DADOS.resolve("usuario.json");

    private final Gson gson;

    public JsonManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public Usuario carregarUsuario() throws IOException {
        if (!Files.exists(ARQUIVO_USUARIO)) {
            return null;
        }

        try (Reader reader = Files.newBufferedReader(ARQUIVO_USUARIO)) {
            Usuario usuario = gson.fromJson(reader, Usuario.class);
            return usuario == null ? null : usuario;
        }
    }

    public void salvarUsuario(Usuario usuario) throws IOException {
        Files.createDirectories(PASTA_DADOS);

        try (Writer writer = Files.newBufferedWriter(ARQUIVO_USUARIO)) {
            gson.toJson(usuario, writer);
        }
    }
}
