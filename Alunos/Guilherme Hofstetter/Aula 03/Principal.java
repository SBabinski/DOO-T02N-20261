import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Venda> registroDeVendas = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Registro de Vendas");
            System.out.println("[4] - Sair");

            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1: calcularTotal();
                    break;
                case 2: calcularTroco();
                    break;
                case 3: exibirRegistro();
                    break;
                case 4: System.out.println("Saindo!");
                    break;
                default: System.out.println("Opcão inválida!");
                    break;
            }
        } while (opcao != 4);

        scan.close();
    }

    private static void calcularTotal() {
        System.out.println("Digite a quantidade de plantas: ");
        int quantidade = scan.nextInt();

        System.out.println("Digite o preço da planta: ");
        double preco = scan.nextDouble();

        double valorTotal = quantidade * preco;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = valorTotal * 0.05;
            System.out.println("Desconto especial de 5% aplicado!");
        }

        double valorComDesconto = valorTotal - desconto;
        System.out.println("O preço final a pagar é: " + valorComDesconto);

        Venda venda = new Venda();
        venda.setQuantidade(quantidade);
        venda.setValorVenda(valorTotal);
        venda.setDescontoAplicado(desconto);
        
        registroDeVendas.add(venda);
        System.out.println("(Venda salva no registro!)");
    }

    private static void exibirRegistro() {
        if (registroDeVendas.isEmpty()) {
            System.out.println("Nenhuma venda foi realizada ainda.");
            return;
        }

        System.out.println("\nRegistro de vendas: ");
        for (int i = 0; i < registroDeVendas.size(); i++) {
            Venda v = registroDeVendas.get(i);
            
            double valorFinal = v.getValorVenda() - v.getDescontoAplicado();
            
            System.out.println("Venda " + (i + 1) + 
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