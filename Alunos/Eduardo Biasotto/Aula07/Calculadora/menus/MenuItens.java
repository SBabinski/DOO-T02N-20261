package Calculadora.menus;

import java.util.Scanner;

import Calculadora.model.DadosItem;
import Calculadora.repository.BancoDados;

public class MenuItens {
    
        public static void exibirMenu(Scanner teclado) {

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n======= Menu de Produtos =======");
            System.out.println("\n1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarItem(teclado);                    
                    break;
                case 2:
                    if (BancoDados.itens.isEmpty()) {
                        System.out.println("\nNenhuma intem cadastrada!");
                    } 
                    else {                    
                        for (DadosItem l : BancoDados.itens) {
                            l.gerarDescricao();
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

    private static void cadastrarItem(Scanner teclado) {

        if (BancoDados.lojas.isEmpty()) {
            System.out.println("\nNenhuma loja cadastrada!");
            return;
        }

        System.out.println("\n====== CADASTRO DE PRODUTO ======");

        System.out.println("\nDigite o nome do produto:");
        String nome = teclado.nextLine();

        System.out.println("\nDigite o tipo do produto:");
        String tipo = teclado.nextLine();

        System.out.println("\nDigite o valor do produto:");
        double valor = teclado.nextDouble();
        teclado.nextLine();

        // ID gerado automaticamente
        int id = BancoDados.itens.size() + 1;

        System.out.println("\nEscolha a loja:");
        for (int i = 0; i < BancoDados.lojas.size(); i++) {
            System.out.println((i + 1) + " - " + BancoDados.lojas.get(i).getNomeFantasia());
        }

        int escolha = teclado.nextInt();
        teclado.nextLine();

        DadosItem item = new DadosItem(id, nome, valor, tipo);

        BancoDados.itens.add(item);
        BancoDados.lojas.get(escolha - 1).getItens().add(item);

        System.out.println("\nProduto cadastrado com sucesso!");
    }
}
