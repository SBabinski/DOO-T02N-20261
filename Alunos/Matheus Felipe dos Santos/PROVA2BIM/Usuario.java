import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private String email;

    private List<Serie> seriesPesquisadas;
    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> queroAssistir;

    public Usuario() {
        this.seriesPesquisadas = new ArrayList<>();
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.queroAssistir = new ArrayList<>();
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;

        this.seriesPesquisadas = new ArrayList<>();
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.queroAssistir = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Serie> getSeriesPesquisadas() {
        return seriesPesquisadas;
    }

    public List<Serie> getFavoritos() {
        return favoritos;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public List<Serie> getQueroAssistir() {
        return queroAssistir;
    }

    public void adicionarSerie(Serie serie) {
        if (serie != null && !seriesPesquisadas.contains(serie)) {
            seriesPesquisadas.add(serie);
        }
    }

    public void adicionarFavorito(Serie serie) {
        if (serie != null && !favoritos.contains(serie)) {
            favoritos.add(serie);
        }
    }

    public void adicionarAssistida(Serie serie) {
        if (serie != null && !assistidas.contains(serie)) {
            assistidas.add(serie);
        }
    }

    public void adicionarQueroAssistir(Serie serie) {
        if (serie != null && !queroAssistir.contains(serie)) {
            queroAssistir.add(serie);
        }
    }

    public void removerSerie(Serie serie) {
        seriesPesquisadas.remove(serie);
    }

    public void removerFavorito(Serie serie) {
        favoritos.remove(serie);
    }

    public void removerAssistida(Serie serie) {
        assistidas.remove(serie);
    }

    public void removerQueroAssistir(Serie serie) {
        queroAssistir.remove(serie);
    }

    public void limparHistorico() {
        seriesPesquisadas.clear();
    }

    public int getTotalSeries() {
        return favoritos.size() + assistidas.size() + queroAssistir.size();
    }

    @Override
    public String toString() {
        return nome + " (" + email + ")";
    }
}