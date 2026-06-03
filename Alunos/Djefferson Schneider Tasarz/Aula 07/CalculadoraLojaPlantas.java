import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CalculadoraLojaPlantas {
    private static List<String> registroVendas = new ArrayList<>();
    private static Map<String, Integer> vendasPorData = new HashMap<>();
    private static Loja lojaMyPlant;
    private static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        inicializarLoja();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("Bem-vindo à My Plant - Sistema Avançado!");

        while (opcao != 10) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Registro de Vendas");
            System.out.println("[4] - Buscar Vendas por Data");
            System.out.println("[5] - Apresentar Loja");
            System.out.println("[6] - Contar Pessoas");
            System.out.println("[7] - Apresentar Funcionários e Clientes");
            System.out.println("[8] - Criar Pedido");
            System.out.println("[9] - Teste ProcessaPedido");
            System.out.println("[10]- Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: calcularPrecoTotal(scanner); break;
                case 2: calcularTroco(scanner); break;
                case 3: mostrarRegistroVendas(); break;
                case 4: buscarVendasPorData(scanner); break;
                case 5: lojaMyPlant.apresentarse(); break;
                case 6: 
                    System.out.println("Clientes: " + lojaMyPlant.contarClientes() + 
                                     " | Vendedores: " + lojaMyPlant.contarVendedores() + 
                                     " | Gerentes: " + lojaMyPlant.contarGerentes());
                    break;
                case 7: apresentarseTodos(); break;
                case 8: criarPedido(scanner); break;
                case 9: testeProcessaPedido(); break;
                case 10: System.out.println("Até logo!"); break;
                default: System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    private static void inicializarLoja() {
        Endereco enderecoLoja = new Endereco("PR", "Cascavel", "Centro", "123", "Sala 01");
        lojaMyPlant = new Loja("My Plant", "My Plant Ltda", "12.345.678/0001-99", enderecoLoja);
        
        Endereco endVendedor = new Endereco("PR", "Cascavel", "Centro", "123", null);
        Vendedor v1 = new Vendedor("João Silva", 28, lojaMyPlant, endVendedor, 2000.0);
        Vendedor v2 = new Vendedor("Maria Santos", 32, lojaMyPlant, endVendedor, 2200.0);
        lojaMyPlant.adicionarVendedor(v1);
        lojaMyPlant.adicionarVendedor(v2);
        
        Endereco endGerente = new Endereco("PR", "Cascavel", "Centro", "456", "Apt 101");
        Gerente g1 = new Gerente("Carlos Lima", 45, lojaMyPlant, endGerente, 5000.0);
        lojaMyPlant.adicionarGerente(g1);
        
        Endereco endCliente1 = new Endereco("PR", "Cascavel", "Jardim", "456", null);
        Cliente c1 = new Cliente("Ana Oliveira", 25, endCliente1);
        Endereco endCliente2 = new Endereco("PR", "Cascavel", "Centro", "789", null);
        Cliente c2 = new Cliente("Pedro Costa", 40, endCliente2);
        lojaMyPlant.adicionarCliente(c1);
        lojaMyPlant.adicionarCliente(c2);
    }

    private static void apresentarseTodos() {
        System.out.println("\n=== FUNCIONÁRIOS ===");
        for (Vendedor v : lojaMyPlant.vendedores) {
            v.apresentarse();
            System.out.println("Média: R$" + String.format("%.2f", v.calcularMedia()) + 
                             " | Bônus: R$" + String.format("%.2f", v.calcularBonus()));
            System.out.println();
        }
        for (Gerente g : lojaMyPlant.gerentes) {
            g.apresentarse();
            System.out.println("Média: R$" + String.format("%.2f", g.calcularMedia()) + 
                             " | Bônus: R$" + String.format("%.2f", g.calcularBonus()));
            System.out.println();
        }
        
        System.out.println("=== CLIENTES ===");
        for (Cliente c : lojaMyPlant.clientes) {
            c.apresentarse();
            System.out.println();
        }
    }

    private static void criarPedido(Scanner scanner) {
        System.out.println("\n=== CRIAR PEDIDO ===");
        List<Item> itens = new ArrayList<>();
        itens.add(new Item(1, "Samambaia", "Planta", 25.50));
        itens.add(new Item(2, "Vaso cerâmica", "Acessório", 15.00));
        
        Cliente cliente = lojaMyPlant.clientes.get(0);
        Vendedor vendedor = lojaMyPlant.vendedores.get(0);
        
        Pedido pedido = ProcessaPedido.processar(cliente, vendedor, lojaMyPlant, itens);
        if (pedido != null) {
            pedidos.add(pedido);
        }
    }

    private static void testeProcessaPedido() {
        System.out.println("\n=== TESTE PROCESSA PEDIDO ===");
        List<Item> itens = new ArrayList<>();
        itens.add(new Item(999, "Teste", "Teste", 100.0));
        
        Cliente c = new Cliente("Teste Cliente", 30, 
                               new Endereco("PR", "Cascavel", "Centro", "000", null));
        Vendedor v = new Vendedor("Teste Vendedor", 25, lojaMyPlant,
                                 new Endereco("PR", "Cascavel", "Centro", "000", null), 2000);
        
        ProcessaPedido.processar(c, v, lojaMyPlant, itens);
    }

    private static void calcularPrecoTotal(Scanner scanner) {
        System.out.println("\n=== Calcular Preço Total ===");
        System.out.print("Quantidade: ");
        int qtd = scanner.nextInt();
        System.out.print("Preço unitário: ");
        double preco = scanner.nextDouble();
        double total = qtd * preco;
        if (qtd > 10) {
            double desc = total * 0.05;
            total -= desc;
            System.out.println("Desconto 5%: R$" + String.format("%.2f", desc));
        }
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        registroVendas.add("Venda: " + qtd + "x R$" + preco + " = R$" + String.format("%.2f", total) + " (" + data + ")");
        vendasPorData.put(data, vendasPorData.getOrDefault(data, 0) + qtd);
        System.out.println("Total: R$" + String.format("%.2f", total));
    }

    private static void calcularTroco(Scanner scanner) {
        System.out.println("\n=== Calcular Troco ===");
        System.out.print("Valor recebido: ");
        double recebido = scanner.nextDouble();
        System.out.print("Valor compra: ");
        double compra = scanner.nextDouble();
        System.out.println("Troco: R$" + String.format("%.2f", recebido - compra));
    }

    private static void mostrarRegistroVendas() {
        System.out.println("\n=== Registro Vendas ===");
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda.");
        } else {
            for (String v : registroVendas) {
                System.out.println(v);
            }
        }
    }

    private static void buscarVendasPorData(Scanner scanner) {
        System.out.println("\n=== Buscar por Data ===");
        System.out.print("Dia (1-31): ");
        int dia = scanner.nextInt();
        System.out.print("Mês (1-12): ");
        int mes = scanner.nextInt();
        String busca = String.format("%02d-%02d-", dia, mes);
        boolean achou = false;
        for (String v : registroVendas) {
            if (v.contains(busca)) {
                System.out.println(v);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma venda em " + dia + "/" + mes);
    }
}