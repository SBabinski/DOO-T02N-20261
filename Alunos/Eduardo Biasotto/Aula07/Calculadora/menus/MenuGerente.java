package Calculadora.menus;

import java.util.Scanner;

import Calculadora.model.DadosLoja;
import Calculadora.model.DadosEndereco;
import Calculadora.model.DadosGerente;
import Calculadora.repository.BancoDados;

public class MenuGerente {

    public static void exibirMenu(Scanner teclado) {

        int opcao = -1;

        while (opcao != 0) { 
            System.out.println("\n======= Menu Gerente =======");
            System.out.println("\n1. Cadastrar gerente");
            System.out.println("2. Listar gerentes");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) { 
                case 1:
                    cadastrarGerente(teclado);
                    break;

                case 2:
                    if (BancoDados.gerentes.isEmpty()) {
                        System.out.println("\nNenhum gerente cadastrado!");
                    } else {
                        for (DadosGerente g : BancoDados.gerentes) {
                            g.apresentarse();
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saindo do menu gerente...");
                    break;

                default:
                    System.out.println("\nOpção inválida.");
            } 
        }
    } 

    public static void cadastrarGerente(Scanner teclado) {
        System.out.println("\n====== SISTEMA DE CADASTRO DE GERENTE ======");

        System.out.println("\nDigite o nome do gerente:");
        String nome = teclado.nextLine();

        System.out.println("\nDigite a idade do gerente:");
        int idade = teclado.nextInt();
        teclado.nextLine();

        System.out.println("\nDigite a cidade do gerente:");
        String cidade = teclado.nextLine();

        System.out.println("\nDigite o bairro do gerente:");
        String bairro = teclado.nextLine();

        System.out.println("\nDigite a rua do gerente:");
        String rua = teclado.nextLine();

        System.out.println("\nDigite o número do gerente:");
        String numero = teclado.nextLine();

        System.out.println("\nDigite o estado:");
        String estado = teclado.nextLine();

        System.out.println("\nDigite um complemento do endereço:");
        String complemento = teclado.nextLine();

        System.out.println("\nDigite o salário base do gerente:");
        double salarioBase = teclado.nextDouble();
        teclado.nextLine();

        DadosLoja loja = null;

        if (BancoDados.lojas.isEmpty()) {
            System.out.println("\nNenhuma loja cadastrada. Gerente ficará sem loja.");
        } else {
            System.out.println("\nEscolha a loja:");

            for (int i = 0; i < BancoDados.lojas.size(); i++) {
                System.out.println((i + 1) + " - " + BancoDados.lojas.get(i).getNomeFantasia());
            }

            int escolha = teclado.nextInt();
            teclado.nextLine();

            if (escolha >= 1 && escolha <= BancoDados.lojas.size()) {
                loja = BancoDados.lojas.get(escolha - 1);
            } else {
                System.out.println("Opção inválida. Gerente ficará sem loja.");
            }
        }

        DadosEndereco endereco = new DadosEndereco(cidade, bairro, rua, estado, complemento, Integer.parseInt(numero));
        DadosGerente gerente = new DadosGerente(nome, idade, loja, endereco, salarioBase);

        BancoDados.gerentes.add(gerente);

        if (loja != null) {
            loja.getGerentes().add(gerente);
        }

        System.out.println("\nGerente cadastrado com sucesso!");
    }
}