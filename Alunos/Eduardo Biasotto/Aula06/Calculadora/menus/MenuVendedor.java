package Calculadora.menus;

import java.util.Scanner;

import Calculadora.model.Vendedor;
import Calculadora.model.Loja;
import Calculadora.repository.BancoDados;

public class MenuVendedor {

    public static void exibirMenu(Scanner teclado) {

        int opcao = -1;

        while (opcao != 0) { 
            System.out.println("\n======= Menu Vendedor =======");
            System.out.println("\n1. Cadastrar vendedor");
            System.out.println("2. Listar vendedores");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) { 
                case 1:
                    cadastrarVendedor(teclado);
                    break;

                case 2:
                    if (BancoDados.vendedores.isEmpty()) {
                        System.out.println("\nNenhum vendedor cadastrado!");
                    } else {
                            Vendedor.apresentarse();
                    }
                    break;

                case 0:
                    System.out.println("Saindo do menu vendedor...");
                    break;

                default:
                    System.out.println("\nOpção inválida.");
            } 
        }
    } 

    public static void cadastrarVendedor(Scanner teclado) {
        System.out.println("\n====== SISTEMA DE CADASTRO DE VENDEDOR ======");

        System.out.println("\nDigite o nome do vendedor:");
        String nome = teclado.nextLine();

        System.out.println("\nDigite a idade do vendedor:");
        int idade = teclado.nextInt();
        teclado.nextLine();

        System.out.println("\nDigite a cidade do vendedor:");
        String cidade = teclado.nextLine();

        System.out.println("\nDigite o bairro do vendedor:");
        String bairro = teclado.nextLine();

        System.out.println("\nDigite a rua do vendedor:");
        String rua = teclado.nextLine();

        System.out.println("\nDigite o número do vendedor:");
        String numero = teclado.nextLine();

        System.out.println("\nDigite o salário base do vendedor:");
        double salarioBase = teclado.nextDouble();
        teclado.nextLine();

        Loja loja = null;

        if (BancoDados.lojas.isEmpty()) {
            System.out.println("\nNenhuma loja cadastrada. Vendedor ficará sem loja.");
        } else {
            System.out.println("\nEscolha a loja:");

            for (int i = 0; i < BancoDados.lojas.size(); i++) {
                System.out.println(i + " - " + BancoDados.lojas.get(i).getNomeFantasia());
            }

            int escolha = teclado.nextInt();
            teclado.nextLine();

            if (escolha >= 0 && escolha < BancoDados.lojas.size()) {
                loja = BancoDados.lojas.get(escolha);
            } else {
                System.out.println("Opção inválida. Vendedor ficará sem loja.");
            }
        }

        Vendedor vendedor = new Vendedor(nome, idade, loja, cidade, bairro, rua, numero, salarioBase);

        BancoDados.vendedores.add(vendedor);

        if (loja != null) {
            loja.getVendedores().add(vendedor);
        }

        System.out.println("\nVendedor cadastrado com sucesso!");
    }
}