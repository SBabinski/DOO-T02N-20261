package CalculadoraLoja;

import java.util.Scanner;

public class Main {

    public static float valorTotal;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int opcao = 1000;

        do {

            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            opcao = scan.nextInt();

            switch(opcao) {

                case 1: calPrecoTotal(scan); break;
                case 2: calTroco(scan); break;
                case 3: opcao = 3; break;
                default: System.out.println("Selecione uma opção válida!"); break;

            }

        } while(opcao != 3);

    }

    public static void calPrecoTotal(Scanner scan) {

        int quantidade = 0;
        float valor = 0;

        System.out.println("Quantas plantas o cliente vai levar vai levar?");
        quantidade =  scan.nextInt();
        System.out.println("Defina o valor da planta!");
        valor = scan.nextFloat();

        valorTotal = quantidade * valor;
        System.out.println("O valor total da compra ficou: "+ valorTotal);
        System.out.println();

    }

    public static void calTroco(Scanner scan) {

        if (valorTotal <= 0) {
            System.out.println("O valor total deve ser maior que zero!");
            System.out.println();
            return;
        }

        System.out.println("Quanto de dinheiro o cliente vai pagar");
        float dinheiro = scan.nextFloat();

        if (dinheiro <= valorTotal) {
            System.out.println("O cliente nao possui dinheiro suficiente!");
            System.out.println();
            return;
        }

        float troco = dinheiro - valorTotal;
        System.out.println("O troco do cliente ficou: " + troco);
        System.out.println();

    }
}