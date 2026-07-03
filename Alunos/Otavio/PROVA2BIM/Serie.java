import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Serie {

    private final int id;
    private final String nome;
    private final String idioma;
    private final List<String> generos;
    private final Double nota;
    private final EstadoSerie estado;
    private final String statusOriginal;
    private final String dataEstreia;
    private final String dataTermino;
    private final String emissora;

    public Serie(int id, String nome, String idioma, List<String> generos, Double nota,
                 EstadoSerie estado, String statusOriginal, String dataEstreia,
                 String dataTermino, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = (generos == null) ? new ArrayList<>() : new ArrayList<>(generos);
        this.nota = nota;
        this.estado = (estado == null) ? EstadoSerie.DESCONHECIDO : estado;
        this.statusOriginal = (statusOriginal == null) ? "" : statusOriginal;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = (emissora == null) ? "Desconhecida" : emissora;
    }

    public static Serie de(int id, String nome, String idioma, List<String> generos,
                           Double nota, String status, String estreia,
                           String termino, String emissora) {
        return new Serie(id, nome, idioma, generos, nota,
                EstadoSerie.deStatusApi(status), status, estreia, termino, emissora);
    }

    public static Serie fromJson(JsonNode show) {
        int id = show.path("id").asInt(-1);
        String nome = textoOuPadrao(show.path("name"), "Sem nome");
        String idioma = textoOuPadrao(show.path("language"), "Desconhecido");

        List<String> generos = new ArrayList<>();
        JsonNode generosNo = show.path("genres");
        if (generosNo.isArray()) {
            for (JsonNode g : generosNo) {
                if (g != null && !g.isNull()) {
                    generos.add(g.asText());
                }
            }
        }

        Double nota = null;
        JsonNode media = show.path("rating").path("average");
        if (media.isNumber()) {
            nota = media.asDouble();
        }

        String status = textoOuPadrao(show.path("status"), "");
        String estreia = textoOuNulo(show.path("premiered"));
        String termino = textoOuNulo(show.path("ended"));
        String emissora = extrairEmissora(show);

        return new Serie(id, nome, idioma, generos, nota,
                EstadoSerie.deStatusApi(status), status, estreia, termino, emissora);
    }

    public Map<String, Object> toMapa() {
        Map<String, Object> mapa = new LinkedHashMap<>();
        mapa.put("id", id);
        mapa.put("name", nome);
        mapa.put("language", idioma);
        mapa.put("genres", new ArrayList<Object>(generos));

        Map<String, Object> rating = new LinkedHashMap<>();
        rating.put("average", nota);
        mapa.put("rating", rating);

        mapa.put("status", statusOriginal);
        mapa.put("premiered", dataEstreia);
        mapa.put("ended", dataTermino);

        Map<String, Object> network = new LinkedHashMap<>();
        network.put("name", emissora);
        mapa.put("network", network);

        return mapa;
    }

    private static String extrairEmissora(JsonNode show) {
        JsonNode network = show.path("network").path("name");
        if (network.isTextual() && !network.asText().trim().isEmpty()) {
            return network.asText().trim();
        }
        JsonNode web = show.path("webChannel").path("name");
        if (web.isTextual() && !web.asText().trim().isEmpty()) {
            return web.asText().trim();
        }
        return "Desconhecida";
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public List<String> getGeneros() {
        return Collections.unmodifiableList(generos);
    }

    public Double getNota() {
        return nota;
    }

    public EstadoSerie getEstado() {
        return estado;
    }

    public String getDataEstreia() {
        return dataEstreia;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public String getEmissora() {
        return emissora;
    }

    public String getGenerosTexto() {
        return generos.isEmpty() ? "-" : String.join(", ", generos);
    }

    public String getNotaTexto() {
        return (nota == null) ? "N/A" : String.format("%.1f", nota);
    }

    public String getEstreiaTexto() {
        return (dataEstreia == null) ? "-" : dataEstreia;
    }

    public String getTerminoTexto() {
        return (dataTermino == null) ? "-" : dataTermino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Serie)) {
            return false;
        }
        return id == ((Serie) o).id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return nome + " (" + getEstreiaTexto() + ")";
    }

    private static String textoOuPadrao(JsonNode no, String padrao) {
        if (no == null || no.isMissingNode() || no.isNull()) {
            return padrao;
        }
        String s = no.asText().trim();
        return s.isEmpty() ? padrao : s;
    }

    private static String textoOuNulo(JsonNode no) {
        if (no == null || no.isMissingNode() || no.isNull()) {
            return null;
        }
        String s = no.asText().trim();
        return s.isEmpty() ? null : s;
    }
}