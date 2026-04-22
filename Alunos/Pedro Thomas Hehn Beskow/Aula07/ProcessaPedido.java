import java.time.LocalDate;

public class ProcessaPedido {

    // recebe os dados necessários, monta o pedido e tenta confirmar o pagamento
    public Pedido processar(int       id,
                            LocalDate dataCriacao,
                            LocalDate dataPagamento,
                            LocalDate dataVencimentoReserva,
                            Cliente   cliente,
                            Vendedor  vendedor,
                            Loja      loja,
                            Item[]    itens) {

        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento,
                                   dataVencimentoReserva, cliente,
                                   vendedor, loja, itens);

        System.out.println("\nProcessando pedido #" + id + "...");

        if (confirmarPagamento(pedido)) {
            System.out.println("✔  Pagamento confirmado! Pedido processado com sucesso.");
            pedido.gerarDescricaoVenda();
        } else {
            System.out.println("✘  Pagamento NÃO confirmado. Reserva vencida em "
                + pedido.dataVencimentoReserva.format(Pedido.FORMATO_DATA)
                + " — pedido cancelado.");
        }

        return pedido;
    }

    // pagamento só é confirmado se a data atual não ultrapassou o vencimento da reserva
    private boolean confirmarPagamento(Pedido pedido) {
        return !LocalDate.now().isAfter(pedido.dataVencimentoReserva);
    }
}
