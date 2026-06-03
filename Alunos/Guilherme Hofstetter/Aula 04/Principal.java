import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Principal {
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Venda> registroDeVendas = new ArrayList<>();
    private static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n[1] - Calcular Preço Total e Registrar Venda");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Registro Geral de Vendas");
            System.out.println("[4] - Buscar Total de Vendas por Data");
            System.out.println("[5] - Sair");

            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1: calcularTotal();
                    break;
                case 2: calcularTroco();
                    break;
                case 3: exibirRegistro();
                    break;
                case 4: buscarVendasPorData();
                    break;
                case 5: System.out.println("Saindo!");
                    break;
                default: System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 5);

        scan.close();
    }

    private static void calcularTotal() {
        System.out.println("Digite a data da venda (dd/MM/yyyy): ");
        String dataString = scan.nextLine();
        
        LocalDate dataVenda = LocalDate.parse(dataString, formatador);

        System.out.println("Digite a quantidade de plantas: ");
        int quantidade = scan.nextInt();

        System.out.println("Digite o preço da planta: ");
        double preco = scan.nextDouble();
        scan.nextLine();

        double valorTotal = quantidade * preco;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = valorTotal * 0.05;
            System.out.println("Desconto especial de 5% aplicado!");
        }

        double valorComDesconto = valorTotal - desconto;
        System.out.println("O preço final a pagar é: " + valorComDesconto);

        Venda venda = new Venda();
        venda.setDataVenda(dataVenda);
        venda.setQuantidade(quantidade);
        venda.setValorVenda(valorTotal);
        venda.setDescontoAplicado(desconto);
        
        registroDeVendas.add(venda);
        System.out.println("(Venda salva no registro!)");
    }

    private static void buscarVendasPorData() {
        System.out.println("Digite a data que deseja buscar (dd/MM/yyyy): ");
        String dataString = scan.nextLine();
        
        LocalDate dataBusca = LocalDate.parse(dataString, formatador);
        
        int totalPlantasVendidas = 0;
        int quantidadeDeVendas = 0;
        double faturamentoDoDia = 0.0;

        for (Venda v : registroDeVendas) {
            if (v.getDataVenda().equals(dataBusca)) {
                quantidadeDeVendas++;
                totalPlantasVendidas += v.getQuantidade();
                faturamentoDoDia += (v.getValorVenda() - v.getDescontoAplicado());
            }
        }

        System.out.println("\n--- Resumo de Vendas: " + dataBusca.format(formatador) + " ---");
        if (quantidadeDeVendas == 0) {
            System.out.println("Nenhuma venda registrada neste dia.");
        } else {
            System.out.println("Total de vendas realizadas (recibos): " + quantidadeDeVendas);
            System.out.println("Total de plantas vendidas: " + totalPlantasVendidas);
            System.out.println("Faturamento total do dia: " + faturamentoDoDia);
        }
        System.out.println("--------------------------------------");
    }

    private static void exibirRegistro() {
        if (registroDeVendas.isEmpty()) {
            System.out.println("Nenhuma venda foi realizada ainda.");
            return;
        }

        System.out.println("\nRegistro geral de vendas: ");
        for (int i = 0; i < registroDeVendas.size(); i++) {
            Venda v = registroDeVendas.get(i);
            double valorFinal = v.getValorVenda() - v.getDescontoAplicado();
            
            System.out.println("Venda " + (i + 1) + 
                               " | Data: " + v.getDataVenda().format(formatador) +
                               " | Quantidade: " + v.getQuantidade() + 
                               " | Valor Bruto: " + v.getValorVenda() + 
                               " | Desconto: " + v.getDescontoAplicado() + 
                               " | Valor Final: " + valorFinal);
        }
    }

    private static void calcularTroco() {
        System.out.println("Digite o valor pago: ");
        double valorPago = scan.nextDouble();

        System.out.println("Digite o preço total: ");
        double total = scan.nextDouble();
        double troco = valorPago - total;

        if (troco < 0) {
            System.out.println("Valor pago é insuficiente. Faltam: " + (-troco));
        } else {
            System.out.println("O troco é: " + troco);
        }
    }
}