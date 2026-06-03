import java.util.ArrayList;
import java.util.List;

public class ProcessaPedido {
    private static int proximoId = 1;

    public static Pedido processar(Cliente cliente, Pessoa funcionario, Loja loja, List<Item> itens) {
        Pedido pedido = new Pedido(proximoId++, cliente, funcionario, loja, itens);
        
        if (confirmarPagamento(pedido)) {
            System.out.println("Pedido processado e pagamento confirmado!");
            pedido.gerarDescricaoVenda();
            return pedido;
        } else {
            System.out.println("Pedido rejeitado - reserva vencida!");
            return null;
        }
    }

    private static boolean confirmarPagamento(Pedido pedido) {
        return !pedido.isVencido();
    }
}