import com.fasterxml.jackson.databind.JsonNode;
import java.util.LinkedHashMap;
import java.util.Map;

public class Usuario {

    private String nome;

    public Usuario(String nome) {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "Convidado";
        } else {
            this.nome = nome.trim();
        }
    }

    public Map<String, Object> toMapa() {
        Map<String, Object> mapa = new LinkedHashMap<>();
        mapa.put("nome", nome);
        return mapa;
    }

    public static Usuario fromJson(JsonNode no) {
        if (no == null || no.isMissingNode() || no.isNull()) {
            return new Usuario("Convidado");
        }
        JsonNode nomeNo = no.path("nome");
        if (nomeNo.isMissingNode() || nomeNo.isNull()) {
            return new Usuario("Convidado");
        }
        return new Usuario(nomeNo.asText("Convidado"));
    }

    @Override
    public String toString() {
        return nome;
    }
}