
public class ProcessaPedido {

    public Pedido processar(Cliente cliente, Vendedor vendedor, Loja loja, int qtd, double preco) {

        Pedido pedido = new Pedido(1, cliente, vendedor, loja);

        // criando itens
        for (int i = 0; i < qtd; i++) {
            pedido.adicionarItem(new Item(i + 1, "Planta", "Flor", preco));
        }

        return pedido;
    }


    public double calcularTroco(double recebido, double total) {
    return recebido - total;
    }
}