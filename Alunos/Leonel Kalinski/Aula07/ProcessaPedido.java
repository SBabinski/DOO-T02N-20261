package objetos;

import java.util.Date;


public class ProcessaPedido {

    public Pedido processar(Cliente c, Vendedor v, Loja l) {
        Pedido p = new Pedido(1, c, v, l);

       
        p.adicionarItem(new Item(3, "Rosa", "Flor", 10));
        p.adicionarItem(new Item(2, "Vaso", "Acessório", 20));

        Date vencimento = new Date(System.currentTimeMillis() + 86400000);
        p.setDataVencimentoReserva(vencimento);

        if (confirmarPagamento(p)) {
            p.setDataPagamento(new Date());
            System.out.println("Pagamento confirmado!");
        } else {
            System.out.println("Reserva vencida!");
        }

        return p;
    }

    private boolean confirmarPagamento(Pedido p) {
        Date agora = new Date();
        return agora.before(p.getDataVencimentoReserva());
    }
}