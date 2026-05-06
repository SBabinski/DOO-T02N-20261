import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(Cliente cliente, Vendedor vendedor, Loja loja){

        Date agora = new Date();
        Date vencimento = new Date(agora.getTime() + 86400000);

        Pedido pedido = new Pedido(1, agora, vencimento, cliente, vendedor, loja);

        pedido.itens.add(new Item(1, "Orquídea", "Planta", 50));
        pedido.itens.add(new Item(2, "Cacto", "Planta", 30));

        if(confirmarPagamento(pedido)){
            pedido.dataPagamento = new Date();
            System.out.println("Pagamento aprovado!");
        } else {
            System.out.println("Reserva vencida!");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido){
        Date agora = new Date();
        return agora.before(pedido.dataVencimentoReserva);
    }
}