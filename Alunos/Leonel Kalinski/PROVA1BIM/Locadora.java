package objetos;

public class Locadora {
	private Locacao[] locacoes = new Locacao[10];
	private int cont = 0;
	
	public void adicionarLocacao(Locacao l) {
        if (cont < 10) {
            locacoes[cont++] = l;
        } else {
            System.out.println("limite de empresgtimos atingido");
        }
    }

    public void listarPendentes() {
        for (int i = 0; i < cont; i++) {
            if (!locacoes[i].isDevolvido()) {
                locacoes[i].exibir();
            }
        }
    }
}
