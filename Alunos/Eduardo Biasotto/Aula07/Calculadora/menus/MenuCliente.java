package Calculadora.menus;

import java.util.Scanner;
import Calculadora.repository.BancoDados;
import Calculadora.model.DadosCliente;
import Calculadora.model.DadosEndereco;

public class MenuCliente {

    public static void exibirMenu(Scanner teclado) {

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n======= Menu Cliente =======");
            System.out.println("\n1. Cadastrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente(teclado);                    
                    break;
                case 2:

                    if (BancoDados.clientes.isEmpty()) {
                        System.out.println("\nNenhum cliente cadastrado!");
                    } else {
                        for (DadosCliente c : BancoDados.clientes) {
                            c.apresentarse();
                        }
                    }
                    break;

                case 0:
                    System.out.println("\nSaindo...");
                    break;

                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarCliente(Scanner teclado) {

        System.out.println("\n====== SISTEMA DE CADASTRO DE CLIENTE ======");

        System.out.println("\nDigite o nome do cliente:");
        String nome = teclado.nextLine();

        System.out.println("\nDigite a idade do cliente:");
        int idade = teclado.nextInt();
        teclado.nextLine();

        System.out.println("\nDigite a cidade do cliente:");
        String cidade = teclado.nextLine();

        System.out.println("\nDigite o bairro do cliente:");
        String bairro = teclado.nextLine();

        System.out.println("\nDigite a rua do cliente:");
        String rua = teclado.nextLine();

        System.out.println("\nDigite o número da casa do cliente:");
        int numero = teclado.nextInt();
        teclado.nextLine();

        System.out.println("\nDigite o estado:");
        String estado = teclado.nextLine();

        System.out.println("\nDigite um complemento do endereço:");
        String complemento = teclado.nextLine();

        DadosEndereco endereco = new DadosEndereco(cidade, bairro, rua, estado, complemento, numero);
        DadosCliente cliente = new DadosCliente(nome, idade, endereco);
        BancoDados.clientes.add(cliente);

        System.out.println("\nCliente cadastrado com sucesso!");
    }
}