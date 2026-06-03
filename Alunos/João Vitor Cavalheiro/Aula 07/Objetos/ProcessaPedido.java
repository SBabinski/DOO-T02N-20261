import java.time.LocalDate;

public class ProcessaPedido extends Pedido{

    public ProcessaPedido(Cliente cliente, LocalDate dataCriacao, LocalDate dataPagamento,
            LocalDate dataVencimentoReserva, int id, Loja loja, Vendedor vendedor) {
        super(cliente, dataCriacao, dataPagamento, dataVencimentoReserva, id, loja, vendedor);
    }

    public Pedido procesar(LocalDate dataCriacao,
        LocalDate dataPAgamento,
        LocalDate dataVencimentoReserva){

        if(confirmarPagamento(dataVencimentoReserva, dataPagamento)){
            Pedido pedido = new Pedido(cliente, 
                dataCriacao, 
                dataPagamento, 
                dataVencimentoReserva,
                id, 
                loja, 
                vendedor);
            System.out.println("Pagamento Aprovado");
            return pedido;
        }else{
            System.out.println("Pagamento Vencido");
            return null;
        }
    }

    private boolean confirmarPagamento(LocalDate dataVencimentoReserva, LocalDate dataPagamento){
        return !dataPagamento.isAfter(dataVencimentoReserva);
    }
}
