public class Locadora {
    private Locacao[] locacoes = new Locacao[10];
    private int totalLocacoes = 0;

    public boolean adicionarLocacao(Locacao locacao) {
        if (totalLocacoes >= 10) {
            System.out.println("Limite de locações atingido.");
            return false;
        }
        locacoes[totalLocacoes] = locacao;
        totalLocacoes++;
        System.out.println("Locação adicionada com sucesso.");
        return true;
    }

    public void mostrarLocacoes() {
        if (totalLocacoes == 0) {
            System.out.println("Nenhuma locação registrada.");
            return;
        }
        for (int i = 0; i < totalLocacoes; i++) {
            System.out.println("Locação " + (i + 1) + ":");
            locacoes[i].mostrarDetalhes();
            System.out.println();
        }
    }

    public void mostrarLocacoesPendentes() {
        boolean encontrou = false;
        for (int i = 0; i < totalLocacoes; i++) {
            if (!locacoes[i].isDevolvido()) {
                locacoes[i].mostrarDetalhes();
                System.out.println();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma locação pendente.");
        }
    }

    public void devolverLocacao(int index) {
        if (index < 0 || index >= totalLocacoes) {
            System.out.println("Índice de locação inválido.");
            return;
        }
        if (locacoes[index].isDevolvido()) {
            System.out.println("Locação já foi devolvida.");
            return;
        }
        locacoes[index].devolver();
        System.out.println("Locação devolvida com sucesso.");
    }

    public Locacao[] getLocacoes() { return locacoes; }
    public int getTotalLocacoes() { return totalLocacoes; }
}
