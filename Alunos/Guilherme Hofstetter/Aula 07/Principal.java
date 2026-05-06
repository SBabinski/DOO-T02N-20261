import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Principal {
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Venda> registroDeVendas = new ArrayList<>();
    private static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static Loja loja = new Loja(
        "My Plant",
        "My Plant Comércio de Plantas LTDA",
        "12.345.678/0001-99",
        new Endereco("SP", "São Paulo", "Centro", "Rua das Flores", 100, "Loja Principal")
    );

    private static void carregarDadosIniciais() {
        Endereco endV1 = new Endereco("SP", "São Paulo", "Centro", "Rua das Flores", 100, "");
        Endereco endV2 = new Endereco("SP", "São Paulo", "Jardins", "Av. Paulista", 200, "");
        Endereco endV3 = new Endereco("SP", "São Paulo", "Mooca", "Rua da Mooca", 300, "");
        Endereco endG1 = new Endereco("SP", "São Paulo", "Centro", "Rua das Flores", 100, "");
        Endereco endC1 = new Endereco("SP", "São Paulo", "Pinheiros", "Rua dos Pinheiros", 50, "Apto 1");

        loja.adicionarVendedor(new Vendedor("Carlos Silva", 35, endV1, loja, 2500.00));
        loja.adicionarVendedor(new Vendedor("Ana Souza", 28, endV2, loja, 2200.00));
        loja.adicionarVendedor(new Vendedor("João Pereira", 42, endV3, loja, 3000.00));
        loja.adicionarGerente(new Gerente("Fernanda Lima", 40, endG1, loja, 5000.00));
        loja.adicionarCliente(new Cliente("Maria Oliveira", 45, endC1));
    }

    public static void main(String[] args) {
        carregarDadosIniciais();
        int opcao;

        do {
            System.out.println("\\n--- My Plant ---");
            System.out.println("[1] - Calcular Preço Total e Registrar Venda");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Registro Geral de Vendas");
            System.out.println("[4] - Buscar Total de Vendas por Data");
            System.out.println("[5] - Gerenciar Vendedores");
            System.out.println("[6] - Gerenciar Clientes");
            System.out.println("[7] - Gerenciar Gerentes");
            System.out.println("[8] - Informações da Loja");
            System.out.println("[9] - Criar Pedido");
            System.out.println("[10] - Sair");

            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1: calcularTotal();       break;
                case 2: calcularTroco();       break;
                case 3: exibirRegistro();      break;
                case 4: buscarVendasPorData(); break;
                case 5: menuVendedores();      break;
                case 6: menuClientes();        break;
                case 7: menuGerentes();        break;
                case 8: informacoesLoja();     break;
                case 9: criarPedido();         break;
                case 10: System.out.println("Saindo!"); break;
                default: System.out.println("Opção inválida!"); break;
            }
        } while (opcao != 10);

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
        System.out.println("Estado: ");
        String estado = scan.nextLine();
        System.out.println("Cidade: ");
        String cidade = scan.nextLine();
        System.out.println("Bairro: ");
        String bairro = scan.nextLine();
        System.out.println("Rua: ");
        String rua = scan.nextLine();
        System.out.println("Número: ");
        int numero = scan.nextInt();
        scan.nextLine();
        System.out.println("Complemento: ");
        String complemento = scan.nextLine();
        System.out.println("Salário base: ");
        double salarioBase = scan.nextDouble();
        scan.nextLine();

        Endereco end = new Endereco(estado, cidade, bairro, rua, numero, complemento);
        loja.adicionarVendedor(new Vendedor(nome, idade, end, loja, salarioBase));
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
        System.out.println("Salários: " + v.getSalarioRecebido()[0] + " | " + v.getSalarioRecebido()[1] + " | " + v.getSalarioRecebido()[2]);
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
        System.out.println("Estado: ");
        String estado = scan.nextLine();
        System.out.println("Cidade: ");
        String cidade = scan.nextLine();
        System.out.println("Bairro: ");
        String bairro = scan.nextLine();
        System.out.println("Rua: ");
        String rua = scan.nextLine();
        System.out.println("Número: ");
        int numero = scan.nextInt();
        scan.nextLine();
        System.out.println("Complemento: ");
        String complemento = scan.nextLine();

        Endereco end = new Endereco(estado, cidade, bairro, rua, numero, complemento);
        loja.adicionarCliente(new Cliente(nome, idade, end));
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

    // Gerentes

    private static void menuGerentes() {
        System.out.println("\n--- Gerenciar Gerentes ---");
        System.out.println("[1] - Cadastrar novo Gerente");
        System.out.println("[2] - Listar Gerentes");
        System.out.println("[3] - Ver detalhes de um Gerente");

        int opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1: cadastrarGerente(); break;
            case 2: listarGerentes();   break;
            case 3: detalhesGerente();  break;
            default: System.out.println("Opção inválida!"); break;
        }
    }

    private static void cadastrarGerente() {
        System.out.println("Nome: ");
        String nome = scan.nextLine();
        System.out.println("Idade: ");
        int idade = scan.nextInt();
        scan.nextLine();
        System.out.println("Estado: ");
        String estado = scan.nextLine();
        System.out.println("Cidade: ");
        String cidade = scan.nextLine();
        System.out.println("Bairro: ");
        String bairro = scan.nextLine();
        System.out.println("Rua: ");
        String rua = scan.nextLine();
        System.out.println("Número: ");
        int numero = scan.nextInt();
        scan.nextLine();
        System.out.println("Complemento: ");
        String complemento = scan.nextLine();
        System.out.println("Salário base: ");
        double salarioBase = scan.nextDouble();
        scan.nextLine();

        Endereco end = new Endereco(estado, cidade, bairro, rua, numero, complemento);
        loja.adicionarGerente(new Gerente(nome, idade, end, loja, salarioBase));
        System.out.println("Gerente cadastrado com sucesso!");
    }

    private static void listarGerentes() {
        ArrayList<Gerente> gerentes = loja.getGerentes();
        System.out.println("\nTotal de gerentes: " + loja.contarGerentes());
        for (int i = 0; i < gerentes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + gerentes.get(i).getNome());
        }
    }

    private static void detalhesGerente() {
        listarGerentes();
        ArrayList<Gerente> gerentes = loja.getGerentes();
        if (gerentes.isEmpty()) return;

        System.out.println("Digite o número do gerente: ");
        int indice = scan.nextInt() - 1;
        scan.nextLine();

        if (indice < 0 || indice >= gerentes.size()) {
            System.out.println("Gerente não encontrado.");
            return;
        }

        Gerente g = gerentes.get(indice);
        System.out.println("\n--- Dados do Gerente ---");
        g.apresentarse();
        System.out.println("Salários: " + g.getSalarioRecebido()[0] + " | " + g.getSalarioRecebido()[1] + " | " + g.getSalarioRecebido()[2]);
        System.out.println("Média salarial: " + g.calcularMedia());
        System.out.println("Bônus: " + g.calcularBonus());
    }

    // Loja

    private static void informacoesLoja() {
        System.out.println("\n--- Informações da Loja ---");
        loja.apresentarse();
        System.out.println("Vendedores cadastrados: " + loja.contarVendedores());
        System.out.println("Gerentes cadastrados: " + loja.contarGerentes());
        System.out.println("Clientes cadastrados: " + loja.contarClientes());
    }

    // Pedido

    private static void criarPedido() {
        ArrayList<Cliente> clientes = loja.getClientes();
        ArrayList<Vendedor> vendedores = loja.getVendedores();

        if (clientes.isEmpty() || vendedores.isEmpty()) {
            System.out.println("É necessário ter ao menos um cliente e um vendedor cadastrados.");
            return;
        }

        Date hoje = new Date();
        Date vencimento = new Date(hoje.getTime() + 3 * 86400000L);

        Cliente cliente = clientes.get(0);
        Vendedor vendedor = vendedores.get(0);

        Pedido pedido = new Pedido(1, hoje, null, vencimento, cliente, vendedor, loja);
        pedido.adicionarItem(new Item(1, "Samambaia", "Planta", 45.00));
        pedido.adicionarItem(new Item(2, "Vaso Cerâmica", "Acessório", 30.00));
        pedido.adicionarItem(new Item(3, "Adubo", "Insumo", 15.00));

        System.out.println("\n--- Itens do Pedido ---");
        for (Item item : pedido.getItens()) {
            item.gerarDescricao();
        }

        new ProcessaPedido().processar(pedido);
    }
}
