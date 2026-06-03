import java.time.LocalDate;
import java.util.ArrayList;

public class Testes {
    static ArrayList<Loja> lojas = new ArrayList<>();

    public static void rodar() {
        for (int i = 0; i < lojas.size(); i++) {
            lojas.get(i).apresentarse();
        }
    }

    // ── TESTE 1: Gerente ──────────────────────────────────────────────
    public static void testarGerente() {
        System.out.println("===== TESTE GERENTE =====");
        Endereco endereco = new Endereco("PR", "Cafelandia", "98", "Parque", "Rua das Flores", "Apto 2");
        Loja loja = new Loja("Copacol", "Copacol Ltda", "12.398.142/0001-98", endereco);
        Gerente gerente = new Gerente("Carlos", 40, endereco);
        loja.adicionarFuncionario(gerente);

        // Três lançamentos de salário
        gerente.receberSalario();
        gerente.receberSalario();
        gerente.receberSalario();

        gerente.apresentarse();
        System.out.println("Media salarial: R$ " + gerente.calcularMedia());
        System.out.println("Bonus: R$ " + gerente.calcularBonus());
        System.out.println();
    }

    // ── TESTE 2: Endereco ─────────────────────────────────────────────
    public static void testarEndereco() {
        System.out.println("===== TESTE ENDERECO =====");
        Endereco endereco = new Endereco("SP", "Sao Paulo", "200", "Centro", "Av. Paulista", "Sala 5");
        endereco.apresentarLogradouro();
        System.out.println();
    }

    // ── TESTE 3: Item ─────────────────────────────────────────────────
    public static void testarItem() {
        System.out.println("===== TESTE ITEM =====");
        Item item1 = new Item("001", "Notebook", "Eletronico", 3500.00);
        Item item2 = new Item("002", "Mouse", "Eletronico", 150.00);
        Item item3 = new Item("003", "Cadeira Gamer", "Movel", 1200.00);
        System.out.println(item1.gerarDescricao());
        System.out.println(item2.gerarDescricao());
        System.out.println(item3.gerarDescricao());
        System.out.println();
    }

    // ── TESTE 4: Pedido e ProcessaPedido ──────────────────────────────
    public static void testarPedido() {
        System.out.println("===== TESTE PEDIDO =====");
        Endereco endereco = new Endereco("PR", "Cafelandia", "98", "Parque", "Rua das Flores", "Apto 2");
        Cliente cliente = new Cliente("Maria", 30, endereco);
        Vendedor vendedor = new Vendedor("Joao", 25, endereco);
        Loja loja = new Loja("Copacol", "Copacol Ltda", "12.398.142/0001-98", endereco);
        loja.adicionarFuncionario(vendedor);

        ArrayList<Item> itens = new ArrayList<>();
        itens.add(new Item("001", "Notebook", "Eletronico", 3500.00));
        itens.add(new Item("002", "Mouse", "Eletronico", 150.00));

        // Teste reserva válida (criado hoje, vence em 3 dias)
        System.out.println("-- Reserva valida --");
        Pedido pedido1 = ProcessaPedido.processar("PED-001", LocalDate.now(),cliente, vendedor, loja, itens);
        ProcessaPedido.processarPagamento(pedido1);

        // Teste reserva vencida (criado há 5 dias)
        System.out.println("\n-- Reserva vencida --");
        Pedido pedido2 = ProcessaPedido.processar("PED-002", LocalDate.now().minusDays(5),cliente, vendedor, loja, itens);
        ProcessaPedido.processarPagamento(pedido2);
        System.out.println();
    }

    // ── TESTE 5: Herança ──────────────────────────────────────────────
    public static void testarHeranca() {
        System.out.println("===== TESTE HERANCA =====");
        Endereco endereco = new Endereco("PR", "Cafelandia", "10", "Centro", "Rua A", "Casa");
        Vendedor vendedor = new Vendedor("Ana", 28, endereco);
        Gerente gerente = new Gerente("Roberto", 45, endereco);

        System.out.println("vendedor instanceof Funcionario: " + (vendedor instanceof Funcionario));
        System.out.println("gerente instanceof Funcionario: " + (gerente instanceof Funcionario));
        System.out.println("Bonus vendedor (20%): R$ " + vendedor.calcularBonus());
        System.out.println("Bonus gerente (35%): R$ " + gerente.calcularBonus());
        System.out.println();
    }

    public static void main(String[] args) {
        testarEndereco();
        testarItem();
        testarGerente();
        testarHeranca();
        testarPedido();
    }
}