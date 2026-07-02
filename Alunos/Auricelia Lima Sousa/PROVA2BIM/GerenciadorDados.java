import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class GerenciadorDados {
    public void salvarSeries(ArrayList<Serie> lista, String arq) {
        try (FileWriter writer = new FileWriter(arq + ".json")) {
            StringBuilder json = new StringBuilder("[\n");
            for (int i = 0; i < lista.size(); i++) {
                Serie s = lista.get(i);
                json.append("  {\n")
                    .append("    \"nome\": \"").append(s.getNome()).append("\",\n")
                    .append("    \"idioma\": \"").append(s.getIdioma()).append("\",\n")
                    .append("    \"generos\": \"").append(s.getGeneros()).append("\",\n")
                    .append("    \"nota\": \"").append(s.getNota()).append("\",\n")
                    .append("    \"estado\": \"").append(s.getEstado()).append("\",\n")
                    .append("    \"dataEstreia\": \"").append(s.getDataEstreia()).append("\",\n")
                    .append("    \"dataTermino\": \"").append(s.getDataTermino()).append("\",\n")
                    .append("    \"emissora\": \"").append(s.getEmissora()).append("\"\n")
                    .append("  }");
                if (i < lista.size() - 1) json.append(",");
                json.append("\n");
            }
            json.append("]");
            writer.write(json.toString());
        } catch (Exception e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public ArrayList<Serie> carregarSeries(String arq) {
    ArrayList<Serie> lista = new ArrayList<>();
    File f = new File(arq + ".json");
    if (!f.exists()) return lista;
    try (Scanner sc = new Scanner(f)) {
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) sb.append(sc.nextLine());
        
        Matcher m = Pattern.compile("\\s*\\{\\s*(.*?)\\s*\\}(?=\\s*,|\\s*\\])").matcher(sb.toString());
        while (m.find()) {
            String obj = m.group(1);
            lista.add(new Serie(
                extrair(obj, "nome"), extrair(obj, "idioma"),
                extrair(obj, "generos"), extrair(obj, "nota"),
                extrair(obj, "estado"), extrair(obj, "dataEstreia"),
                extrair(obj, "dataTermino"), extrair(obj, "emissora")
            ));
        }
    } catch (Exception e) {}
    return lista;
}

    private String extrair(String json, String campo) {
        Matcher m = Pattern.compile("\"" + campo + "\":\\s*\"(.*?)\"").matcher(json);
        return m.find() ? m.group(1) : "N/A";
    }
}