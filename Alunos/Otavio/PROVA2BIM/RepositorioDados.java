import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RepositorioDados {

    private final Path arquivo;

    public RepositorioDados(String caminho) {
        this.arquivo = Paths.get(caminho);
    }

    public boolean existe() {
        return Files.exists(arquivo);
    }

    public void gravar(String conteudo) throws IOException {
        Files.write(arquivo, conteudo.getBytes(StandardCharsets.UTF_8));
    }

    public String ler() throws IOException {
        return new String(Files.readAllBytes(arquivo), StandardCharsets.UTF_8);
    }

    public String getCaminho() {
        return arquivo.toAbsolutePath().toString();
    }
}