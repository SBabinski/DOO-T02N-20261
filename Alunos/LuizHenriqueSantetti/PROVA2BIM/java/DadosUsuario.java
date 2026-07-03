import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DadosUsuario {
    private Usuario usuario;
    private ArrayList<Serie> favoritos;
    private ArrayList<Serie> assistidas;
    private ArrayList<Serie> queroAssistir;

    public DadosUsuario(Usuario usuario) {
        this.usuario = usuario;
        favoritos = new ArrayList<Serie>();
        assistidas = new ArrayList<Serie>();
        queroAssistir = new ArrayList<Serie>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Serie> getFavoritos() {
        return favoritos;
    }

    public ArrayList<Serie> getAssistidas() {
        return assistidas;
    }

    public ArrayList<Serie> getQueroAssistir() {
        return queroAssistir;
    }

    public void setFavoritos(ArrayList<Serie> lista) {
        favoritos = lista == null ? new ArrayList<Serie>() : lista;
    }

    public void setAssistidas(ArrayList<Serie> lista) {
        assistidas = lista == null ? new ArrayList<Serie>() : lista;
    }

    public void setQueroAssistir(ArrayList<Serie> lista) {
        queroAssistir = lista == null ? new ArrayList<Serie>() : lista;
    }

    public boolean adicionar(ArrayList<Serie> lista, Serie serie) {
        if (lista == null || serie == null || contem(lista, serie))
            return false;
        lista.add(serie);
        return true;
    }

    public boolean contem(ArrayList<Serie> lista, Serie serie) {
        if (lista == null || serie == null)
            return false;
        for (Serie item : lista)
            if (item != null && item.mesmaSerie(serie))
                return true;
        return false;
    }

    public void ordenarLista(ArrayList<Serie> lista, String criterio) {
        if (lista == null)
            return;
        if ("Nota (maior primeiro)".equals(criterio))
            Collections.sort(lista, compararNota());
        else if ("Estado".equals(criterio))
            Collections.sort(lista, compararEstado());
        else if ("Estreia (mais antiga)".equals(criterio))
            Collections.sort(lista, compararData());
        else
            Collections.sort(lista, compararNome());
    }

    private Comparator<Serie> compararNome() {
        return new Comparator<Serie>() {
            public int compare(Serie a, Serie b) {
                return compararTextos(a.getNome(), b.getNome());
            }
        };
    }

    private Comparator<Serie> compararEstado() {
        return new Comparator<Serie>() {
            public int compare(Serie a, Serie b) {
                return compararTextos(a.getEstado(), b.getEstado());
            }
        };
    }

    private Comparator<Serie> compararNota() {
        return new Comparator<Serie>() {
            public int compare(Serie a, Serie b) {
                if (a.getNota() < 0 && b.getNota() < 0)
                    return 0;
                if (a.getNota() < 0)
                    return 1;
                if (b.getNota() < 0)
                    return -1;
                return Double.compare(b.getNota(), a.getNota());
            }
        };
    }

    private Comparator<Serie> compararData() {
        return new Comparator<Serie>() {
            public int compare(Serie a, Serie b) {
                LocalDate dataA = lerData(a.getDataEstreia());
                LocalDate dataB = lerData(b.getDataEstreia());
                if (dataA == null && dataB == null)
                    return 0;
                if (dataA == null)
                    return 1;
                if (dataB == null)
                    return -1;
                return dataA.compareTo(dataB);
            }
        };
    }

    private int compararTextos(String a, String b) {
        String textoA = a == null ? "" : a.trim();
        String textoB = b == null ? "" : b.trim();
        if (textoA.isEmpty() && textoB.isEmpty())
            return 0;
        if (textoA.isEmpty())
            return 1;
        if (textoB.isEmpty())
            return -1;
        return textoA.compareToIgnoreCase(textoB);
    }

    private LocalDate lerData(String texto) {
        try {
            return texto == null || texto.trim().isEmpty() ? null : LocalDate.parse(texto);
        } catch (Exception e) {
            return null;
        }
    }
}
