package fag;
import java.util.Date;
import java.util.List;

public class ProcessaPedido {

    public Pedido processar(int id, Date criacao, Date vencimento, Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {
        System.out.println("\nProcessando pedido #" + id + "...");
        Pedido novoPedido = new Pedido(id, criacao, vencimento, cliente, vendedor, loja, itens);
        
        if (confirmarPagamento(vencimento)) {
            novoPedido.setDataPagamento(new Date()); 
            System.out.println("Pagamento aprovado!");
        } else {
            System.out.println("Reserva vencida! Pagamento não aprovado.");
        }
        return novoPedido;
    }

    private boolean confirmarPagamento(Date dataVencimentoReserva) {
        Date dataAtual = new Date();
        return !dataAtual.after(dataVencimentoReserva); 
    }

    public void testarConfirmacaoPagamento() {
        System.out.println("\n--- Teste Unitário: Confirmar Pagamento ---");
        Date dataVencida = new Date(System.currentTimeMillis() - 100000); 
        Date dataValida = new Date(System.currentTimeMillis() + 100000); 
        System.out.println("Teste Vencido (Espera False): " + confirmarPagamento(dataVencida));
        System.out.println("Teste Válido (Espera True): " + confirmarPagamento(dataValida));
    }
}