package Calculadora.menus;

import java.util.Scanner;
import java.time.LocalDate;

import Calculadora.model.*;
import Calculadora.repository.BancoDados;

public class MenuVenda {

    public static void exibirMenu(Scanner teclado) {

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n======= MENU VENDA =======");
            System.out.println("1. Realizar venda");
            System.out.println("2. Listar vendas");
            System.out.println("3. Buscar por dia");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    calcularVendaCompleta(teclado);
                    break;

                case 2:
                    listarVendas();
                    break;

                case 3:
                    buscarPorDia(teclado);
                    break;
            }
        }
    }


    public static void calcularVendaCompleta(Scanner teclado) {

        if (BancoDados.lojas.isEmpty()) {
            System.out.println("\nNenhuma loja cadastrada!");
            return;
        }

        if (BancoDados.vendedores.isEmpty()) {
            System.out.println("\nNenhum vendedor cadastrado!");
            return;
        }

        System.out.println("\nEscolha a loja:");
        for (int i = 0; i < BancoDados.lojas.size(); i++) {
            System.out.println(i + " - " + BancoDados.lojas.get(i).getNomeFantasia());
        }

        int escolhaLoja = teclado.nextInt();
        Loja loja = BancoDados.lojas.get(escolhaLoja);

        System.out.println("\nEscolha o vendedor:");
        for (int i = 0; i < BancoDados.vendedores.size(); i++) {
            System.out.println(i + " - " + BancoDados.vendedores.get(i));
        }

        int escolhaVend = teclado.nextInt();
        Vendedor vendedor = BancoDados.vendedores.get(escolhaVend);

        System.out.println("\nQuantidade:");
        int quantidade = teclado.nextInt();

        System.out.println("Valor unitário:");
        double valorUnitario = teclado.nextDouble();

        double valorTotal = quantidade * valorUnitario;
        double valorFinal = valorTotal;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = valorTotal * 0.05;
            valorFinal -= desconto;
            System.out.println("Desconto aplicado!\n");
        }

        System.out.println("Valor final: R$ " + valorFinal);

        System.out.println("\nValor recebido:");
        double recebido = teclado.nextDouble();

        if (recebido > valorFinal) {
            System.out.println("Troco: R$ " + (recebido - valorFinal));
        } else if (recebido < valorFinal) {
            System.out.println("Faltou: R$ " + (valorFinal - recebido));
        } else {
            System.out.println("\nnPagamento exato!");
        }


        Cliente cliente = new Cliente("Cliente", 0, "", "", "", "");


        Venda venda = new Venda(loja, vendedor, cliente, valorFinal);

        BancoDados.vendas.add(venda);

        System.out.println("\nVenda realizada com sucesso!");
    }

    public static void listarVendas() {

        if (BancoDados.vendas.isEmpty()) {
            System.out.println("\nNenhuma venda!");
            return;
        }

        double total = 0;

        for (Venda v : BancoDados.vendas) {
            v.apresentarse();
            total += v.getValor();
        }

        System.out.println("\nTotal geral: R$ " + total);
    }

    public static void buscarPorDia(Scanner teclado) {

        System.out.println("Dia:");
        int dia = teclado.nextInt();

        System.out.println("Mês:");
        int mes = teclado.nextInt();

        System.out.println("Ano:");
        int ano = teclado.nextInt();

        LocalDate dataBusca = LocalDate.of(ano, mes, dia);

        boolean encontrou = false;

        for (Venda v : BancoDados.vendas) {
            if (v.getData().equals(dataBusca)) {
                v.apresentarse();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma venda nessa data!");
        }
    }
}