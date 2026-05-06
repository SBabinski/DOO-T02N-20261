public class Item {

    int id;
    String nome;
    String tipo;
    double valor;
    int quantidade;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.quantidade = 1;
    }

    double getTotal() {
        return valor * quantidade;
    }

    void gerarDescricao() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Valor: R$ " + valor);
    }


    void gerarDescricaoPedido() {
        System.out.println(
            nome + " | Qtd: " + quantidade + " | Total: R$ " + getTotal()
        );
    }
}