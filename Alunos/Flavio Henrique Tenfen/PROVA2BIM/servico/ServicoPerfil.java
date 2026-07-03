package servico;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import modelo.PerfilUsuario;
import util.ParserJson;


public class ServicoPerfil {

    private static final String PASTA_DADOS  = System.getProperty("user.home")
                                               + File.separator + ".tvtracker";
    private static final String ARQUIVO_JSON = PASTA_DADOS + File.separator + "perfil.json";

    public PerfilUsuario carregar() {
        Path caminho = Paths.get(ARQUIVO_JSON);
        if (!Files.exists(caminho)) {
            return new PerfilUsuario();
        }
        try {
            String json = new String(Files.readAllBytes(caminho), StandardCharsets.UTF_8);
            return ParserJson.parsePerfil(json);
        } catch (Exception e) {
            System.err.println("Aviso: não foi possível carregar o perfil — " + e.getMessage());
            return new PerfilUsuario();
        }
    }

    public void salvar(PerfilUsuario perfil) throws IOException {
        Files.createDirectories(Paths.get(PASTA_DADOS));
        String json = ParserJson.serializarPerfil(perfil);
        Files.write(Paths.get(ARQUIVO_JSON),
                json.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}
