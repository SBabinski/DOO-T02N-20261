package objetos;

import java.time.LocalDate;

public class Livro extends Item {
    private String autor;

    public Livro(String titulo, LocalDate dataPubli, String autor) {
        super(titulo, dataPubli);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public  void exibir(){
        super.exibir();
        System.out.printf(" - Autor(a): %s.\n", autor);
    }
    
}
