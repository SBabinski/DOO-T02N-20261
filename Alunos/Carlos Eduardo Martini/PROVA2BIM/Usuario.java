import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nomeUsu;

    private List<Serie> seriesFavoritas;
    private List<Serie> seriesAssistidas;
    private List<Serie> seriesDesejadas;

    public Usuario(String nomeUsu){
        this.nomeUsu = nomeUsu;

        this.seriesFavoritas = new ArrayList<>();
        this.seriesAssistidas = new ArrayList<>();
        this.seriesDesejadas = new ArrayList<>();
    }
    public String getNomeUsu() {
        return nomeUsu;
    }
    
    public void exibirNome(){
        System.out.println("nome do Usuario: " +nomeUsu);
    }
}
