public class Locadora {

    Locacao[] locacoes = new Locacao[10];
    int contador = 0;

    void adicionarLocacao(Locacao l) {
        if (contador < 10) {
            locacoes[contador++] = l;
            System.out.println("Locacao cadastrada.");
        } else {
            System.out.println("Limite de locacoes atingido.");
        }
    }

    void listarAtivas() {
        for (int i = 0; i < contador; i++) {
            if (!locacoes[i].devolvido) {
                locacoes[i].mostrar();
            }
        }
    }
}