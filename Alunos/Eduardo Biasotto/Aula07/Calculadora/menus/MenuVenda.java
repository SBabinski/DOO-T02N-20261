package Calculadora.menus;

import java.util.Calendar;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Date;

import Calculadora.model.*;
import Calculadora.repository.BancoDados;
import Calculadora.services.ProcessaPedido;

public class MenuVenda {

    public static void exibirMenu(Scanner teclado) {

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n======= MENU VENDA =======");
            System.out.println("1. Realizar venda");
            System.out.println("2. Listar vendas");
            System.out.println("3. Buscar por dia");
            System.out.println("4. Cadastrar dados teste");
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
                case 4:
                    criarPedidoFake();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção invalida tente novamente!\n");
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

        if (BancoDados.clientes.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado!");
            return;
        }

        System.out.println("\nEscolha a loja:");
        for (int i = 0; i < BancoDados.lojas.size(); i++) {
            System.out.println((i + 1) + " - " + BancoDados.lojas.get(i).getNomeFantasia());
        }

        int escolhaLoja = teclado.nextInt() - 1;
        if (escolhaLoja < 0 || escolhaLoja >= BancoDados.lojas.size()) {
            System.out.println("\nOpção inválida!");
            return;
        }
        DadosLoja loja = BancoDados.lojas.get(escolhaLoja);

        System.out.println("\nEscolha o vendedor:");
        for (int i = 0; i < BancoDados.vendedores.size(); i++) {
            System.out.println((i + 1) + " - " + BancoDados.vendedores.get(i));
        }

        int escolhaVend = teclado.nextInt() - 1;
        if (escolhaVend < 0 || escolhaVend >= BancoDados.vendedores.size()) {
            System.out.println("\nOpção inválida!");
            return;
        }
        DadosVendedor vendedor = BancoDados.vendedores.get(escolhaVend);

        System.out.println("\nEscolha o cliente:");
        for (int i = 0; i < BancoDados.clientes.size(); i++) {
            System.out.println((i + 1) + " - " + BancoDados.clientes.get(i));
        }

        int escolhaCliente = teclado.nextInt() - 1;
        if (escolhaCliente < 0 || escolhaCliente >= BancoDados.clientes.size()) {
            System.out.println("\nOpção inválida!");
            return;
        }
        DadosCliente cliente = BancoDados.clientes.get(escolhaCliente);

        if (loja.getItens().isEmpty()) {
            System.out.println("\nNenhum produto cadastrado nesta loja!");
            return;
        }

        System.out.println("\nEscolha o produto:");
        for (int i = 0; i < loja.getItens().size(); i++) {
            System.out.println((i + 1) + " - " + loja.getItens().get(i).getNome() + " | R$ " + loja.getItens().get(i).getValor());
        }

        int escolhaItem = teclado.nextInt() - 1;
        if (escolhaItem < 0 || escolhaItem >= loja.getItens().size()) {
            System.out.println("\nOpção inválida!");
            return;
        }
        DadosItem item = loja.getItens().get(escolhaItem);
        double valorUnitario = item.getValor();

        System.out.println("\nQuantidade:");
        int quantidade = teclado.nextInt();

        double valorTotal = quantidade * valorUnitario;
        double valorFinal = valorTotal;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = valorTotal * 0.05;
            valorFinal -= desconto;
            System.out.println("Desconto aplicado!");
        }

        System.out.println("Valor final: R$ " + valorFinal);

        System.out.println("\nValor recebido:");
        double recebido = teclado.nextDouble();

        if (recebido > valorFinal) {
            System.out.println("Troco: R$ " + (recebido - valorFinal));
        } else if (recebido < valorFinal) {
            System.out.println("Faltou: R$ " + (valorFinal - recebido));
        } else {
            System.out.println("\nPagamento exato!");
        }

        if (!loja.getClientes().contains(cliente)) {
            loja.getClientes().add(cliente);
        }

        DadosVenda venda = new DadosVenda(loja, vendedor, cliente, item, valorFinal, quantidade, desconto);
        BancoDados.vendas.add(venda);

        System.out.println("\nVenda realizada com sucesso!");
    }

    public static void listarVendas() {

        if (BancoDados.vendas.isEmpty()) {
            System.out.println("\nNenhuma venda!");
            return;
        }

        double total = 0;

        for (DadosVenda v : BancoDados.vendas) {
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

        for (DadosVenda v : BancoDados.vendas) {
            if (v.getData().equals(dataBusca)) {
                v.apresentarse();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma venda nessa data!");
        }
    }

    public static void criarPedidoFake() {

        if (BancoDados.lojas.isEmpty() || BancoDados.vendedores.isEmpty() || BancoDados.clientes.isEmpty()) {
            System.out.println("\nCadastre ao menos uma loja, vendedor e cliente primeiro!");
            return;
        }

        DadosLoja loja = BancoDados.lojas.get(0);
        DadosVendedor vendedor = BancoDados.vendedores.get(0);
        DadosCliente cliente = BancoDados.clientes.get(0);

        Date hoje = new Date();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date dataVencimento = cal.getTime();

        ProcessaPedido processador = new ProcessaPedido();
        processador.processar(1, hoje, hoje, dataVencimento, cliente, vendedor, loja);
    }
}