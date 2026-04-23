import java.util.ArrayList;

public class Locadora {

    private ArrayList<Locacao> locacoes;
    private int capacidadeMaxima = 10;

    public Locadora() {
        this.locacoes = new ArrayList<>();
    }

    public void adicionarLocacao(Locacao l) {
        if (locacoes.size() < capacidadeMaxima) {
            locacoes.add(l);
            System.out.println("Locação adicionada com sucesso!");
        } else {
            System.out.println("Limite de locações atingido!");
        }
    }

    public void listarPendentes() {
        System.out.println("\n===Locações Pendentes===");

        boolean encontrou = false;

        for (Locacao l : locacoes) {
            if (!l.isDevolvido()) {
                l.mostrarDados();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma locação pendente.");
        }
    }

    public ArrayList<Locacao> getLocacoes() {
        return locacoes;
    }

}