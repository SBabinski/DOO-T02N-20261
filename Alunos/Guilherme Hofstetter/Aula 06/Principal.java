import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Principal {
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Venda> registroDeVendas = new ArrayList<>();
    private static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static Loja loja = new Loja(
        "My Plant",
        "My Plant Comércio de Plantas LTDA",
        "69.6969.111/1111-99",
        "São Paulo",
        "Centro",
        "Rua das Flores, 100"
    );

    private static void carregarDadosIniciais() {
        Vendedor v1 = new Vendedor("Carlos Silva", 35, loja, "São Paulo", "Centro", "Rua das Flores, 100", 2500.00);
        Vendedor v2 = new Vendedor("Ana Souza", 28, loja, "São Paulo", "Jardins", "Av. Paulista, 200", 2200.00);
        Vendedor v3 = new Vendedor("João Pereira", 42, loja, "São Paulo", "Mooca", "Rua da Mooca, 300", 3000.00);
        loja.adicionarVendedor(v1);
        loja.adicionarVendedor(v2);
        loja.adicionarVendedor(v3);

        Cliente c1 = new Cliente("Maria Oliveira", 45, "São Paulo", "Pinheiros", "Rua dos Pinheiros, 50");
        loja.adicionarCliente(c1);
    }

    public static void main(String[] args) {
        carregarDadosIniciais();

        int opcao;

        do {
            System.out.println("\n--- My Plant ---");
            System.out.println("[1] - Calcular Preço Total e Registrar Venda");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Registro Geral de Vendas");
            System.out.println("[4] - Buscar Total de Vendas por Data");
            System.out.println("[5] - Gerenciar Vendedores");
            System.out.println("[6] - Gerenciar Clientes");
            System.out.println("[7] - Informações da Loja");
            System.out.println("[8] - Sair");

            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1: calcularTotal();    break;
                case 2: calcularTroco();    break;
                case 3: exibirRegistro();   break;
                case 4: buscarVendasPorData(); break;
                case 5: menuVendedores();   break;
                case 6: menuClientes();     break;
                case 7: informacoesLoja();  break;
                case 8: System.out.println("Saindo!"); break;
                default: System.out.println("Opção inválida!"); break;
            }
        } while (opcao != 8);

        scan.close();
    }

    // Vendas

    private static void calcularTotal() {
        System.out.println("Digite a data da venda (dd/MM/yyyy): ");
        String dataString = scan.nextLine();
        LocalDate dataVenda = LocalDate.parse(dataString, formatador);

        System.out.println("Digite a quantidade de plantas: ");
        int quantidade = scan.nextInt();

        System.out.println("Digite o preço da planta: ");
        double preco = scan.nextDouble();
        scan.nextLine();

        double valorTotal = quantidade * preco;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = valorTotal * 0.05;
            System.out.println("Desconto especial de 5% aplicado!");
        }

        double valorComDesconto = valorTotal - desconto;
        System.out.println("O preço final a pagar é: " + valorComDesconto);

        Venda venda = new Venda();
        venda.setDataVenda(dataVenda);
        venda.setQuantidade(quantidade);
        venda.setValorVenda(valorTotal);
        venda.setDescontoAplicado(desconto);
        registroDeVendas.add(venda);
        System.out.println("(Venda salva no registro!)");
    }

    private static void calcularTroco() {
        System.out.println("Digite o valor pago: ");
        double valorPago = scan.nextDouble();
        System.out.println("Digite o preço total: ");
        double total = scan.nextDouble();
        scan.nextLine();
        double troco = valorPago - total;

        if (troco < 0) {
            System.out.println("Valor pago é insuficiente. Faltam: " + (-troco));
        } else {
            System.out.println("O troco é: " + troco);
        }
    }

    private static void exibirRegistro() {
        if (registroDeVendas.isEmpty()) {
            System.out.println("Nenhuma venda foi realizada ainda.");
            return;
        }
        System.out.println("\nRegistro geral de vendas: ");
        for (int i = 0; i < registroDeVendas.size(); i++) {
            Venda v = registroDeVendas.get(i);
            double valorFinal = v.getValorVenda() - v.getDescontoAplicado();
            System.out.println("Venda " + (i + 1) +
                " | Data: " + v.getDataVenda().format(formatador) +
                " | Quantidade: " + v.getQuantidade() +
                " | Valor Bruto: " + v.getValorVenda() +
                " | Desconto: " + v.getDescontoAplicado() +
                " | Valor Final: " + valorFinal);
        }
    }

    private static void buscarVendasPorData() {
        System.out.println("Digite a data que deseja buscar (dd/MM/yyyy): ");
        String dataString = scan.nextLine();
        LocalDate dataBusca = LocalDate.parse(dataString, formatador);

        int totalPlantas = 0, quantidadeVendas = 0;
        double faturamento = 0.0;

        for (Venda v : registroDeVendas) {
            if (v.getDataVenda().equals(dataBusca)) {
                quantidadeVendas++;
                totalPlantas += v.getQuantidade();
                faturamento += (v.getValorVenda() - v.getDescontoAplicado());
            }
        }

        System.out.println("\n--- Resumo de Vendas: " + dataBusca.format(formatador) + " ---");
        if (quantidadeVendas == 0) {
            System.out.println("Nenhuma venda registrada neste dia.");
        } else {
            System.out.println("Total de vendas (recibos): " + quantidadeVendas);
            System.out.println("Total de plantas vendidas: " + totalPlantas);
            System.out.println("Faturamento total do dia: " + faturamento);
        }
        System.out.println("--------------------------------------");
    }

    // Vendedores

    private static void menuVendedores() {
        System.out.println("\n--- Gerenciar Vendedores ---");
        System.out.println("[1] - Cadastrar novo Vendedor");
        System.out.println("[2] - Listar Vendedores");
        System.out.println("[3] - Ver detalhes de um Vendedor");

        int opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1: cadastrarVendedor(); break;
            case 2: listarVendedores();  break;
            case 3: detalhesVendedor();  break;
            default: System.out.println("Opção inválida!"); break;
        }
    }

    private static void cadastrarVendedor() {
        System.out.println("Nome: ");
        String nome = scan.nextLine();
        System.out.println("Idade: ");
        int idade = scan.nextInt();
        scan.nextLine();
        System.out.println("Cidade: ");
        String cidade = scan.nextLine();
        System.out.println("Bairro: ");
        String bairro = scan.nextLine();
        System.out.println("Rua: ");
        String rua = scan.nextLine();
        System.out.println("Salário base: ");
        double salarioBase = scan.nextDouble();
        scan.nextLine();

        loja.adicionarVendedor(new Vendedor(nome, idade, loja, cidade, bairro, rua, salarioBase));
        System.out.println("Vendedor cadastrado com sucesso!");
    }

    private static void listarVendedores() {
        ArrayList<Vendedor> vendedores = loja.getVendedores();
        System.out.println("\nTotal de vendedores: " + loja.contarVendedores());
        for (int i = 0; i < vendedores.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + vendedores.get(i).getNome());
        }
    }

    private static void detalhesVendedor() {
        listarVendedores();
        ArrayList<Vendedor> vendedores = loja.getVendedores();
        if (vendedores.isEmpty()) return;

        System.out.println("Digite o número do vendedor: ");
        int indice = scan.nextInt() - 1;
        scan.nextLine();

        if (indice < 0 || indice >= vendedores.size()) {
            System.out.println("Vendedor não encontrado.");
            return;
        }

        Vendedor v = vendedores.get(indice);
        System.out.println("\n--- Dados do Vendedor ---");
        v.apresentarse();
        System.out.println("Salários recebidos: " + v.getSalarioRecebido()[0] + " | " + v.getSalarioRecebido()[1] + " | " + v.getSalarioRecebido()[2]);
        System.out.println("Média salarial: " + v.calcularMedia());
        System.out.println("Bônus: " + v.calcularBonus());
    }

    // Clientes

    private static void menuClientes() {
        System.out.println("\n--- Gerenciar Clientes ---");
        System.out.println("[1] - Cadastrar novo Cliente");
        System.out.println("[2] - Listar Clientes");
        System.out.println("[3] - Ver detalhes de um Cliente");

        int opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1: cadastrarCliente(); break;
            case 2: listarClientes();   break;
            case 3: detalhesCliente();  break;
            default: System.out.println("Opção inválida!"); break;
        }
    }

    private static void cadastrarCliente() {
        System.out.println("Nome: ");
        String nome = scan.nextLine();
        System.out.println("Idade: ");
        int idade = scan.nextInt();
        scan.nextLine();
        System.out.println("Cidade: ");
        String cidade = scan.nextLine();
        System.out.println("Bairro: ");
        String bairro = scan.nextLine();
        System.out.println("Rua: ");
        String rua = scan.nextLine();

        loja.adicionarCliente(new Cliente(nome, idade, cidade, bairro, rua));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        ArrayList<Cliente> clientes = loja.getClientes();
        System.out.println("\nTotal de clientes: " + loja.contarClientes());
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + clientes.get(i).getNome());
        }
    }

    private static void detalhesCliente() {
        listarClientes();
        ArrayList<Cliente> clientes = loja.getClientes();
        if (clientes.isEmpty()) return;

        System.out.println("Digite o número do cliente: ");
        int indice = scan.nextInt() - 1;
        scan.nextLine();

        if (indice < 0 || indice >= clientes.size()) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("\n--- Dados do Cliente ---");
        clientes.get(indice).apresentarse();
    }

    // Loja

    private static void informacoesLoja() {
        System.out.println("\n--- Informações da Loja ---");
        loja.apresentarse();
        System.out.println("Vendedores cadastrados: " + loja.contarVendedores());
        System.out.println("Clientes cadastrados: " + loja.contarClientes());
    }
}
