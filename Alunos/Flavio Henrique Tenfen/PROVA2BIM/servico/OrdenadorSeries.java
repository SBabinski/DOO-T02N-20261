package servico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import modelo.CriterioOrdenacao;
import modelo.Serie;

public class OrdenadorSeries {

    private OrdenadorSeries() {}

    public static List<Serie> ordenar(List<Serie> series, CriterioOrdenacao criterio) {
        List<Serie> resultado = new ArrayList<>(series);
        resultado.sort(comparadorPara(criterio));
        return resultado;
    }

    private static Comparator<Serie> comparadorPara(CriterioOrdenacao criterio) {
        return switch (criterio) {
            case NOME        -> Comparator.comparing(s -> s.getNome() == null ? "" : s.getNome().toLowerCase());
            case NOTA        -> Comparator.comparingDouble(Serie::getNota).reversed();
            case ESTADO      -> Comparator.comparing(s -> s.getEstado() == null ? "" : s.getEstado().toLowerCase());
            case DATA_ESTREIA-> Comparator.comparing(s -> s.getDataEstreia() == null ? "" : s.getDataEstreia());
        };
    }
}
