import java.util.ArrayList;
import java.util.Scanner;
import Objetos.Resultados;

public class SuperCalculadora {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Double> vendas = new ArrayList<Double>();

    public static void main(String[] args) {
        Objetos.Resultados.clearTerminal();
        menu();
    }


    public static void menu() {
    
        int opcao = -1;

        do {
            System.out.println("================================");
            System.out.println("( 1 ) Calcular valor Total ");
            System.out.println("( 2 ) Registro de Vendas ");
            System.out.println("( 0 ) Sair ");
            System.out.println("================================");
            
            System.out.printf("Informe uma opcao: ");
            opcao = scan.nextInt();
            validaOpcao(opcao);

        } while (opcao != 0);
    }

    public static void validaOpcao(int opcao) {

        if (opcao == 1) {
            calculaTotal();
        } else if (opcao == 2) {
            Objetos.Resultados.mostraVendas(vendas);
        } else if (opcao == 0) {
            System.out.println("Saindo...");
        } else {
            Objetos.Resultados.clearTerminal();
            System.out.println("================================");
            System.out.println("Opcao invalida, tente novamente.");
            menu();
        }
    }

    public static void calculaTotal() {
        
        System.out.println("================================");
        
        System.out.printf("Informe o valor do produto: ");
        float valorProduto = scan.nextFloat();

        System.out.printf("Informe a quantidade: ");
        int quantidade = scan.nextInt();

        double valorTotal = valorProduto * quantidade;
        Objetos.Resultados.mostraTotalCompra(valorProduto, quantidade, valorTotal);    
        if (quantidade >= 10) {
            valorTotal = calculaDesconto(valorTotal);
        }

        calculaTroco(valorTotal);
    }

    public static void calculaTroco(double valorTotal) {

        System.out.println("================================");
        System.out.println("...Calcular Troco... ");        

        System.out.print("Informe o valor pago: ");
        double valorPago = scan.nextDouble();

        double troco = valorPago - valorTotal;

        if (troco < 0) {
            System.out.printf("Valor insuficiente, faltam: %.2f", Math.abs(troco));
            System.out.println();
        } else {
            Objetos.Resultados.mostraTroco(valorTotal, valorPago, troco);
            vendas.add(valorTotal);
        }

    }

    public static double calculaDesconto(double valorTotal) {

        double valorDesconto = 95;
        double desconto = (valorTotal * valorDesconto) / 100;
        Objetos.Resultados.mostraDesconto(desconto);
    
        return desconto;
    }


}