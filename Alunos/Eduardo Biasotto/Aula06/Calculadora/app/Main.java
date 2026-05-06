package Calculadora.app;

import java.util.Scanner;
import Calculadora.menus.*;

public class Main {

    public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);
    int opcao = -1;

    System.out.println("Bem-vindo ao sistema da loja - ");

    while (opcao != 0) {
        System.out.println("\n======= Menu Principal =======");
        System.out.println("\n1 - Menu Vendedor");
        System.out.println("2 - Menu Cliente");
        System.out.println("3 - Menu Loja");
        System.out.println("4 - Realizar Venda");
        System.out.println("0 - Sair");
        System.out.println("\nEscolha uma opção: ");

        opcao = teclado.nextInt();
        teclado.nextLine(); // Limpa o buffer do scanner

        switch (opcao) {
            case 1:
                MenuVendedor.exibirMenu(teclado);
                break;
            case 2:
                MenuCliente.exibirMenu(teclado);
                break;

            case 3:
                MenuLoja.exibirMenu(teclado);
                break;

            case 4:
                MenuVenda.exibirMenu(teclado);
                break;

            case 0:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    }   
}
