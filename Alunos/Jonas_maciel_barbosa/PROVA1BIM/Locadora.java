public class Locadora {

    private Locacao[] locacoes = new Locacao[10];
    private int totalLocacoes = 0;

    public boolean adicionarLocacao(Locacao locacao) {
        if (totalLocacoes >= 10) {
            System.out.println("Nao ha espaco para novas locacoes!");
            return false;
        }
        locacoes[totalLocacoes] = locacao;
        totalLocacoes++;
        System.out.println("Locacao registrada com sucesso!");
        return true;
    }

    public void listarSemDevolucao() {
        System.out.println(" *** Locacoes em aberto *** ");
        boolean achou = false;
        for (int i = 0; i < totalLocacoes; i++) {
            if (!locacoes[i].isDevolvido()) {
                System.out.println("Indice: " + i);
                locacoes[i].exibirDados();
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma locacao em aberto.");
        }
    }

    public Locacao getLocacao(int indice) {
        if (indice >= 0 && indice < totalLocacoes) {
            return locacoes[indice];
        }
        return null;
    }

    public int getTotalLocacoes() {
        return totalLocacoes;
    }
}
