import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Persistencia {
    private final Path arquivo = Paths.get("dados_series.json");
    private final JsonSimples json = new JsonSimples();
    private boolean dadosPadraoUsados;
    private String avisoCarregamento;

    public DadosUsuario carregarDados() {
        dadosPadraoUsados = false;
        avisoCarregamento = null;
        if (!Files.exists(arquivo))
            return criarDadosIniciais();
        try {
            String texto = Files.readString(arquivo, StandardCharsets.UTF_8);
            if (texto.trim().isEmpty())
                throw new Exception("arquivo vazio");
            Object raiz = json.ler(texto);
            if (!(raiz instanceof JsonObjeto))
                throw new Exception("objeto principal ausente");
            JsonObjeto objeto = (JsonObjeto) raiz;
            DadosUsuario dados = new DadosUsuario(new Usuario(texto(objeto.pegar("nomeUsuario"))));
            dados.setFavoritos(lerLista(objeto.pegar("favoritos")));
            dados.setAssistidas(lerLista(objeto.pegar("assistidas")));
            dados.setQueroAssistir(lerLista(objeto.pegar("queroAssistir")));
            return dados;
        } catch (Exception e) {
            avisoCarregamento = "O arquivo de dados estava invalido. Foram usados dados padrao.";
            return criarDadosIniciais();
        }
    }

    public boolean salvarDados(DadosUsuario dados) {
        try {
            JsonObjeto raiz = new JsonObjeto();
            raiz.adicionar("nomeUsuario", dados.getUsuario().getNome());
            raiz.adicionar("favoritos", escreverLista(dados.getFavoritos()));
            raiz.adicionar("assistidas", escreverLista(dados.getAssistidas()));
            raiz.adicionar("queroAssistir", escreverLista(dados.getQueroAssistir()));
            Files.writeString(arquivo, formatarJson(json.escrever(raiz)), StandardCharsets.UTF_8);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private DadosUsuario criarDadosIniciais() {
        dadosPadraoUsados = true;
        DadosUsuario dados = new DadosUsuario(new Usuario("Usuario"));

        ArrayList<String> generosAlice = new ArrayList<String>();
        generosAlice.add("Science-Fiction");
        generosAlice.add("Thriller");
        generosAlice.add("Mystery");
        dados.adicionar(dados.getFavoritos(), new Serie(50036, "Alice in Borderland", "Japanese",
                generosAlice, 7.7, "Ended", "2020-12-10", "2025-09-25", "Netflix"));

        ArrayList<String> drama = new ArrayList<String>();
        drama.add("Drama");
        dados.adicionar(dados.getFavoritos(), new Serie(169, "Breaking Bad", "English", drama, 9.2,
                "Ended", "2008-01-20", "2013-09-29", "AMC"));

        ArrayList<String> generosLaCasa = new ArrayList<String>();
        generosLaCasa.add("Action");
        generosLaCasa.add("Crime");
        generosLaCasa.add("Thriller");
        dados.adicionar(dados.getAssistidas(), new Serie(27436, "La Casa de Papel", "Spanish",
                generosLaCasa, 8.0, "Ended", "2017-05-02", "2021-12-03", "Netflix"));

        ArrayList<String> generosSuits = new ArrayList<String>();
        generosSuits.add("Drama");
        generosSuits.add("Legal");
        dados.adicionar(dados.getAssistidas(), new Serie(172, "Suits", "English", generosSuits,
                8.1, "Ended", "2011-06-23", "2019-09-25", "USA Network"));

        ArrayList<String> comedia = new ArrayList<String>();
        comedia.add("Comedy");
        dados.adicionar(dados.getAssistidas(), new Serie(526, "The Office", "English", comedia, 8.5,
                "Ended", "2005-03-24", "2013-05-16", "NBC"));
        ArrayList<String> ficcao = new ArrayList<String>();
        ficcao.add("Drama");
        ficcao.add("Science-Fiction");
        dados.adicionar(dados.getQueroAssistir(), new Serie(2993, "Stranger Things", "English", ficcao, 8.6,
                "Running", "2016-07-15", "", "Netflix"));
        return dados;
    }

    private ArrayList<Serie> lerLista(Object valor) {
        ArrayList<Serie> lista = new ArrayList<Serie>();
        if (!(valor instanceof ArrayList))
            return lista;
        for (Object item : (ArrayList<?>) valor) {
            if (item instanceof JsonObjeto) {
                lista.add(lerSerie((JsonObjeto) item));
            }
        }
        return lista;
    }

    private Serie lerSerie(JsonObjeto objeto) {
        ArrayList<String> generos = new ArrayList<String>();
        Object lista = objeto.pegar("generos");
        if (lista instanceof ArrayList)
            for (Object item : (ArrayList<?>) lista)
                if (item != null)
                    generos.add(item.toString());
        return new Serie(inteiro(objeto.pegar("id"), -1), texto(objeto.pegar("nome")), texto(objeto.pegar("idioma")),
                generos, decimal(objeto.pegar("nota"), -1), texto(objeto.pegar("estado")),
                texto(objeto.pegar("dataEstreia")), texto(objeto.pegar("dataTermino")),
                texto(objeto.pegar("emissora")));
    }

    private ArrayList<Object> escreverLista(ArrayList<Serie> lista) {
        ArrayList<Object> resultado = new ArrayList<Object>();
        if (lista == null)
            return resultado;
        for (Serie serie : lista) {
            if (serie == null)
                continue;
            JsonObjeto objeto = new JsonObjeto();
            objeto.adicionar("id", serie.getId());
            objeto.adicionar("nome", serie.getNome());
            objeto.adicionar("idioma", serie.getIdioma());
            objeto.adicionar("generos", serie.getGeneros());
            objeto.adicionar("nota", serie.getNota());
            objeto.adicionar("estado", serie.getEstado());
            objeto.adicionar("dataEstreia", serie.getDataEstreia());
            objeto.adicionar("dataTermino", serie.getDataTermino());
            objeto.adicionar("emissora", serie.getEmissora());
            resultado.add(objeto);
        }
        return resultado;
    }

    private String texto(Object valor) {
        if (valor == null)
            return "";
        return valor.toString();
    }

    private int inteiro(Object valor, int padrao) {
        if (valor instanceof Number)
            return ((Number) valor).intValue();
        return padrao;
    }

    private double decimal(Object valor, double padrao) {
        if (valor instanceof Number)
            return ((Number) valor).doubleValue();
        return padrao;
    }

    public boolean isDadosPadraoUsados() {
        return dadosPadraoUsados;
    }

    public String getAvisoCarregamento() {
        return avisoCarregamento;
    }

    private String formatarJson(String compacto) {
        StringBuilder bonito = new StringBuilder();
        boolean string = false, escape = false;
        int nivel = 0;
        for (int i = 0; i < compacto.length(); i++) {
            char c = compacto.charAt(i);
            if (string) {
                bonito.append(c);
                if (escape)
                    escape = false;
                else if (c == '\\')
                    escape = true;
                else if (c == '"')
                    string = false;
            } else if (c == '"') {
                string = true;
                bonito.append(c);
            } else if (c == '{' || c == '[') {
                bonito.append(c).append('\n');
                nivel++;
                indentar(bonito, nivel);
            } else if (c == '}' || c == ']') {
                bonito.append('\n');
                nivel--;
                indentar(bonito, nivel);
                bonito.append(c);
            } else if (c == ',') {
                bonito.append(c).append('\n');
                indentar(bonito, nivel);
            } else if (c == ':')
                bonito.append(": ");
            else
                bonito.append(c);
        }
        return bonito.toString();
    }

    private void indentar(StringBuilder texto, int nivel) {
        for (int i = 0; i < nivel; i++)
            texto.append("  ");
    }
}
