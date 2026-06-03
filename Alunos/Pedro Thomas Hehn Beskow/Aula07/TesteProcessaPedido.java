import java.time.LocalDate;

// Testa o método processar() de ProcessaPedido em dois cenários:
//   1. Reserva dentro do prazo  → deve confirmar
//   2. Reserva vencida          → deve rejeitar
public class TesteProcessaPedido {

    public static void main(String[] args) {

        // ── dados compartilhados pelos dois testes ──────────────────────────
        Endereco endLoja = new Endereco("SP", "São Paulo", "Centro",
                                        "100", "Loja térreo");
        Loja loja = new Loja("My Plant",
                             "My Plant Comércio de Plantas Ltda.",
                             "12.345.678/0001-99", endLoja);

        Endereco endCliente = new Endereco("SP", "São Paulo", "Jardins",
                                           "500", "Apto 12");
        Cliente cliente = new Cliente("Mariana Lima", 45, endCliente);

        Endereco endVendedor = new Endereco("SP", "São Paulo", "Centro",
                                            "100", "");
        Vendedor vendedor = new Vendedor("Carlos Silva", 32, endVendedor,
                                         loja.nomeFantasia, 3000.00);

        Item[] itens = {
            new Item(1, "Orquídea Phalaenopsis", "Flor",  89.90),
            new Item(2, "Vaso de Cerâmica P",    "Vaso",  45.00),
            new Item(3, "Adubo Orgânico 500g",   "Insumo", 22.50)
        };

        ProcessaPedido processador = new ProcessaPedido();

        // ── Teste 1: reserva válida (vence daqui a 5 dias) ──────────────────
        System.out.println("=== TESTE 1: Reserva dentro do prazo ===");
        LocalDate vencimentoValido = LocalDate.now().plusDays(5);
        processador.processar(
            1001,
            LocalDate.now(),
            LocalDate.now(),
            vencimentoValido,
            cliente, vendedor, loja, itens
        );

        // ── Teste 2: reserva vencida (venceu há 3 dias) ─────────────────────
        System.out.println("\n=== TESTE 2: Reserva vencida ===");
        LocalDate vencimentoPassado = LocalDate.now().minusDays(3);
        processador.processar(
            1002,
            LocalDate.now().minusDays(10),
            null,
            vencimentoPassado,
            cliente, vendedor, loja, itens
        );
    }
}
