package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Armazena os dados do usuário: nome e suas três listas de séries.
 * É esta classe que é persistida em JSON pelo PersistenceService.
 */
public class UserData {

    private String nomeUsuario;
    private List<Serie> favoritos;
    private List<Serie> jaAssistidas;
    private List<Serie> querAssistir;

    public UserData() {
        this.favoritos     = new ArrayList<>();
        this.jaAssistidas  = new ArrayList<>();
        this.querAssistir  = new ArrayList<>();
    }

    public UserData(String nomeUsuario) {
        this();
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * Preenche as listas com séries populares para facilitar a demonstração
     * do sistema na primeira execução.
     */
    public void carregarDadosIniciais() {

        // ── Favoritos ──────────────────────────────────────────────────────────
        Serie breakingBad = new Serie(
            169, "Breaking Bad", "English",
            Arrays.asList("Drama", "Crime", "Thriller"),
            9.2, "Ended", "2008-01-20", "2013-09-29", "AMC"
        );
        Serie gameOfThrones = new Serie(
            82, "Game of Thrones", "English",
            Arrays.asList("Drama", "Adventure", "Fantasy"),
            9.3, "Ended", "2011-04-17", "2019-05-19", "HBO"
        );
        Serie chernobyl = new Serie(
            37779, "Chernobyl", "English",
            Arrays.asList("Drama", "History", "Thriller"),
            9.1, "Ended", "2019-05-06", "2019-06-03", "HBO"
        );
        favoritos.addAll(Arrays.asList(breakingBad, gameOfThrones, chernobyl));

        // ── Já Assistidas ──────────────────────────────────────────────────────
        Serie theOffice = new Serie(
            526, "The Office", "English",
            Arrays.asList("Comedy"),
            8.9, "Ended", "2005-03-24", "2013-05-16", "NBC"
        );
        Serie dark = new Serie(
            17861, "Dark", "German",
            Arrays.asList("Drama", "Crime", "Science-Fiction", "Thriller"),
            8.8, "Ended", "2017-12-01", "2020-06-27", "Netflix"
        );
        jaAssistidas.addAll(Arrays.asList(theOffice, dark));

        // ── Quero Assistir ─────────────────────────────────────────────────────
        Serie strangerthings = new Serie(
            2993, "Stranger Things", "English",
            Arrays.asList("Drama", "Fantasy", "Horror"),
            8.5, "Running", "2016-07-15", null, "Netflix"
        );
        Serie theBoys = new Serie(
            30770, "The Boys", "English",
            Arrays.asList("Action", "Crime", "Science-Fiction"),
            8.7, "Ended", "2019-07-26", "2024-06-13", "Amazon Prime Video"
        );
        Serie betterCallSaul = new Serie(
            618, "Better Call Saul", "English",
            Arrays.asList("Crime", "Drama", "Thriller"),
            9.0, "Ended", "2015-02-08", "2022-08-15", "AMC"
        );
        querAssistir.addAll(Arrays.asList(strangerthings, theBoys, betterCallSaul));
    }

    // ─── Favoritos ────────────────────────────────────────────────────────────

    public boolean addFavorito(Serie serie) {
        if (!favoritos.contains(serie)) {
            favoritos.add(serie);
            return true;
        }
        return false;
    }

    public boolean removeFavorito(Serie serie) {
        return favoritos.remove(serie);
    }

    public boolean isFavorito(Serie serie) {
        return favoritos.contains(serie);
    }

    // ─── Já Assistidas ────────────────────────────────────────────────────────

    public boolean addJaAssistida(Serie serie) {
        if (!jaAssistidas.contains(serie)) {
            jaAssistidas.add(serie);
            return true;
        }
        return false;
    }

    public boolean removeJaAssistida(Serie serie) {
        return jaAssistidas.remove(serie);
    }

    public boolean isJaAssistida(Serie serie) {
        return jaAssistidas.contains(serie);
    }

    // ─── Quero Assistir ───────────────────────────────────────────────────────

    public boolean addQuerAssistir(Serie serie) {
        if (!querAssistir.contains(serie)) {
            querAssistir.add(serie);
            return true;
        }
        return false;
    }

    public boolean removeQuerAssistir(Serie serie) {
        return querAssistir.remove(serie);
    }

    public boolean isQuerAssistir(Serie serie) {
        return querAssistir.contains(serie);
    }

    // ─── Getters / Setters ────────────────────────────────────────────────────

    public String getNomeUsuario()                       { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario)       { this.nomeUsuario = nomeUsuario; }

    public List<Serie> getFavoritos()                    { return favoritos; }
    public void setFavoritos(List<Serie> favoritos)      { this.favoritos = favoritos; }

    public List<Serie> getJaAssistidas()                 { return jaAssistidas; }
    public void setJaAssistidas(List<Serie> jaAssistidas){ this.jaAssistidas = jaAssistidas; }

    public List<Serie> getQuerAssistir()                 { return querAssistir; }
    public void setQuerAssistir(List<Serie> querAssistir){ this.querAssistir = querAssistir; }
}
