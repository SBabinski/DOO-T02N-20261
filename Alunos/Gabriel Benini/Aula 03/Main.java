package CalculadoraLoja;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static float valorTotal;
    public static ArrayList<String> registros = new ArrayList<String>();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int opcao = 1000;

        do {

            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Registro");
            System.out.println("[4] - Sair");
            opcao = scan.nextInt();

            switch(opcao) {

                case 1: calPrecoTotal(scan); break;
                case 2: calTroco(scan); break;
                case 3: mostrarRegistro(); break;
                case 4: opcao = 4; break;
                default: System.out.println("Selecione uma opção válida!"); break;

            }

        } while(opcao != 4);

    }

    public static void mostrarRegistro() {

        int tamanho = registros.size();

        if (tamanho == 0){
            System.out.println("Nenhum registro foi encontrado!");
            System.out.println();
            return;
        }

        for (int i = 0; i < tamanho; i++) {

            System.out.println((i + 1) + " - " + registros.get(i));

        }
    }

    public static void calPrecoTotal(Scanner scan) {

        int quantidade = 0;
        float valor = 0;

        System.out.println("Quantas plantas o cliente vai levar vai levar?");
        quantidade =  scan.nextInt();
        System.out.println("Defina o valor da planta!");
        valor = scan.nextFloat();

        int i = registros.size();
        valorTotal = quantidade * valor;

        if (quantidade > 10){
            float desconto = valorTotal * 0.05f;
            valorTotal = valorTotal - desconto;

            registros.add("Quantidade: " + quantidade + " / Valor inicial: " + (valorTotal + desconto) + " / Valor descontado: " + desconto + " / Valor total: " + valorTotal);

            System.out.println("O valor total com desconto de 5% ficou: " + valorTotal);
        } else {
            registros.add("Quantidade: " + quantidade + " / Valor total: " + valorTotal);

            System.out.println("O valor total da compra ficou: "+ valorTotal);
        }
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