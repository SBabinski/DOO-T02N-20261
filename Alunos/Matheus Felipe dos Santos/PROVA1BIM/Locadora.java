public class Locadora {
    Locacao[] locacoes = new Locacao[10];
    int quantidade = 0;

    void adicionarLocacao(Locacao l) {
        if (quantidade < 10) {
            locacoes[quantidade] = l;
            quantidade++;
        } else {
            System.out.println("Limite de locações atingido");
        }
    }

    void listarLocacoesAtivas() {
        for (int i = 0; i < quantidade; i++) {
            if (!locacoes[i].devolvido) {
                locacoes[i].exibir();
                System.out.println("-------------------");
            }
        }
    }
}