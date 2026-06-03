package Calculadora.menus;

import java.util.Scanner;

import Calculadora.model.DadosEndereco;
import Calculadora.model.DadosLoja;
import Calculadora.repository.BancoDados;

public class MenuLoja {

    public static void exibirMenu(Scanner teclado) {

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n======= Menu Loja =======");
            System.out.println("\n1. Cadastrar loja");
            System.out.println("2. Listar lojas");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLoja(teclado);                    
                    break;
                case 2:
                    if (BancoDados.lojas.isEmpty()) {
                        System.out.println("\nNenhuma loja cadastrada!");
                    } 
                    else {                    
                        for (DadosLoja l : BancoDados.lojas) {
                            l.apresentarse();
                        }
                    }
                    break;
                
                case 0:
                    System.out.println("Saindo do menu loja...");
                    break;
                
                default:
                    System.out.println("Opção inválida. Tente novamente."); 
                
                }
            }
    }

    private static void cadastrarLoja(Scanner teclado) {
        
        System.out.println("\n====== SISTEMA DE CADASTRO DE LOJA ======");

        System.out.println("\nDigite o nome fantasia da loja:");
        String nomeFantasia = teclado.nextLine();

        System.out.println("\nDigite a razão social da loja:");
        String razaoSocial = teclado.nextLine();

        System.out.println("\nDigite o CNPJ da loja:");
        String cnpj = teclado.nextLine();

        System.out.println("\nDigite a cidade da loja:");
        String cidade = teclado.nextLine();

        System.out.println("\nDigite o bairro da loja:");
        String bairro = teclado.nextLine();

        System.out.println("\nDigite a rua da loja:");
        String rua = teclado.nextLine();

        System.out.println("\nDigite o número da loja:");
        int numero = teclado.nextInt();
        teclado.nextLine();

        System.out.println("\nDigite o Estado:");
        String estado = teclado.nextLine();

        System.out.println("\nDigite o complemento da loja:");
        String complemento = teclado.nextLine();

        DadosEndereco endereco = new DadosEndereco(cidade, bairro, rua, estado, complemento, numero);
        DadosLoja loja = new DadosLoja(nomeFantasia, razaoSocial, cnpj, endereco);
        BancoDados.lojas.add(loja);
        System.out.println("\nLoja cadastrada com sucesso!");


    }
    
}
