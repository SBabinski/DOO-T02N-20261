package Locadora.app;

import java.util.Scanner;
import Locadora.menus.MenuPrincipal;
import Locadora.repository.BancoDados;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        BancoDados bancoDados = new BancoDados();
        int opcao;

        System.out.println("Seja bem vindo a Locadora CarroPlus!");

        do {
            System.out.println("==== Menu de Opções ====");
            System.out.println("1. Cadastrar Veiculo");
            System.out.println("2. Cadastrar Cliente");
            System.out.println("3. Alugar Veiculo");
            System.out.println("4. Devolver Veiculo");
            System.out.println("5. Listar sem devolucao");
            System.out.println("6. Demonstracao");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    MenuPrincipal.menuVeiculo(teclado, bancoDados);
                    break;
                case 2:
                    MenuPrincipal.menuCliente(teclado, bancoDados);
                    break;
                case 3:
                    MenuPrincipal.menuLocacao(teclado, bancoDados);
                    break;
                case 4:
                    MenuPrincipal.menuDevolucao(teclado, bancoDados);
                    break;
                case 5:
                    bancoDados.listarSemDevolucao();
                    break;
                case 6:
                    MenuPrincipal.demonstracao(bancoDados);
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        teclado.close();
    }
}