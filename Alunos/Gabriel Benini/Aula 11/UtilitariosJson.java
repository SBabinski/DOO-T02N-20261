import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilitariosJson {

    private UtilitariosJson() {}

    public static String extrairTexto(String json, String chave) {
        Matcher m = Pattern.compile("\"" + chave + "\"\\s*:\\s*\"([^\"]*)\"").matcher(json);
        return m.find() ? m.group(1) : "";
    }

    public static double extrairNumero(String json, String chave) {
        Matcher m = Pattern.compile("\"" + chave + "\"\\s*:\\s*(-?[0-9]+\\.?[0-9]*)").matcher(json);
        try {
            return m.find() ? Double.parseDouble(m.group(1)) : 0.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static String extrairBloco(String json, int inicio) throws Exception {
        int pos = json.indexOf('{', inicio);
        if (pos == -1) throw new Exception("Bloco JSON não encontrado.");
        int profundidade = 0;
        for (int i = pos; i < json.length(); i++) {
            if (json.charAt(i) == '{') profundidade++;
            else if (json.charAt(i) == '}' && --profundidade == 0)
                return json.substring(pos, i + 1);
        }
        throw new Exception("Estrutura JSON malformada.");
    }
}