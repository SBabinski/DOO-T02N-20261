package CalculadoraLoja;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    static Scanner scan = new Scanner(System.in);
    public static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static ArrayList<Gabrielinha> registros = new ArrayList<>();
    public static Loja lojaPrincipal;

    public static void main(String[] args) {

        int opcao = 100;

        do {

            System.out.println("[1] - Calcular Troco");
            System.out.println("[2] - Registro");
            System.out.println("[3] - Buscar vendas por Mes e Dia");
            System.out.println("[4] - Gerenciar Loja");
            System.out.println("[5] - Criar Pedido Fake");
            System.out.println("[6] - Sair");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    calTroco(scan);
                    break;
                case 2:
                    mostrarRegistro();
                    break;
                case 3:
                    buscarVendas(scan);
                    break;
                case 4:
                    gerenciarLoja(scan);
                    break;
                case 5:
                    criarPedidoFake();
                    break;
                case 6:
                    opcao = 6;
                    break;
                default:
                    System.out.println("Selecione uma opção válida!");
                    break;

            }

        } while (opcao != 6);

    }

    public static void gerenciarLoja(Scanner scan) {
        int op = 0;

        do {
            System.out.println("\n=== GERENCIAR LOJA ===");
            System.out.println("[1] - Apresentar Loja");
            System.out.println("[2] - Contar Vendedores");
            System.out.println("[3] - Contar Clientes");
            System.out.println("[4] - Adicionar Vendedor");
            System.out.println("[5] - Adicionar Cliente");
            System.out.println("[6] - Listar Vendedores");
            System.out.println("[7] - Listar Clientes");
            System.out.println("[8] - Voltar ao menu principal");
            op = scan.nextInt();

            switch(op) {
                case 1:
                    lojaPrincipal.apresentarse();
                    break;
                case 2:
                    lojaPrincipal.contarVendedores();
                    break;
                case 3:
                    lojaPrincipal.contarClientes();
                    break;
                case 4:
                    adicionarVendedor(scan);
                    break;
                case 5:
                    adicionarCliente(scan);
                    break;
                case 6:
                    listarVendedores();
                    break;
                case 7:
                    listarClientes();
                    break;
                case 8:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while(op != 8);
    }

    public static void adicionarVendedor(Scanner scan) {
        scan.nextLine();
        System.out.print("Nome do vendedor: ");
        String nome = scan.nextLine();
        System.out.print("Idade: ");
        int idade = scan.nextInt();
        scan.nextLine();
        System.out.print("Cidade: ");
        String cidade = scan.nextLine();
        System.out.print("Bairro: ");
        String bairro = scan.nextLine();
        System.out.print("Rua: ");
        String rua = scan.nextLine();
        System.out.print("Salário Base: ");
        float salarioBase = scan.nextFloat();

        Endereco endereco = new Endereco("PR", cidade, bairro, 0, rua);

        Vendedor v = new Vendedor(nome, idade, lojaPrincipal, endereco, salarioBase);
        lojaPrincipal.adicionarVendedor(v);
        System.out.println("Vendedor adicionado com sucesso!");
    }

    public static void adicionarCliente(Scanner scan) {
        scan.nextLine();
        System.out.print("Nome do cliente: ");
        String nome = scan.nextLine();
        System.out.print("Idade: ");
        int idade = scan.nextInt();
        scan.nextLine();
        System.out.print("Cidade: ");
        String cidade = scan.nextLine();
        System.out.print("Bairro: ");
        String bairro = scan.nextLine();
        System.out.print("Rua: ");
        String rua = scan.nextLine();

        Endereco endereco = new Endereco("PR", cidade, bairro, 0, rua);

        Cliente c = new Cliente(nome, idade, endereco);
        lojaPrincipal.adicionarCliente(c);
        System.out.println("Cliente adicionado com sucesso!");
    }

    public static void listarVendedores() {
        if (lojaPrincipal.getVendedores().isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado.");
            return;
        }
        System.out.println("\n=== LISTA DE VENDEDORES ===");
        for (Vendedor v : lojaPrincipal.getVendedores()) {
            v.apresentarse();
        }
    }

    public static void listarClientes() {
        if (lojaPrincipal.getClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("\n=== LISTA DE CLIENTES ===");
        for (Cliente c : lojaPrincipal.getClientes()) {
            c.apresentarse();
        }
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

    public static void criarPedidoFake() {

        if (lojaPrincipal.getClientes().isEmpty() || lojaPrincipal.getVendedores().isEmpty()) {
            System.out.println("Cadastre ao menos um cliente e um vendedor.");
            return;
        }

        ArrayList<Item> itens = new ArrayList<>();
        itens.add(new Item(1, "Vaso", "Decoração", 59.90));
        itens.add(new Item(2, "Samambaia", "Planta", 39.90));

        Date vencimento = new Date(System.currentTimeMillis() + 86400000);

        ProcessaPedido processador = new ProcessaPedido();

        Pedido pedido = processador.processar(
                1,
                lojaPrincipal.getClientes().get(0),
                lojaPrincipal.getVendedores().get(0),
                lojaPrincipal,
                itens,
                vencimento
        );

        if (pedido != null) {
            pedido.gerarDescricaoVenda();
        }
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
