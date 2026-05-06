import java.util.Date;
import java.util.ArrayList;

public class ProcessaPedido {

    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens) {
        Date dataAtual = new Date(); 
        
        
        long tresDiasEmMs = 3L * 24L * 60L * 60L * 1000L;
        Date dataVencimento = new Date(dataAtual.getTime() + tresDiasEmMs);

        Pedido novoPedido = new Pedido(id, dataAtual, dataVencimento, cliente, vendedor, loja);
        
  
        for (Item item : itens) {
            novoPedido.itens.add(item);
        }

        return novoPedido;
    }

    private void confirmarPagamento(Pedido pedido) {
        Date dataAtual = new Date();

        if (dataAtual.before(pedido.dataVencimentoReserva)) {
            System.out.println("Pagamento confirmado!");
        } else {
            System.out.println("Erro: Reserva vencida. Pagamento não pode ser realizado.");
        }
    }
}