import java.io.*;
import java.util.*;

public class PersistenciaUsuario {

    private static final String ARQUIVO = "usuario.json";

    public static void salvar(Usuario u) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {

            pw.println("{");
            pw.println("\"nome\":\"" + u.getNome() + "\",");
            pw.println("\"email\":\"" + u.getEmail() + "\",");

            pw.println("\"pesquisadas\":" + lista(u.getSeriesPesquisadas()) + ",");
            pw.println("\"favoritos\":" + lista(u.getFavoritos()) + ",");
            pw.println("\"assistidas\":" + lista(u.getAssistidas()) + ",");
            pw.println("\"queroAssistir\":" + lista(u.getQueroAssistir()));

            pw.println("}");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Usuario carregar() {

        File file = new File(ARQUIVO);

        if (!file.exists()) {
            return new Usuario("Matheus", "matheus@teste.com");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            StringBuilder json = new StringBuilder();
            String linha;

            while ((linha = br.readLine()) != null) {
                json.append(linha);
            }

            String txt = json.toString();

            Usuario u = new Usuario(
                    extrair(txt, "\"nome\":\""),
                    extrair(txt, "\"email\":\"")
            );

            u.getSeriesPesquisadas().addAll(parse(txt, "pesquisadas"));
            u.getFavoritos().addAll(parse(txt, "favoritos"));
            u.getAssistidas().addAll(parse(txt, "assistidas"));
            u.getQueroAssistir().addAll(parse(txt, "queroAssistir"));

            return u;

        } catch (Exception e) {
            e.printStackTrace();
            return new Usuario("Matheus", "matheus@teste.com");
        }
    }


    private static String lista(List<Serie> lista) {

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < lista.size(); i++) {

            Serie s = lista.get(i);

            sb.append("{")
                    .append("\"nome\":\"").append(s.getNome()).append("\",")
                    .append("\"idioma\":\"").append(s.getIdioma()).append("\",")
                    .append("\"nota\":").append(s.getNota()).append(",")
                    .append("\"status\":\"").append(s.getStatus()).append("\",")
                    .append("\"estreia\":\"").append(s.getEstreia()).append("\",")
                    .append("\"imagemUrl\":\"").append(s.getImagemUrl()).append("\"")
                    .append("}");

            if (i < lista.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }


    private static List<Serie> parse(String json, String chave) {

        List<Serie> lista = new ArrayList<>();

        try {

            String bloco = json.split("\"" + chave + "\":\\[")[1].split("]")[0];

            String[] itens = bloco.split("\\{");

            for (String it : itens) {

                if (!it.contains("nome")) {
                    continue;
                }

                String nome = extrair(it, "\"nome\":\"");
                String idioma = extrair(it, "\"idioma\":\"");

                double nota = 0;
                try {
                    nota = Double.parseDouble(it.split("\"nota\":")[1].split(",")[0]);
                } catch (Exception ignored) {
                }

                String status = extrair(it, "\"status\":\"");
                String estreia = extrair(it, "\"estreia\":\"");
                String imagemUrl = extrair(it, "\"imagemUrl\":\"");

                lista.add(new Serie(
                        nome,
                        idioma,
                        nota,
                        status,
                        new ArrayList<>(),
                        estreia,
                        "-",
                        "-",
                        imagemUrl
                ));
            }

        } catch (Exception e) {
            return lista;
        }

        return lista;
    }


    private static String extrair(String txt, String chave) {

        try {
            return txt.split(chave)[1].split("\"")[0];
        } catch (Exception e) {
            return "-";
        }
    }
}