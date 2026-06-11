package objetos;

import java.time.LocalDate;

public class Revista extends Item {
    private int nEdicao;

    public Revista(String titulo, LocalDate dataPubli, int nEdicao) {
        super(titulo, dataPubli);
        this.nEdicao = nEdicao;
    }

    public int getnEdicao() {
        return nEdicao;
    }

    public void setnEdicao(int nEdicao) {
        this.nEdicao = nEdicao;
    }

    @Override
    public void exibir(){
        super.exibir();
        System.out.printf(" - Ediçaõ(a): %d.\n", nEdicao);
    }
}
