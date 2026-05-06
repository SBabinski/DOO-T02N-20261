package Aula03;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraProduto {
    static Scanner scan = new Scanner(System.in);
    static List<String> vendas = new ArrayList<>();

    public static void main(String[] args){
        mostrarMenu();
    }

    public static void mostrarMenu() {
        int op = -1;
        do {
            System.out.println("----Menu Calculadora----");
            System.out.println("1- Calcular Valor Total");
            System.out.println("2- Calcular Troco");
            System.out.println("3- Registro de Vendas");
            System.out.println("0- Sair");
            op = scan.nextInt();
            scan.nextLine();
            validarEscolha(op);
        } while (op != 0);
        System.out.println("Sistema Encerrado!");
    }
    public static void validarEscolha(int op){
        switch (op) {
            case 1:
                calcularValorTotal();
                break;

            case 2:
                calcularTroco();
                break;

            case 3:
                mostrarReistroVendas();
                break;

            case 0:
                break;
            default:
                System.out.println("Opção Inválida! Digite Novamente!");
        }
    }
    public static void calcularValorTotal(){
        System.out.println("Valor do Produto: ");
        double valor = scan.nextDouble();
        System.out.println("Quantidade do Produto: ");
        int quantidade = scan.nextInt();
        scan.nextLine();
        double valorTotal = quantidade*valor;
        double desconto = 0;

        if(quantidade>10) {
            desconto = valorTotal * 0.05;
            valorTotal -= desconto;
        }
        System.out.println("Valor Total: " + valorTotal);
        System.out.println("Desconto Total: " + desconto);

        String venda = "Quantidade: " + quantidade +
                       " | Preço: " + valor +
                       " | Desconto: " + desconto +
                       " | Total: " + valorTotal;

        vendas.add(venda);
    }

    public static void calcularTroco(){
        System.out.println("Valor da Compra: ");
        double compra = scan.nextDouble();
        System.out.println("Valor Entregue pelo Cliente: ");
        double valorCliente = scan.nextDouble();
        double troco = valorCliente - compra;
        if (troco>0){
            System.out.println("Valor do Troco:"+troco);
        }
        else if (troco == 0) {
            System.out.println("Valor Correto! Sem Necessidade de Troco");

        } else {
            System.out.println("Faltando um Total de " + (troco * -1) + " para completar a compra.");
        }

    }

    public static void mostrarReistroVendas(){
        System.out.println("\n--- Resgistro de Vendas ---");

        if(vendas.isEmpty()){
            System.out.println("Nenhuma venda registrada!");
        }
        else{
            for(String venda : vendas){
                System.out.println(venda);
            }
        }
    }
}
