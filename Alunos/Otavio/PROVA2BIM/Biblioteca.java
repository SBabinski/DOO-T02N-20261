import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Biblioteca {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final RepositorioDados repositorio;

    private Usuario usuario;
    private final List<Serie> favoritos = new ArrayList<>();
    private final List<Serie> assistidas = new ArrayList<>();
    private final List<Serie> desejaAssistir = new ArrayList<>();

    public Biblioteca(RepositorioDados repositorio) {
        this.repositorio = repositorio;
    }

    public void iniciar() {
        if (repositorio.existe()) {
            try {
                carregar();
            } catch (PersistenciaException e) {
                System.err.println("Aviso: " + e.getMessage() + " Recomecando com dados padrao.");
                reiniciarComDadosPadrao();
            }
        } else {
            reiniciarComDadosPadrao();
        }
        if (usuario == null) {
            usuario = new Usuario("Convidado");
        }
    }

    private void reiniciarComDadosPadrao() {
        usuario = new Usuario("Convidado");
        favoritos.clear();
        assistidas.clear();
        desejaAssistir.clear();
        semearDadosIniciais();
        salvar();
    }

    private void semearDadosIniciais() {
        favoritos.add(Serie.de(169, "Breaking Bad", "English",
                Arrays.asList("Drama", "Crime", "Thriller"), 9.3,
                "Ended", "2008-01-20", "2013-09-29", "AMC"));
        favoritos.add(Serie.de(82, "Game of Thrones", "English",
                Arrays.asList("Drama", "Adventure", "Fantasy"), 9.0,
                "Ended", "2011-04-17", "2019-05-19", "HBO"));

        assistidas.add(Serie.de(2993, "Friends", "English",
                Arrays.asList("Comedy", "Romance"), 8.6,
                "Ended", "1994-09-22", "2004-05-06", "NBC"));
        assistidas.add(Serie.de(396, "Sherlock", "English",
                Arrays.asList("Drama", "Crime", "Mystery"), 9.0,
                "Ended", "2010-07-25", "2017-01-15", "BBC One"));

        desejaAssistir.add(Serie.de(1955, "The Last of Us", "English",
                Arrays.asList("Drama", "Action", "Horror"), 8.7,
                "Running", "2023-01-15", null, "HBO"));
        desejaAssistir.add(Serie.de(1371, "Dark", "German",
                Arrays.asList("Drama", "Science-Fiction", "Thriller"), 8.4,
                "Ended", "2017-12-01", "2020-06-27", "Netflix"));
    }

    public List<Serie> getLista(TipoLista tipo) {
        switch (tipo) {
            case FAVORITOS:       return Collections.unmodifiableList(favoritos);
            case ASSISTIDAS:      return Collections.unmodifiableList(assistidas);
            case DESEJA_ASSISTIR: return Collections.unmodifiableList(desejaAssistir);
            default: throw new IllegalArgumentException("Tipo de lista invalido: " + tipo);
        }
    }

    private List<Serie> listaInterna(TipoLista tipo) {
        switch (tipo) {
            case FAVORITOS:       return favoritos;
            case ASSISTIDAS:      return assistidas;
            case DESEJA_ASSISTIR: return desejaAssistir;
            default: throw new IllegalArgumentException("Tipo de lista invalido: " + tipo);
        }
    }

    public boolean adicionar(TipoLista tipo, Serie serie) {
        List<Serie> lista = listaInterna(tipo);
        if (lista.contains(serie)) {
            return false;
        }
        lista.add(serie);
        salvar();
        return true;
    }

    public boolean remover(TipoLista tipo, Serie serie) {
        boolean removido = listaInterna(tipo).remove(serie);
        if (removido) {
            salvar();
        }
        return removido;
    }

    public boolean contem(TipoLista tipo, Serie serie) {
        return listaInterna(tipo).contains(serie);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void salvar() {
        try {
            Map<String, Object> dados = new LinkedHashMap<>();
            dados.put("usuario", usuario.toMapa());
            dados.put("favoritos", serializar(favoritos));
            dados.put("assistidas", serializar(assistidas));
            dados.put("desejaAssistir", serializar(desejaAssistir));
            String json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(dados);
            repositorio.gravar(json);
        } catch (JsonProcessingException e) {
            throw new PersistenciaException("Nao foi possivel converter os dados para JSON.", e);
        } catch (IOException e) {
            throw new PersistenciaException("Nao foi possivel salvar os dados em disco.", e);
        }
    }

    private void carregar() {
        try {
            String conteudo = repositorio.ler();
            JsonNode raiz = MAPPER.readTree(conteudo);
            if (raiz == null || !raiz.isObject()) {
                throw new PersistenciaException("Arquivo de dados corrompido.");
            }

            usuario = Usuario.fromJson(raiz.path("usuario"));

            favoritos.clear();
            favoritos.addAll(lerLista(raiz.path("favoritos")));
            assistidas.clear();
            assistidas.addAll(lerLista(raiz.path("assistidas")));
            desejaAssistir.clear();
            desejaAssistir.addAll(lerLista(raiz.path("desejaAssistir")));

        } catch (JsonProcessingException e) {
            throw new PersistenciaException("Arquivo de dados em formato JSON invalido.", e);
        } catch (IOException e) {
            throw new PersistenciaException("Nao foi possivel ler o arquivo de dados.", e);
        }
    }

    private List<Serie> lerLista(JsonNode arrayNo) {
        List<Serie> lista = new ArrayList<>();
        if (arrayNo != null && arrayNo.isArray()) {
            for (JsonNode item : arrayNo) {
                if (item.isObject()) {
                    lista.add(Serie.fromJson(item));
                }
            }
        }
        return lista;
    }

    private List<Object> serializar(List<Serie> lista) {
        List<Object> saida = new ArrayList<>();
        for (Serie s : lista) {
            saida.add(s.toMapa());
        }
        return saida;
    }
}
