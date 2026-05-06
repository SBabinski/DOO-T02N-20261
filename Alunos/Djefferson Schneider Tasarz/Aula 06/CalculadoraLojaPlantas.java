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

    public static void main(String[] args) {
        inicializarLoja();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("Bem-vindo à loja de plantas My Plant da Dona Gabrielinha");

        while (opcao != 8) {
            System.out.println("\n=== MENU ===");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Registro de Vendas");
            System.out.println("[4] - Buscar Vendas por Data");
            System.out.println("[5] - Apresentar Loja");
            System.out.println("[6] - Contar Clientes e Vendedores");
            System.out.println("[7] - Apresentar Vendedores e Clientes");
            System.out.println("[8] - Sair");
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
                    mostrarRegistroVendas();
                    break;
                case 4:
                    buscarVendasPorData(scanner);
                    break;
                case 5:
                    lojaMyPlant.apresentarse();
                    break;
                case 6:
                    System.out.println("Total de clientes: " + lojaMyPlant.contarClientes());
                    System.out.println("Total de vendedores: " + lojaMyPlant.contarVendedores());
                    break;
                case 7:
                    apresentarseTodos(scanner);
                    break;
                case 8:
                    System.out.println("Encerrando o sistema My Plant. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void inicializarLoja() {
        lojaMyPlant = new Loja("My Plant", "My Plant Ltda", "12.345.678/0001-99", 
                              "Cascavel", "Centro", "Rua das Flores, 123");
        
        Vendedor vendedor1 = new Vendedor("João Silva", 28, lojaMyPlant, 
                                         "Cascavel", "Centro", "Rua das Flores, 123", 2000.0);
        Vendedor vendedor2 = new Vendedor("Maria Santos", 32, lojaMyPlant, 
                                         "Cascavel", "Centro", "Rua das Flores, 123", 2200.0);
        
        lojaMyPlant.adicionarVendedor(vendedor1);
        lojaMyPlant.adicionarVendedor(vendedor2);
        
        Cliente cliente1 = new Cliente("Ana Oliveira", 25, "Cascavel", "Jardim", "Av. Brasil, 456");
        Cliente cliente2 = new Cliente("Pedro Costa", 40, "Cascavel", "Centro", "Rua Principal, 789");
        
        lojaMyPlant.adicionarCliente(cliente1);
        lojaMyPlant.adicionarCliente(cliente2);
    }

    private static void apresentarseTodos(Scanner scanner) {
        System.out.println("\n=== Vendedores ===");
        for (Vendedor vendedor : lojaMyPlant.vendedores) {
            vendedor.apresentarse();
            System.out.println("Média salarial: R$ " + String.format("%.2f", vendedor.calcularMedia()));
            System.out.println("Bônus: R$ " + String.format("%.2f", vendedor.calcularBonus()));
            System.out.println();
        }

        System.out.println("=== Clientes ===");
        for (Cliente cliente : lojaMyPlant.clientes) {
            cliente.apresentarse();
            System.out.println();
        }
    }

    private static void calcularPrecoTotal(Scanner scanner) {
        System.out.println("\n=== Calcular Preço Total ===");
        System.out.print("Informe a quantidade da planta: ");
        int quantidade = scanner.nextInt();

        System.out.print("Informe o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double total = quantidade * precoUnitario;
        
        if (quantidade > 10) {
            double desconto = total * 0.05;
            total = total - desconto;
            System.out.println("Desconto de 5% aplicado: R$ " + String.format("%.2f", desconto));
        }

        String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String venda = "Venda: " + quantidade + " plantas x R$ " + precoUnitario + 
                      " = R$ " + String.format("%.2f", total) + " (" + dataHoje + ")";
        registroVendas.add(venda);
        
        vendasPorData.put(dataHoje, vendasPorData.getOrDefault(dataHoje, 0) + quantidade);
        
        System.out.println("Preço total da venda: R$ " + String.format("%.2f", total));
    }

    private static void calcularTroco(Scanner scanner) {
        System.out.println("\n=== Calcular Troco ===");
        System.out.print("Informe o valor recebido pelo cliente: ");
        double valorRecebido = scanner.nextDouble();

        System.out.print("Informe o valor total da compra: ");
        double valorCompra = scanner.nextDouble();

        double troco = valorRecebido - valorCompra;

        System.out.println("Troco a ser devolvido: R$ " + String.format("%.2f", troco));
    }
   
    private static void mostrarRegistroVendas() {
        System.out.println("\n=== Registro de Vendas ===");
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (String venda : registroVendas) {
                System.out.println(venda);
            }
        }
    }
   
    private static void buscarVendasPorData(Scanner scanner) {
        System.out.println("\n=== Buscar Vendas por Data ===");
        System.out.print("Informe o DIA (1-31): ");
        int dia = scanner.nextInt();
        System.out.print("Informe o MÊS (1-12): ");
        int mes = scanner.nextInt();
        
        String dataBusca = String.format("%02d-%02d-", dia, mes);
        
        boolean encontrou = false;
        for (String venda : registroVendas) {
            if (venda.contains(dataBusca)) {
                System.out.println(venda);
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhuma venda encontrada para " + dia + "/" + mes);
        }
    }
}