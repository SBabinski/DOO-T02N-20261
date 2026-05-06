package objetos;

import java.time.LocalDate;
import java.util.List;

public class ProcessaPedido {

    public Pedido processar(LocalDate dataCriacao, Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {

        LocalDate vencimento = dataCriacao.plusDays(3);
        
        
        Pedido novoPedido = new Pedido(dataCriacao, null, vencimento, cliente, vendedor, loja, itens);
        
        System.out.println("Pedido processado com sucesso!");
        return novoPedido;
    }

    
    public boolean confirmarPagamento(Pedido pedido) {
        LocalDate hoje = LocalDate.now();
        
        if (hoje.isBefore(pedido.getDataVencimentoReserva()) || hoje.isEqual(pedido.getDataVencimentoReserva())) {
            pedido.setDataPagamento(hoje);
            System.out.println("Pagamento confirmado!");
            return true;
        } else {
            System.out.println("Erro: Reserva vencida. Não é possível confirmar pagamento.");
            return false;
        }
    }
}
