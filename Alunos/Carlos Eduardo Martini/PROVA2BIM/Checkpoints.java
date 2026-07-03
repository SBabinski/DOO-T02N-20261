import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Checkpoints {

    public void salvarNaLista(Serie serie, String tipoLista) {
        String nomeArquivo = tipoLista.toLowerCase().replace(" ", "_") + ".txt";
        try {
            FileWriter escritor = new FileWriter(nomeArquivo, true);
            escritor.write("Nome: " + serie.getNomeSerie() + " | Idioma: " + serie.getIdioma() + " | Nota: "
                    + serie.getNotGeral() + "\n");
            escritor.close();
            System.out.println("Série salva com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao tentar salvar o arquivo: " + e.getMessage());
        }
    }

    public ArrayList<Serie> lerLista(String tipoLista) {
        ArrayList<Serie> seriesSalvas = new ArrayList<>();
        String nomeArquivo = tipoLista.toLowerCase().replace(" ", "_") + ".txt";

        try {
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.contains("Nome: ")) {
                    String nome = extrairParte(linha, "Nome: ", " | ");
                    String idioma = extrairParte(linha, "Idioma: ", " | ");
                    String nota = extrairParte(linha, "Nota: ", "\n");

                    String estado = linha.contains("Estado: ") ? extrairParte(linha, "Estado: ", " | ")
                            : "Desconhecido";
                    String estreia = linha.contains("Estreia: ") ? extrairParte(linha, "Estreia: ", " | ")
                            : "0000-00-00";

                    Serie s = new Serie(nome, idioma, "", nota, estado, estreia, "", "", "");
                    seriesSalvas.add(s);
                }
            }
            leitor.close();
        } catch (IOException e) {
            System.out.println("Nota: Arquivo " + nomeArquivo + " ainda não existe.");
        }

        return seriesSalvas;
    }

    private String extrairParte(String linha, String chaveInicio, String chaveFim) {
        if (!linha.contains(chaveInicio))
            return "";
        int inicio = linha.indexOf(chaveInicio) + chaveInicio.length();
        int fim = chaveFim.equals("\n") ? linha.length() : linha.indexOf(chaveFim, inicio);
        if (fim == -1)
            fim = linha.length();
        return linha.substring(inicio, fim).trim();
    }

    public void saveSeries(String nome, String idioma) {
    }

    public void reescreverLista(java.util.ArrayList<Serie> lista, String tipoLista) {
        String nomeArquivo = tipoLista.toLowerCase().replace(" ", "_") + ".txt";
        try {
            // O 'false' aqui diz para o Java limpar o arquivo antes de escrever
            java.io.FileWriter escritor = new java.io.FileWriter(nomeArquivo, false);
            
            for (Serie serie : lista) {
                escritor.write("Nome: " + serie.getNomeSerie() + 
                               " | Idioma: " + serie.getIdioma() + 
                               " | Nota: " + serie.getNotGeral() + 
                               " | Estado: " + serie.getEstado() + 
                               " | Estreia: " + serie.getDataEstreia() + "\n");
            }
            
            escritor.close();
        } catch (java.io.IOException e) {
            System.out.println("Erro ao reescrever o arquivo: " + e.getMessage());
        }
    }
}