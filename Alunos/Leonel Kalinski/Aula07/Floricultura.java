package fag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objetos.Cliente;
import objetos.Gerente;
import objetos.Loja;
import objetos.Pedido;
import objetos.ProcessaPedido;
import objetos.Vendedor;

public class Floricultura {

    public static Scanner scan = new Scanner(System.in);
    static List<Pedido> registro = new ArrayList<>();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int opcao;
        do {
            System.out.println("------- MENU ------");
            System.out.println("1-Preco Total");
            System.out.println("2-Troco");
            System.out.println("3-Registro");
            System.out.println("4-Buscar Por Data");
            System.out.println("5-Apresentações");
            System.out.println("6-Criar Pedido");
            System.out.println("0-Sair");

            opcao = scan.nextInt();

            validarEscolha(opcao);

        } while (opcao != 0);
    }

    public static void validarEscolha(int opcao) {
        switch (opcao) {
            case 1 -> precoTotal();
            case 2 -> troco();
            case 3 -> registro();
            case 4 -> buscarPorData();
            case 5 -> apresentar();
            case 6 -> criarPedido();
            case 0 -> System.out.println("saindo...");
        }
    }

    public static void precoTotal() {
        System.out.println("quantas plantas voce comprou");
        int qtd = scan.nextInt();
        System.out.println("qual o valor da planta");
        double preco = scan.nextDouble();
        Loja loja = new Loja();
        loja.setNomeFantasia("My Plant");
        Cliente c = new Cliente();
        c.setNome("Cliente simples");
        Vendedor v = new Vendedor();
        v.setNome("Vendedor simples");
        v.setLoja(loja);
        
        Pedido p = new Pedido(1, c, v, loja);
        p.adicionarItem(new objetos.Item(1, "Planta", "Flor", preco));
       
        registro.add(p);
        System.out.println("Total: " + p.calcularValorTotal());
    }

    public static void criarPedido() {
        Loja loja = new Loja();
        loja.setNomeFantasia("My Plant");

        Cliente c = new Cliente();
        c.setNome("Cliente Teste");

        Vendedor v = new Vendedor();
        v.setNome("Vendedor Teste");
        v.setLoja(loja);

        ProcessaPedido proc = new ProcessaPedido();
        Pedido p = proc.processar(c, v, loja);

        registro.add(p);
        p.gerarDescricaoVenda();
    }

    public static void registro() {
        if (registro.size() == 0) {
            System.out.println("nenhum registro");
            return;
        }

        for (int i = 0; i < registro.size(); i++) {
            System.out.println("Pedido " + (i + 1));
            registro.get(i).gerarDescricaoVenda();
        }
    }

    public static void buscarPorData() {
        System.out.println("Digite a data (dd/MM/yyyy): ");
        String dataInput = scan.next();

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");

        double total = 0;

        for (Pedido p : registro) {
            String dataPedido = sdf.format(p.getDataCriacao());

            if (dataPedido.equals(dataInput)) {
                total += p.calcularValorTotal();
            }
        }

        System.out.println("Total vendido no dia: " + total);
    }

    public static void troco() {
        System.out.println("Quanto voce pagou?");
        double pago = scan.nextDouble();

        System.out.println("qual era o preco total?");
        double preco = scan.nextDouble();

        if (pago < preco) {
            System.out.println("Valor insuficiente!");
        } else {
            System.out.println("Troco: " + (pago - preco));
        }
    }

    public static void apresentar() {

        Loja loja = new Loja();
        loja.setNomeFantasia("My Plant");

        // GERENTE
        Gerente g1 = new Gerente();
        g1.setNome("Chefe");
        g1.setIdade(40);
        g1.setLoja(loja);
        g1.setSalarioBase(2000);
        g1.receberSalario(2000);
        g1.receberSalario(2100);
        g1.receberSalario(2200);

        // VENDEDOR
        Vendedor v1 = new Vendedor();
        v1.setNome("João");
        v1.setIdade(25);
        v1.setLoja(loja);
        v1.setSalarioBase(1200);
        v1.receberSalario(1200);
        v1.receberSalario(1250);
        v1.receberSalario(1300);

        System.out.println("=== FUNCIONÁRIOS ===");

        g1.apresentarse();
        System.out.println("Média Salarial: " + g1.calcularMediaSalarial());
        System.out.println("Bônus: " + g1.calcularBonusSalarial());

        System.out.println("-------------------");

        v1.apresentarse();
        System.out.println("Média Salarial: " + v1.calcularMediaSalarial());
        System.out.println("Bônus: " + v1.calcularBonusSalarial());
    }
}