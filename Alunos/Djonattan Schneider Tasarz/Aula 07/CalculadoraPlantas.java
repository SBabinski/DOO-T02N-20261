import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CalculadoraPlantas {
    private static List<Venda> registroVendas = new ArrayList<>();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static int proximoIdPedido = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("CALCULADORA DA DONA GABRIELINHA");
        System.out.println("Loja de Plantas Exóticas");
        System.out.println();

        Endereco enderecoLoja = new Endereco("PR", "Cascavel", "Centro", "123", "Rua das Flores");
        Loja lojaMyPlant = new Loja(
                "My Plant",
                "My Plant Comércio de Plantas Exóticas Ltda",
                "12.345.678/0001-99",
                enderecoLoja
        );

        Endereco enderecoVendedor = new Endereco("PR", "Cascavel", "Centro", "123", "Rua das Flores");
        Vendedor vendedor1 = new Vendedor(
                "João Silva",
                28,
                lojaMyPlant,
                enderecoVendedor,
                2500.0
        );

        Endereco enderecoCliente = new Endereco("PR", "Cascavel", "Centro", "200", "Rua das Flores");
        Cliente cliente1 = new Cliente(
                "Ana Costa",
                35,
                enderecoCliente
        );

        lojaMyPlant.adicionarVendedor(vendedor1);
        lojaMyPlant.adicionarCliente(cliente1);

        do {
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Registrar Venda");
            System.out.println("[4] - Consultar vendas por DIA");
            System.out.println("[5] - Consultar vendas por MÊS");
            System.out.println("[6] - Apresentar Vendedor");
            System.out.println("[7] - Apresentar Cliente");
            System.out.println("[8] - Apresentar Loja");
            System.out.println("[9] - Criar Pedido");
            System.out.println("[10] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    registrarVenda(scanner);
                    break;
                case 4:
                    consultarPorDia(scanner);
                    break;
                case 5:
                    consultarPorMes(scanner);
                    break;
                case 6:
                    vendedor1.apresentarSe();
                    System.out.printf("Média salarial: R$ %.2f%n", vendedor1.calcularMedia());
                    System.out.printf("Bônus: R$ %.2f%n%n", vendedor1.calcularBonus());
                    break;
                case 7:
                    cliente1.apresentarSe();
                    break;
                case 8:
                    lojaMyPlant.apresentarSe();
                    System.out.println("Quantidade de clientes: " + lojaMyPlant.contarClientes());
                    System.out.println("Quantidade de vendedores: " + lojaMyPlant.contarVendedores());
                    break;
                case 9:
                    criarPedidoFake(cliente1, vendedor1, lojaMyPlant);
                    break;
                case 10:
                    System.out.println("Obrigado por usar a calculadora");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            System.out.println();
        } while (opcao != 10);

        scanner.close();
    }

    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário (R$): ");
        double precoUnitario = scanner.nextDouble();

        double totalSemDesconto = quantidade * precoUnitario;
        double desconto = 0.0;
        double totalComDesconto = totalSemDesconto;

        if (quantidade > 10) {
            desconto = totalSemDesconto * 0.05;
            totalComDesconto = totalSemDesconto - desconto;
            System.out.println("Desconto especial de 5% aplicado!");
        }

        System.out.printf("Preço total sem desconto: R$ %.2f%n", totalSemDesconto);
        System.out.printf("Desconto aplicado: R$ %.2f%n", desconto);
        System.out.printf("Preço total com desconto: R$ %.2f%n", totalComDesconto);
    }

    public static void calcularTroco(Scanner scanner) {
        System.out.print("Valor total da compra (R$): ");
        double total = scanner.nextDouble();
        System.out.print("Valor pago pelo cliente (R$): ");
        double pago = scanner.nextDouble();

        if (pago < total) {
            System.out.println("Dinheiro insuficiente!");
        } else {
            double troco = pago - total;
            System.out.printf("Troco: R$ %.2f%n", troco);
        }
    }

    public static void registrarVenda(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas vendidas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário (R$): ");
        double precoUnitario = scanner.nextDouble();

        double valorVenda = quantidade * precoUnitario;
        double desconto = 0.0;

        if (quantidade > 10) {
            desconto = valorVenda * 0.05;
            valorVenda -= desconto;
            System.out.println("Desconto especial de 5% aplicado e registrado!");
        }

        LocalDate data = LocalDate.now();

        Venda venda = new Venda(quantidade, precoUnitario, valorVenda, desconto, data);
        registroVendas.add(venda);

        System.out.println("Venda registrada com sucesso!");
        System.out.println("Data: " + data.format(formatter));
        System.out.printf("Quantidade: %d plantas%n", quantidade);
        System.out.printf("Valor final: R$ %.2f%n", valorVenda);
        System.out.printf("Desconto: R$ %.2f%n", desconto);
    }

    public static void consultarPorDia(Scanner scanner) {
        System.out.print("Digite a data (dd/MM/yyyy): ");
        scanner.nextLine();
        String dataStr = scanner.nextLine();
        LocalDate dataBusca = LocalDate.parse(dataStr, formatter);

        int totalVendas = 0;

        for (Venda v : registroVendas) {
            if (v.getData().equals(dataBusca)) {
                totalVendas += v.getQuantidade();
            }
        }

        System.out.println("Total de plantas vendidas no dia: " + totalVendas);
    }

    public static void consultarPorMes(Scanner scanner) {
        System.out.print("Digite o mês (1-12): ");
        int mes = scanner.nextInt();

        int totalVendas = 0;

        for (Venda v : registroVendas) {
            if (v.getData().getMonthValue() == mes) {
                totalVendas += v.getQuantidade();
            }
        }

        System.out.println("Total de plantas vendidas no mês: " + totalVendas);
    }

    public static void criarPedidoFake(Cliente cliente, Vendedor vendedor, Loja loja) {
        Endereco enderecoItem = new Endereco("PR", "Cascavel", "Centro", "123", "Rua das Flores");
        Item item1 = new Item(1, "Orquídea Azul", "Planta", 59.90);
        Item item2 = new Item(2, "Monstera Variegata", "Planta", 149.90);
        Item[] itens = new Item[] { item1, item2 };

        Date dataCriacao = new Date();
        Date dataVencimentoReserva = new Date(System.currentTimeMillis() + 24L * 60L * 60L * 1000L);

        ProcessaPedido processaPedido = new ProcessaPedido();
        Pedido pedido = processaPedido.processar(
                proximoIdPedido++,
                dataCriacao,
                dataVencimentoReserva,
                cliente,
                vendedor,
                loja,
                itens
        );

        if (pedido != null) {
            System.out.printf("Pedido total: R$ %.2f%n", pedido.calcularValorTotal());
        }
    }

    static class Venda {
        private int quantidade;
        private double precoUnitario;
        private double valorFinal;
        private double desconto;
        private LocalDate data;

        public Venda(int quantidade, double precoUnitario, double valorFinal, double desconto, LocalDate data) {
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
            this.valorFinal = valorFinal;
            this.desconto = desconto;
            this.data = data;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public LocalDate getData() {
            return data;
        }
    }
}