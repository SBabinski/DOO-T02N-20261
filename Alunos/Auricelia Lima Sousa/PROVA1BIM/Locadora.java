public class Locadora {
    public Locacao[] lista = new Locacao[10];
    public int contador = 0;

    public void adicionarLocacao(Locacao l) { 
        if (contador < 10) {
            lista[contador] = l;
            contador++;
            System.out.println("Locacao registrada!");
        } else {
            System.out.println("Locadora cheia!");
        }
    }

    public void listarAtivas() {
        for (int i = 0; i < contador; i++) {
            if (!lista[i].devolvida) {
                lista[i].exibirDadosLocacao();
            }
        }
    }
}