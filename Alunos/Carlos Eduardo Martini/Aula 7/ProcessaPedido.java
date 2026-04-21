import java.util.Date;

public class ProcessaPedido {

    public void processar(Pedidos pedido) {
        System.out.println("--- Processando Pedido " + pedido.id + " ---");

        Date dataAtual = new Date();

        if (dataAtual.after(pedido.dataVencimento)) {
            System.out.println("ERRO: O pedido não pode ser processado vencido em: " + pedido.dataVencimento);
        } else {
            pedido.dataPagamento = dataAtual;
            System.out.println("SUCESSO: Pagamento confirmado em: " + pedido.dataPagamento);
            System.out.println("Cliente: " + pedido.cliente.nome + " | Vendedor: " + pedido.vendedor.nome);
        }
    }
}