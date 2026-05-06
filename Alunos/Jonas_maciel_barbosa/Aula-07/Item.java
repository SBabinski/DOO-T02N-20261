public class Item {
    int id;
    String nome;
    String tipo;
    double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        if (valor > 0) {
            this.valor = valor;
        } else {
            System.out.println("Valor do item inválido. Verifique e tente novamente.");
        }
    }

    public void gerarDescricao() {
        System.out.println("ID: " + id + " | Nome: " + nome + " | Tipo: " + tipo + " | Valor: " + valor);
    }
}
