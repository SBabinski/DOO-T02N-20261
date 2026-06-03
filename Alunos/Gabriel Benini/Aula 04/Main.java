package CalculadoraLoja;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static ArrayList<Gabrielinha> registros = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int opcao = 1000;

        do {

            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Registro");
            System.out.println("[4] - Buscar vendas por Mes e Dia");
            System.out.println("[5] - Sair");
            opcao = scan.nextInt();

            switch(opcao) {

                case 1: calPrecoTotal(scan); break;
                case 2: calTroco(scan); break;
                case 3: mostrarRegistro(); break;
                case 4: buscarVendas(scan); break;
                case 5: opcao = 5; break;
                default: System.out.println("Selecione uma opção válida!"); break;

            }

        } while(opcao != 5);

        scan.close();

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

        int registro = registros.size() + 1;
        float valorTotal = quantidade * valor;

        int dia = ThreadLocalRandom.current().nextInt(1, 31);
        int mes = ThreadLocalRandom.current().nextInt(1, 12);

        LocalDate dataVenda = LocalDate.parse("2026-" + (mes < 10 ? "0" : "") + mes + "-" + (dia < 10 ? "0" : "") + dia);

        if (quantidade > 10){
            float desconto = valorTotal * 0.05f;
            valorTotal = valorTotal - desconto;

            Gabrielinha gabrielinha = new Gabrielinha(registro, valorTotal, quantidade, desconto, dataVenda);

            System.out.println(gabrielinha);

            registros.add(gabrielinha);

            System.out.println("O valor total com desconto de 5% ficou: " + valorTotal);
        } else {

            Gabrielinha gabrielinha = new Gabrielinha(registro, valorTotal, quantidade, 0.0f, dataVenda);

            System.out.println(gabrielinha);

            registros.add(gabrielinha);

            System.out.println("O valor total da compra ficou: "+ valorTotal);
        }
        System.out.println();
    }

    public static void calTroco(Scanner scan) {

        Gabrielinha loja = buscarLoja(scan);

        if (loja == null) {

            System.out.println("Nenhum registro foi encontrado!");
            System.out.println();
            return;

        }

        float valorTotal = loja.getValorTotal();

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

        loja.setDinheiro(dinheiro);
        loja.setTroco(troco);

    }

    public static Gabrielinha buscarLoja(Scanner scan) {

        System.out.println("Digite o registro da venda: ");
        System.out.println();

        int registro = scan.nextInt();

        for (Gabrielinha gabrielinha : registros) {

            if (gabrielinha.getRegistro() == registro)
                return gabrielinha;

        }

        return null;

    }

    public static void buscarVendas(Scanner scan) {

        System.out.println("Escolha um dia: ");
        int dia = scan.nextInt();

        System.out.println("Escolha um mes: ");
        int mes = scan.nextInt();

        List<Gabrielinha> lista = buscarVendas(dia, mes);

        if (lista.isEmpty()) {

            System.out.println("Nenhum registro foi encontrado!");
            System.out.println();
            return;

        }

        for (Gabrielinha gabrielinha : lista) {

            System.out.println(gabrielinha);

        }

        System.out.println();

    }

    public static List<Gabrielinha> buscarVendas(int dia, int mes) {

        List<Gabrielinha> vendas = new ArrayList<>();

        for (Gabrielinha gabrielinha : registros) {

            LocalDate dataVenda = gabrielinha.getDataVenda();

            if (dataVenda.getDayOfMonth() == dia && dataVenda.getMonthValue() == mes) {

                vendas.add(gabrielinha);

            }

        }

        return vendas;

    }
}