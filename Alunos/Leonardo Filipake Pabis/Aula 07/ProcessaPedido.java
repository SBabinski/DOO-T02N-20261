

public class ProcessaPedido {
    public static Pedido processar(int id, Clientes cliente, Vendedor vendedor, Lojas loja, String dataVencimento) {
        Pedido pedido = new Pedido(id, cliente, vendedor, loja, dataVencimento);
        return pedido;
    }

    private static boolean confirmarPagamento(Pedido pedido) {
        if (Date.dataAtualDate().isBefore(pedido.getDataVencimentoReservaDate())){
            System.out.println("Pagamento dentro do prazo");
            return true;
        } else {
            System.out.println("Pagamento fora do prazo");
            return false;
        }
    }

    public static boolean pagamentoConfirmado(Pedido pedido){
        return confirmarPagamento(pedido);
    }
}
