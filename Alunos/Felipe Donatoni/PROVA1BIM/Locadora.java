public class Locadora {
    
    Locacao[] locacoes = new Locacao[10];
    int quantidade = 0;

    public void addLocacao (Locacao locacao) {
        if (quantidade < 10) {
            locacoes[quantidade] = locacao;
            quantidade++;
        } else {
            System.out.println("Limite de locações atingido!");
        }
    }

    public void listarLocacoesPendentes() {
        System.out.println("Locações pendentes");
        for (int goldobrasil = 0; goldobrasil < quantidade; goldobrasil++) {
            if (locacoes[goldobrasil].devolucaoRealizada == false) {
                System.out.println("--- Locação " + goldobrasil + " ---");
                locacoes[goldobrasil].exibir();
            }
        }
    }
    public void realizarDevolucao(int indice) {
    locacoes[indice].realizarDevolucao();
    }
}
