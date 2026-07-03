package controller;

import model.Serie;
import model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListaController {

    public ListaController() {
    }

    public void adicionaFavorito(User usuario, Serie serie) {
        usuario.adicionaFavoritoALista(serie);
    }

    public void removeFavorito(User usuario, Serie serie) {
        usuario.removeFavoritoDaLista(serie);
    }

    public void adicionaSerieAssistida(User usuario, Serie serie) {
        usuario.adicionaSerieAssistidaALista(serie);
    }

    public void removeSerieAssistida(User usuario, Serie serie) {
        usuario.removeSerieAssistidaALista(serie);
    }

    public void adicionaSerieDesejada(User usuario, Serie serie) {
        usuario.adicionaSerieDesejadaALista(serie);
    }

    public void removeSerieDesejada(User usuario, Serie serie) {
        usuario.removeSerieDesejadaDaLista(serie);
    }

    public List<Serie> ordenarPorNome(List<Serie> lista) {
        List<Serie> listaOrdenada = new ArrayList<>(lista);

        listaOrdenada.sort(
                Comparator.comparing(
                        serie -> valorTexto(serie.getName()),
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return listaOrdenada;
    }

    public List<Serie> ordenarPorNota(List<Serie> lista) {
        List<Serie> listaOrdenada = new ArrayList<>(lista);

        listaOrdenada.sort(
                Comparator.comparing(
                        (Serie serie) -> valorNota(serie.getNotaGeral())
                ).reversed()
        );

        return listaOrdenada;
    }

    public List<Serie> ordenarPorEstado(List<Serie> lista) {
        List<Serie> listaOrdenada = new ArrayList<>(lista);

        listaOrdenada.sort(
                Comparator.comparing(
                        serie -> valorTexto(serie.getStatus()),
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return listaOrdenada;
    }

    public List<Serie> ordenarPorDataEstreia(List<Serie> lista) {
        List<Serie> listaOrdenada = new ArrayList<>(lista);

        listaOrdenada.sort(
                Comparator.comparing(
                        Serie::getPremiered,
                        Comparator.nullsLast(Comparator.naturalOrder())
                )
        );

        return listaOrdenada;
    }

    private String valorTexto(String texto) {
        if (texto == null) {
            return "";
        }

        return texto;
    }

    private Double valorNota(Double nota) {
        if (nota == null) {
            return -1.0;
        }

        return nota;
    }
}