import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListaSeries {

    private Set<Serie> favoritos;
    private Set<Serie> assistidas;
    private Set<Serie> desejam;

    public ListaSeries() {
        favoritos  = new HashSet<>();
        assistidas = new HashSet<>();
        desejam    = new HashSet<>();
    }

    public void adicionarFavorito(Serie s)  { favoritos.add(s); }
    public void removerFavorito(Serie s)    { favoritos.remove(s); }
    public List<Serie> getFavoritos()       { return new ArrayList<>(favoritos); }
    public boolean isFavorito(Serie s)      { return favoritos.contains(s); }

    public void adicionarAssistida(Serie s) { assistidas.add(s); desejam.remove(s); }
    public void removerAssistida(Serie s)   { assistidas.remove(s); }
    public List<Serie> getAssistidas()      { return new ArrayList<>(assistidas); }
    public boolean isAssistida(Serie s)     { return assistidas.contains(s); }

    public void adicionarDesejo(Serie s)    { desejam.add(s); assistidas.remove(s); }
    public void removerDesejo(Serie s)      { desejam.remove(s); }
    public List<Serie> getDesejam()         { return new ArrayList<>(desejam); }
    public boolean isDesejo(Serie s)        { return desejam.contains(s); }

    public List<Serie> ordenar(List<Serie> lista, String criterio) {
        List<Serie> ordenada = new ArrayList<>(lista);
        if ("Nota".equals(criterio)) {
            ordenada.sort(Comparator.comparingDouble(Serie::getNota).reversed());
        } else if ("Estado".equals(criterio)) {
            ordenada.sort(Comparator.comparing(s -> s.getEstado() != null ? s.getEstado() : ""));
        } else if ("Data de estreia".equals(criterio)) {
            ordenada.sort(Comparator.comparing(
                s -> s.getDataEstreia() != null ? s.getDataEstreia() : java.time.LocalDate.MAX,
                Comparator.reverseOrder()
            ));
        } else {
            ordenada.sort(Comparator.comparing(Serie::getNome));
        }
        return ordenada;
    }
}
