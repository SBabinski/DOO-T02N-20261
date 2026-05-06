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
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("Bem-vindo à loja de plantas da Dona Gabrielinha ");

        while (opcao != 5) {
            System.out.println("\nMENU ");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Registro de Vendas");
            System.out.println("[4] - Buscar Vendas por Data");
            System.out.println("[5] - Sair");
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
                    System.out.println("Encerrando a calculadora. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void calcularPrecoTotal(Scanner scanner) {
        System.out.println("\nCalcular Preço Total ");
        System.out.print("Informe a quantidade da planta: ");
        int quantidade = scanner.nextInt();

        System.out.print("Informe o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double total = quantidade * precoUnitario;
        
        if (quantidade > 10) {
            double desconto = total * 0.05;
            total = total - desconto;
            System.out.println("Desconto de 5% aplicado: R$ " + desconto);
        }

        String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String venda = "Venda: " + quantidade + " plantas x R$ " + precoUnitario + " = R$ " + total + " (" + dataHoje + ")";
        registroVendas.add(venda);
        
    
        vendasPorData.put(dataHoje, vendasPorData.getOrDefault(dataHoje, 0) + quantidade);
        
        System.out.println("Preço total da venda: R$ " + total);
    }

    private static void calcularTroco(Scanner scanner) {
        System.out.println("\nCalcular Troco ");
        System.out.print("Informe o valor recebido pelo cliente: ");
        double valorRecebido = scanner.nextDouble();

        System.out.print("Informe o valor total da compra: ");
        double valorCompra = scanner.nextDouble();

        double troco = valorRecebido - valorCompra;

        System.out.println("Troco a ser devolvido: R$ " + troco);
    }
    
    private static void mostrarRegistroVendas() {
        System.out.println("\nRegistro de Vendas");
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (String venda : registroVendas) {
                System.out.println(venda);
            }
        }
    }
    
    private static void buscarVendasPorData(Scanner scanner) {
        System.out.println("\nBuscar Vendas por Data");
        System.out.print("Informe o DIA (1-31): ");
        int dia = scanner.nextInt();
        System.out.print("Informe o MÊS (1-12): ");
        int mes = scanner.nextInt();
        
        String dataBusca = String.format("%02d-%02d-%%%%", dia, mes);
        
        boolean encontrou = false;
        for (String venda : registroVendas) {
            if (venda.contains(String.format("%02d-%02d-", dia, mes))) {
                System.out.println(venda);
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhuma venda encontrada para " + dia + "/" + mes);
        }
    }
}
