import java.util.Date;
 
public class ProcessaPedido {
    
    public Pedido processar(int id, Cliente cliente, Vendedor vendedor) {
        Date agora = new Date();    
        Date vencimento = new Date(agora.getTime() + 86400000);
 
        Pedido pedido = new Pedido(id, agora, null,vencimento, cliente, vendedor, vendedor.Loja);
        confirmarPagamento(pedido);
        return pedido;
    }
    
    private void confirmarPagamento(Pedido pedido) {

        Date agora = new Date();
        
        if (!agora.after(pedido.dataVencimentoReserva)) {
            pedido.dataPagamento = agora;
            if (pedido.cliente instanceof Cliente) {
                ((Cliente) pedido.cliente).adicionarCompra(pedido.calcularValorTotal());
            }
        }
    }
}
 