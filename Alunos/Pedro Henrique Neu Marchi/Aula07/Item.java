public class Item {

    int id;
    String nome;
    String tipo;
    double valor;

    public void gerarDescricao() {
        System.out.printf("ID: %d | Nome: %s | Tipo: %s | Valor: R$ %.2f%n",
                id, nome, tipo, valor);
    }
}
