import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BancoDados {

    private static final String ARQUIVO = System.getProperty("user.home") + "/Learn/PROVA2BIM/dados.json";

    public void salvar(Usuario usuario, ListaSeries listas) throws Exception {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"usuario\": \"").append(escapar(usuario.getNome())).append("\",\n");
        json.append("  \"favoritos\": ").append(serializarLista(listas.getFavoritos())).append(",\n");
        json.append("  \"assistidas\": ").append(serializarLista(listas.getAssistidas())).append(",\n");
        json.append("  \"desejam\": ").append(serializarLista(listas.getDesejam())).append("\n");
        json.append("}");

        try (FileWriter fw = new FileWriter(ARQUIVO)) {
            fw.write(json.toString());
        }
    }

    public Object[] carregar() throws Exception {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return null;

        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) conteudo.append(linha);
        }

        String json = conteudo.toString();

        String nomeUsuario = extrairValor(json, "\"usuario\": \"", "\"");
        Usuario usuario = new Usuario(nomeUsuario);

        ListaSeries listas = new ListaSeries();

        for (Serie s : lerLista(json, "favoritos"))  listas.adicionarFavorito(s);
        for (Serie s : lerLista(json, "assistidas")) listas.adicionarAssistida(s);
        for (Serie s : lerLista(json, "desejam"))    listas.adicionarDesejo(s);

        return new Object[]{ usuario, listas };
    }

    private String serializarLista(List<Serie> series) {
        if (series.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < series.size(); i++) {
            Serie s = series.get(i);
            sb.append("    {\n");
            sb.append("      \"id\": ").append(s.getId()).append(",\n");
            sb.append("      \"nome\": \"").append(escapar(s.getNome())).append("\",\n");
            sb.append("      \"idioma\": \"").append(escapar(s.getIdioma())).append("\",\n");
            sb.append("      \"generos\": ").append(serializarGeneros(s.getGeneros())).append(",\n");
            sb.append("      \"nota\": ").append(s.getNota()).append(",\n");
            sb.append("      \"estado\": \"").append(escapar(s.getEstado())).append("\",\n");
            sb.append("      \"dataEstreia\": \"").append(s.getDataEstreia() != null ? s.getDataEstreia() : "").append("\",\n");
            sb.append("      \"dataTermino\": \"").append(s.getDataTermino() != null ? s.getDataTermino() : "").append("\",\n");
            sb.append("      \"emissora\": \"").append(escapar(s.getEmissora())).append("\",\n");
            sb.append("      \"resumo\": \"").append(escapar(s.getResumo())).append("\"\n");
            sb.append("    }");
            if (i < series.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]");
        return sb.toString();
    }

    private String serializarGeneros(List<String> generos) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < generos.size(); i++) {
            sb.append("\"").append(escapar(generos.get(i))).append("\"");
            if (i < generos.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private List<Serie> lerLista(String json, String chave) {
        List<Serie> resultado = new ArrayList<>();

        int inicio = json.indexOf("\"" + chave + "\":");
        if (inicio == -1) return resultado;

        int abre  = json.indexOf("[", inicio);
        int fecha = encontrarFechamento(json, abre);
        if (abre == -1 || fecha == -1) return resultado;

        String bloco = json.substring(abre + 1, fecha);
        for (String item : dividirObjetos(bloco)) {
            Serie s = desserializarSerie(item.trim());
            if (s != null) resultado.add(s);
        }

        return resultado;
    }

    private Serie desserializarSerie(String json) {
        try {
            int id          = Integer.parseInt(extrairValor(json, "\"id\": ", ",").trim());
            String nome     = extrairValor(json, "\"nome\": \"", "\"");
            String idioma   = extrairValor(json, "\"idioma\": \"", "\"");
            String estado   = extrairValor(json, "\"estado\": \"", "\"");
            String emissora = extrairValor(json, "\"emissora\": \"", "\"");
            String resumo   = extrairValor(json, "\"resumo\": \"", "\"");
            double nota     = Double.parseDouble(extrairValor(json, "\"nota\": ", ",").trim());

            List<String> generos = new ArrayList<>();
            String generosBloco  = extrairValor(json, "\"generos\": [", "]");
            for (String g : generosBloco.split(",")) {
                String gen = g.replace("\"", "").trim();
                if (!gen.isEmpty()) generos.add(gen);
            }
            if (generos.isEmpty()) generos.add("N/A");

            LocalDate dataEstreia = null, dataTermino = null;
            String de = extrairValor(json, "\"dataEstreia\": \"", "\"");
            String dt = extrairValor(json, "\"dataTermino\": \"", "\"");
            if (!de.isEmpty()) dataEstreia = LocalDate.parse(de);
            if (!dt.isEmpty()) dataTermino = LocalDate.parse(dt);

            return new Serie(id, nome, idioma, generos, nota, estado,
                             dataEstreia, dataTermino, emissora, desescapar(resumo));
        } catch (Exception e) {
            return null;
        }
    }

    private String extrairValor(String json, String inicio, String fim) {
        int i = json.indexOf(inicio);
        if (i == -1) return "";
        i += inicio.length();
        int f = json.indexOf(fim, i);
        if (f == -1) return "";
        return json.substring(i, f);
    }

    private int encontrarFechamento(String json, int abre) {
        int nivel = 0;
        for (int i = abre; i < json.length(); i++) {
            if (json.charAt(i) == '[') nivel++;
            else if (json.charAt(i) == ']') {
                nivel--;
                if (nivel == 0) return i;
            }
        }
        return -1;
    }

    private List<String> dividirObjetos(String json) {
        List<String> objetos = new ArrayList<>();
        int nivel = 0, inicio = -1;
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                if (nivel == 0) inicio = i;
                nivel++;
            } else if (c == '}') {
                nivel--;
                if (nivel == 0 && inicio != -1) {
                    objetos.add(json.substring(inicio, i + 1));
                    inicio = -1;
                }
            }
        }
        return objetos;
    }

    private String escapar(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "");
    }

    private String desescapar(String s) {
        if (s == null) return "";
        return s.replace("\\\"", "\"").replace("\\n", "\n").replace("\\\\", "\\");
    }
}
