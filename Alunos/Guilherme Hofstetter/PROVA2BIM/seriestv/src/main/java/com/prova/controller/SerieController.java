package com.prova.controller;

import com.prova.model.BibliotecaUsuario;
import com.prova.model.Serie;
import com.prova.model.Usuario;
import com.prova.repository.JsonRepository;
import com.prova.service.TvMazeApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SerieController {

    private BibliotecaUsuario biblioteca;
    private final JsonRepository repository;
    private final TvMazeApiService apiService;

    public SerieController() throws IOException {
        this(null);
    }

    public SerieController(String nomeUsuarioInicial) throws IOException {
        this.repository = new JsonRepository();
        this.apiService = new TvMazeApiService();

        this.biblioteca = repository.carregar();

        if (this.biblioteca == null) {
            Usuario usuario = new Usuario(validarNomeUsuario(nomeUsuarioInicial));
            this.biblioteca = new BibliotecaUsuario(usuario);
            repository.salvar(this.biblioteca);
        }
    }

    private String validarNomeUsuario(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return "Usuário";
        }

        return nome.trim();
    }

    public BibliotecaUsuario getBiblioteca() {
        return biblioteca;
    }

    public String getNomeUsuario() {
        return biblioteca.getUsuario().getNome();
    }

    public List<Serie> buscarSeriesPorNome(String nome) throws IOException, InterruptedException {
        return apiService.buscarSeriesPorNome(nome);
    }

    public boolean adicionarFavorito(Serie serie) throws IOException {
        boolean adicionou = biblioteca.adicionarFavorito(serie);

        if (adicionou) {
            repository.salvar(biblioteca);
        }

        return adicionou;
    }

    public boolean removerFavorito(Serie serie) throws IOException {
        boolean removeu = biblioteca.removerFavorito(serie);

        if (removeu) {
            repository.salvar(biblioteca);
        }

        return removeu;
    }

    public boolean adicionarAssistida(Serie serie) throws IOException {
        boolean adicionou = biblioteca.adicionarAssistida(serie);

        if (adicionou) {
            repository.salvar(biblioteca);
        }

        return adicionou;
    }

    public boolean removerAssistida(Serie serie) throws IOException {
        boolean removeu = biblioteca.removerAssistida(serie);

        if (removeu) {
            repository.salvar(biblioteca);
        }

        return removeu;
    }

    public boolean adicionarDesejoAssistir(Serie serie) throws IOException {
        boolean adicionou = biblioteca.adicionarDesejoAssistir(serie);

        if (adicionou) {
            repository.salvar(biblioteca);
        }

        return adicionou;
    }

    public boolean removerDesejoAssistir(Serie serie) throws IOException {
        boolean removeu = biblioteca.removerDesejoAssistir(serie);

        if (removeu) {
            repository.salvar(biblioteca);
        }

        return removeu;
    }

    public List<Serie> listarFavoritos() {
        return biblioteca.getFavoritos();
    }

    public List<Serie> listarAssistidas() {
        return biblioteca.getAssistidas();
    }

    public List<Serie> listarDesejoAssistir() {
        return biblioteca.getDesejoAssistir();
    }

    public List<Serie> listarFavoritosOrdenados(String criterio) {
        return ordenarSeries(biblioteca.getFavoritos(), criterio);
    }

    public List<Serie> listarAssistidasOrdenadas(String criterio) {
        return ordenarSeries(biblioteca.getAssistidas(), criterio);
    }

    public List<Serie> listarDesejoAssistirOrdenadas(String criterio) {
        return ordenarSeries(biblioteca.getDesejoAssistir(), criterio);
    }

    private List<Serie> ordenarSeries(List<Serie> lista, String criterio) {
        List<Serie> copia = new ArrayList<>(lista);

        if (criterio == null) {
            criterio = "Nome";
        }

        switch (criterio) {
            case "Nota":
                copia.sort(Comparator.comparing(
                        (Serie serie) -> serie.getNota() != null ? serie.getNota() : -1.0).reversed());
                break;

            case "Estado":
                copia.sort(Comparator.comparing(
                        serie -> textoSeguro(serie.getEstado()),
                        String.CASE_INSENSITIVE_ORDER));
                break;

            case "Estreia":
                copia.sort(Comparator.comparing(
                        serie -> textoSeguro(serie.getDataEstreia())));
                break;

            case "Nome":
            default:
                copia.sort(Comparator.comparing(
                        serie -> textoSeguro(serie.getNome()),
                        String.CASE_INSENSITIVE_ORDER));
                break;
        }

        return copia;
    }

    private String textoSeguro(String texto) {
        if (texto == null || texto.trim().isEmpty() || texto.equals("Não informado") || texto.equals("Nao informado")) {
            return "ZZZ";
        }

        return texto;
    }
}