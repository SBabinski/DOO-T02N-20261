package modelo;

import java.util.ArrayList;
import java.util.List;

public class PerfilUsuario {

    private String nome;
    private List<Serie> favoritas;
    private List<Serie> assistidas;
    private List<Serie> queroAssistir;

    public PerfilUsuario() {
        this.favoritas     = new ArrayList<>();
        this.assistidas    = new ArrayList<>();
        this.queroAssistir = new ArrayList<>();
    }

    public PerfilUsuario(String nome) {
        this();
        this.nome = nome;
    }

    public String getNome()             { return nome; }
    public void   setNome(String nome)  { this.nome = nome; }

    public List<Serie> getFavoritas()                   { return favoritas; }
    public void        setFavoritas(List<Serie> lista)  { this.favoritas = lista; }

    public List<Serie> getAssistidas()                   { return assistidas; }
    public void        setAssistidas(List<Serie> lista)  { this.assistidas = lista; }

    public List<Serie> getQueroAssistir()                   { return queroAssistir; }
    public void        setQueroAssistir(List<Serie> lista)  { this.queroAssistir = lista; }

    public void adicionarFavorita(Serie s)  { if (!favoritas.contains(s)) favoritas.add(s); }
    public void removerFavorita(Serie s)    { favoritas.remove(s); }
    public boolean ehFavorita(Serie s)      { return favoritas.contains(s); }

    public void adicionarAssistida(Serie s) { if (!assistidas.contains(s)) assistidas.add(s); }
    public void removerAssistida(Serie s)   { assistidas.remove(s); }
    public boolean jaAssistiu(Serie s)      { return assistidas.contains(s); }

    public void adicionarQueroAssistir(Serie s) { if (!queroAssistir.contains(s)) queroAssistir.add(s); }
    public void removerQueroAssistir(Serie s)   { queroAssistir.remove(s); }
    public boolean estaNaLista(Serie s)         { return queroAssistir.contains(s); }
}
