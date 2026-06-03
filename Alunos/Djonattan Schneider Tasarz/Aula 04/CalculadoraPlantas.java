import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculadoraPlantas {
    private static List<Venda> registroVendas = new ArrayList<>();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("CALCULADORA DA DONA GABRIELINHA");
        System.out.println("Loja de Plantas Exóticas");
        System.out.println();

        do {
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");   
            System.out.println("[3] - Registrar Venda");
            System.out.println("[4] - Consultar vendas por DIA");
            System.out.println("[5] - Consultar vendas por MÊS");
            System.out.println("[6] - Sair");     
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
                    System.out.println("Obrigado por usar a calculadora");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            System.out.println();
        } while (opcao != 6);

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