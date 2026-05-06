import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final Loja loja = new Loja(
            "My Plant",
            "My Plant LTDA",
            "12.345.678/0001-99",
            new Endereco("PR", "Cascavel", "Centro", "1000", "Sala 12")
    );

    private static final ArrayList<LocalDate> datasVendas = new ArrayList<>();
    private static final ArrayList<Integer> quantidadesVendas = new ArrayList<>();
    private static final ArrayList<Double> totaisVendas = new ArrayList<>();
    private static final ArrayList<Double> descontosVendas = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    realizarVenda();
                    break;

                case 2:
                    calcularTroco();
                    break;

                case 3:
                    verRegistroVendas();
                    break;

                case 4:
                    buscarVendasPorDia();
                    break;

                case 5:
                    buscarVendasPorMes();
                    break;

                case 6:
                    cadastrarCliente();
                    break;

                case 7:
                    cadastrarVendedor();
                    break;

                case 8:
                    cadastrarGerente();
                    break;

                case 9:
                    listarClientes();
                    break;

                case 10:
                    listarVendedores();
                    break;

                case 11:
                    listarGerentes();
                    break;

                case 12:
                    mostrarLoja();
                    break;

                case 13:
                    criarPedidoFake();
                    break;

                case 14:
                    testarProcessaPedido();
                    break;

                case 15:
                    System.out.println("Encerrando o sistema.");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 15);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Sistema My Plant ===");
        System.out.println("[1] Realizar venda");
        System.out.println("[2] Calcular troco");
        System.out.println("[3] Ver registro de vendas");
        System.out.println("[4] Buscar vendas por dia");
        System.out.println("[5] Buscar vendas por mes");
        System.out.println("[6] Cadastrar cliente");
        System.out.println("[7] Cadastrar vendedor");
        System.out.println("[8] Cadastrar gerente");
        System.out.println("[9] Listar clientes");
        System.out.println("[10] Listar vendedores");
        System.out.println("[11] Listar gerentes");
        System.out.println("[12] Mostrar loja");
        System.out.println("[13] Criar pedido");
        System.out.println("[14] Testar ProcessaPedido");
        System.out.println("[15] Sair");
    }

    private static void realizarVenda() {
        int quantidade = lerInteiro("Quantidade de plantas: ");
        double precoUnitario = lerDouble("Preco unitario: ");
        LocalDate dataVenda = lerData("Data da venda (dd/MM/yyyy): ");

        double totalSemDesconto = quantidade * precoUnitario;
        double desconto = 0.0;

        if (quantidade > 10) {
            desconto = totalSemDesconto * 0.05;
        }

        double totalFinal = totalSemDesconto - desconto;

        if (desconto > 0) {
            System.out.printf("Total sem desconto: R$ %.2f%n", totalSemDesconto);
            System.out.printf("Desconto: R$ %.2f%n", desconto);
        }

        System.out.printf("Total final: R$ %.2f%n", totalFinal);

        datasVendas.add(dataVenda);
        quantidadesVendas.add(quantidade);
        totaisVendas.add(totalFinal);
        descontosVendas.add(desconto);
    }

    private static void calcularTroco() {
        double valorPago = lerDouble("Valor pago: ");
        double valorCompra = lerDouble("Valor da compra: ");

        if (valorPago < valorCompra) {
            System.out.println("Valor insuficiente.");
            return;
        }

        System.out.printf("Troco: R$ %.2f%n", valorPago - valorCompra);
    }

    private static void verRegistroVendas() {
        if (datasVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        System.out.println("\n=== Registro de Vendas ===");

        for (int i = 0; i < datasVendas.size(); i++) {
            System.out.println("Venda " + (i + 1));
            System.out.println("Data: " + datasVendas.get(i).format(dataFormatter));
            System.out.println("Quantidade: " + quantidadesVendas.get(i));

            if (descontosVendas.get(i) > 0) {
                double totalSemDesconto = totaisVendas.get(i) + descontosVendas.get(i);
                System.out.printf("Total sem desconto: R$ %.2f%n", totalSemDesconto);
                System.out.printf("Desconto: R$ %.2f%n", descontosVendas.get(i));
            }

            System.out.printf("Total final: R$ %.2f%n", totaisVendas.get(i));
            System.out.println("-----------------------------");
        }
    }

    private static void buscarVendasPorDia() {
        if (datasVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        LocalDate dataBusca = lerData("Digite a data (dd/MM/yyyy): ");

        int totalVendasEncontradas = 0;
        int quantidadeTotal = 0;
        double valorTotal = 0.0;

        for (int i = 0; i < datasVendas.size(); i++) {
            if (datasVendas.get(i).equals(dataBusca)) {
                totalVendasEncontradas++;
                quantidadeTotal += quantidadesVendas.get(i);
                valorTotal += totaisVendas.get(i);
            }
        }

        System.out.println("Vendas encontradas no dia: " + totalVendasEncontradas);
        System.out.println("Quantidade total vendida no dia: " + quantidadeTotal);
        System.out.printf("Valor total vendido no dia: R$ %.2f%n", valorTotal);
    }

    private static void buscarVendasPorMes() {
        if (datasVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        String texto = lerTexto("Digite o mês e ano (MM/yyyy): ");
        String[] partes = texto.split("/");

        if (partes.length != 2) {
            System.out.println("Formato inválido. Use MM/yyyy.");
            return;
        }

        int mes;
        int ano;

        try {
            mes = Integer.parseInt(partes[0]);
            ano = Integer.parseInt(partes[1]);
        } catch (NumberFormatException e) {
            System.out.println("Formato invalido.");
            return;
        }

        int totalVendasEncontradas = 0;
        int quantidadeTotal = 0;
        double valorTotal = 0.0;

        for (int i = 0; i < datasVendas.size(); i++) {
            LocalDate data = datasVendas.get(i);

            if (data.getMonthValue() == mes && data.getYear() == ano) {
                totalVendasEncontradas++;
                quantidadeTotal += quantidadesVendas.get(i);
                valorTotal += totaisVendas.get(i);
            }
        }

        System.out.println("Vendas encontradas no mes: " + totalVendasEncontradas);
        System.out.println("Quantidade total vendida no mes: " + quantidadeTotal);
        System.out.printf("Valor total vendido no mes: R$ %.2f%n", valorTotal);
    }

    private static void cadastrarCliente() {
        String nome = lerTexto("Nome: ");
        int idade = lerInteiro("Idade: ");
        Endereco endereco = lerEndereco();

        Cliente cliente = new Cliente(nome, idade, endereco);
        loja.clientes.add(cliente);

        System.out.println("Cliente cadastrado.");
    }

    private static void cadastrarVendedor() {
        String nome = lerTexto("Nome: ");
        int idade = lerInteiro("Idade: ");
        Endereco endereco = lerEndereco();
        double salarioBase = lerDouble("Salario base: ");
        double[] salarioRecebido = lerTresSalarios("Salario lancado");

        Vendedor vendedor = new Vendedor(nome, idade, endereco, loja, salarioBase, salarioRecebido);
        loja.vendedores.add(vendedor);

        System.out.println("Vendedor cadastrado.");
    }

    private static void cadastrarGerente() {
        String nome = lerTexto("Nome: ");
        int idade = lerInteiro("Idade: ");
        Endereco endereco = lerEndereco();
        double salarioBase = lerDouble("Salario base: ");
        double[] salarioRecebido = lerTresSalarios("Salario lancado");

        Gerente gerente = new Gerente(nome, idade, endereco, loja, salarioBase, salarioRecebido);
        loja.gerentes.add(gerente);

        System.out.println("Gerente cadastrado.");
    }

    private static void listarClientes() {
        if (loja.clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n=== Clientes ===");

        for (Cliente cliente : loja.clientes) {
            cliente.apresentarSe();
            cliente.apresentarEndereco();
            System.out.println("-----------------------------");
        }
    }

    private static void listarVendedores() {
        if (loja.vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado.");
            return;
        }

        System.out.println("\n=== Vendedores ===");

        for (Vendedor vendedor : loja.vendedores) {
            vendedor.apresentarSe();
            vendedor.apresentarEndereco();
            System.out.printf("Media salarial: R$ %.2f%n", vendedor.calcularMedia());
            System.out.printf("Bonus: R$ %.2f%n", vendedor.calcularBonus());
            System.out.println("-----------------------------");
        }
    }

    private static void listarGerentes() {
        if (loja.gerentes.isEmpty()) {
            System.out.println("Nenhum gerente cadastrado.");
            return;
        }

        System.out.println("\n=== Gerentes ===");

        for (Gerente gerente : loja.gerentes) {
            gerente.apresentarSe();
            gerente.apresentarEndereco();
            System.out.printf("Media salarial: R$ %.2f%n", gerente.calcularMedia());
            System.out.printf("Bonus: R$ %.2f%n", gerente.calcularBonus());
            System.out.println("-----------------------------");
        }
    }

    private static void mostrarLoja() {
        System.out.println("\n=== Dados da Loja ===");
        loja.apresentarSe();
        System.out.println("Clientes cadastrados: " + loja.contarClientes());
        System.out.println("Vendedores cadastrados: " + loja.contarVendedores());
        System.out.println("Gerentes cadastrados: " + loja.contarGerentes());
        System.out.println("Pedidos cadastrados: " + loja.contarPedidos());
    }

    private static void criarPedidoFake() {
        Cliente cliente = obterClienteParaPedido();
        Vendedor vendedor = obterVendedorParaPedido();

        ProcessaPedido processaPedido = new ProcessaPedido();
        Pedido pedido = processaPedido.processar(cliente, vendedor, loja);

        loja.pedidos.add(pedido);

        System.out.println("\n=== Itens do Pedido ===");
        for (Item item : pedido.itens) {
            item.gerarDescricao();
            System.out.println("-----------------------------");
        }

        pedido.gerarDescricaoVenda();
    }

    private static void testarProcessaPedido() {
        Cliente cliente = obterClienteParaPedido();
        Vendedor vendedor = obterVendedorParaPedido();

        ProcessaPedido processaPedido = new ProcessaPedido();
        processaPedido.testarConfirmarPagamento(cliente, vendedor, loja);
    }

    private static Cliente obterClienteParaPedido() {
        if (loja.clientes.isEmpty()) {
            Cliente clienteFake = new Cliente(
                    "Cliente Teste",
                    28,
                    new Endereco("PR", "Cascavel", "Centro", "999", "Teste")
            );
            loja.clientes.add(clienteFake);
            return clienteFake;
        }

        return loja.clientes.get(0);
    }

    private static Vendedor obterVendedorParaPedido() {
        if (loja.vendedores.isEmpty()) {
            Vendedor vendedorFake = new Vendedor(
                    "Vendedor Teste",
                    30,
                    new Endereco("PR", "Cascavel", "Centro", "888", "Teste"),
                    loja,
                    2500,
                    new double[] { 2000, 2100, 2200 }
            );
            loja.vendedores.add(vendedorFake);
            return vendedorFake;
        }

        return loja.vendedores.get(0);
    }

    private static Endereco lerEndereco() {
        String estado = lerTexto("Estado: ");
        String cidade = lerTexto("Cidade: ");
        String bairro = lerTexto("Bairro: ");
        String numero = lerTexto("Numero: ");
        String complemento = lerTexto("Complemento: ");

        return new Endereco(estado, cidade, bairro, numero, complemento);
    }

    private static double[] lerTresSalarios(String rotulo) {
        double[] salarios = new double[3];

        for (int i = 0; i < salarios.length; i++) {
            salarios[i] = lerDouble(rotulo + " " + (i + 1) + ": ");
        }

        return salarios;
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inteiro invalido.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor numerico invalido.");
            }
        }
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            try {
                return LocalDate.parse(entrada, dataFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data invalida. Use o formato dd/MM/yyyy.");
            }
        }
    }
}