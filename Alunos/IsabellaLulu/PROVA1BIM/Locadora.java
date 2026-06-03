public class Locadora {

     Locacao[] locacoes = new Locacao[10];
    private int total = 0;

    public void adicionarLocacao(Locacao l) {
        if (total < 10) {
            locacoes[total++] = l;
            System.out.println("Locação registrada!");
        } else {
            System.out.println("Limite atingido!");
        }
    }

        public void listarAtivas() {
            System.out.println("\n=== LOCAÇÕES ATIVAS ===");

            for (int i = 0; i < total; i++) {
                if (!locacoes[i].isDevolvido()) {
                    locacoes[i].mostrar();
                }
            }
        }

        public void devolverLocacao(int i) {
        if (i >= 0 && i < total) {
            locacoes[i].devolver();
            System.out.println("Devolução feita!");
        } else {
            System.out.println("Índice inválido!");
        }
    }
}