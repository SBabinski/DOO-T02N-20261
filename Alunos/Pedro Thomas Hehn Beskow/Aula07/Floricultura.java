import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Floricultura {

    static ArrayList<int[]>     registroQuantidades = new ArrayList<>();
    static ArrayList<double[]>  registroValores     = new ArrayList<>();
    static ArrayList<LocalDate> registroDatas       = new ArrayList<>();

    static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner objScanner = new Scanner(System.in);
        int intOpcao;

        double[] ultimaVenda = {0, 0, 0}; // [total, desconto, totalComDesconto]

        // ── criando endereços ────────────────────────────────────────────────
        Endereco endLoja = new Endereco("SP", "São Paulo", "Centro", "100", "");

        // ── criando a loja ───────────────────────────────────────────────────
        Loja loja = new Loja(
            "My Plant",
            "My Plant Comércio de Plantas Ltda.",
            "12.345.678/0001-99",
            endLoja
        );

        // ── vendedores ───────────────────────────────────────────────────────
        loja.adicionarVendedor(new Vendedor("Carlos Silva", 32,
            new Endereco("SP", "São Paulo", "Centro", "100", ""),
            loja.nomeFantasia, 3000.00));

        loja.adicionarVendedor(new Vendedor("Ana Souza", 28,
            new Endereco("SP", "São Paulo", "Centro", "100", ""),
            loja.nomeFantasia, 2800.00));

        // ── gerente ──────────────────────────────────────────────────────────
        loja.adicionarGerente(new Gerente("Roberta Alves", 40,
            new Endereco("SP", "São Paulo", "Moema", "300", ""),
            loja.nomeFantasia, 6500.00));

        // ── clientes ─────────────────────────────────────────────────────────
        loja.adicionarCliente(new Cliente("Mariana Lima", 45,
            new Endereco("SP", "São Paulo", "Jardins", "500", "Apto 12")));
        loja.adicionarCliente(new Cliente("Roberto Costa", 60,
            new Endereco("SP", "São Paulo", "Moema", "200", "")));
        loja.adicionarCliente(new Cliente("Fernanda Dias", 35,
            new Endereco("SP", "São Paulo", "Pinheiros", "10", "")));

        System.out.println("    Loja de Plantas da Dona Gabi        ");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Calcular Preço Total");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Ver Registro de Vendas");
            System.out.println("4 - Buscar Vendas por Mês e Dia");
            System.out.println("5 - Ver Informações da Loja");
            System.out.println("6 - Cadastrar Vendedor");
            System.out.println("7 - Cadastrar Cliente");
            System.out.println("8 - Editar Vendedor");
            System.out.println("9 - Editar Cliente");
            System.out.println("10 - Excluir Vendedor");
            System.out.println("11 - Excluir Cliente");
            System.out.println("12 - Criar Pedido (dados de demonstração)");
            System.out.println("13 - Sair");
            System.out.print("Escolha uma opção: ");
            intOpcao = objScanner.nextInt();

            switch (intOpcao) {

                // ── case 1: calcular preço total ────────────────────────────
                case 1:
                    System.out.print("\nQuantidade de plantas: ");
                    int intQuantidade = objScanner.nextInt();
                    System.out.print("Preço unitário (R$): ");
                    double doubPreco = objScanner.nextDouble();

                    double doubTotal      = calcularPrecoTotal(intQuantidade, doubPreco);
                    double doubDesconto   = calcularDesconto(intQuantidade, doubTotal);
                    double doubTotalFinal = doubTotal - doubDesconto;

                    System.out.println("\n--- RESUMO DA COMPRA ---");
                    System.out.printf("Subtotal:  R$ %.2f%n", doubTotal);

                    if (doubDesconto > 0) {
                        System.out.printf("Desconto (5%% para +10 plantas): -R$ %.2f%n", doubDesconto);
                    } else {
                        System.out.println("Desconto:  Nenhum");
                    }

                    System.out.printf("Total a pagar: R$ %.2f%n", doubTotalFinal);

                    ultimaVenda[0] = doubTotal;
                    ultimaVenda[1] = doubDesconto;
                    ultimaVenda[2] = doubTotalFinal;

                    registrarVenda(intQuantidade, doubTotal, doubDesconto, doubTotalFinal);
                    break;

                // ── case 2: calcular troco ──────────────────────────────────
                case 2:
                    if (ultimaVenda[2] == 0) {
                        System.out.println("\nNenhuma compra calculada ainda. Use a opção 1 primeiro.");
                        break;
                    }

                    System.out.printf("%nTotal da última compra: R$ %.2f%n", ultimaVenda[2]);
                    System.out.print("Valor recebido (R$): ");
                    double doubRecebido = objScanner.nextDouble();

                    double doubTroco = calcularTroco(doubRecebido, ultimaVenda[2]);

                    System.out.println("\n--- PAGAMENTO ---");
                    System.out.printf("Valor recebido: R$ %.2f%n", doubRecebido);
                    System.out.printf("Total a pagar:  R$ %.2f%n", ultimaVenda[2]);

                    if (doubTroco < 0) {
                        System.out.printf("Valor insuficiente! Faltam R$ %.2f%n", doubTroco * -1);
                    } else {
                        System.out.printf("Troco:          R$ %.2f%n", doubTroco);
                    }
                    break;

                // ── case 3: ver registro de vendas ──────────────────────────
                case 3:
                    exibirRegistro();
                    break;

                // ── case 4: buscar vendas por mês e dia ─────────────────────
                case 4:
                    System.out.print("\nInforme o mês (1-12): ");
                    int intMes = objScanner.nextInt();
                    System.out.print("Informe o dia (1-31): ");
                    int intDia = objScanner.nextInt();

                    if (intMes < 1 || intMes > 12 || intDia < 1 || intDia > 31) {
                        System.out.println("Data inválida! Mês deve ser 1-12 e dia 1-31.");
                        break;
                    }

                    buscarVendasPorMesEDia(intMes, intDia);
                    break;

                // ── case 5: ver informações da loja ─────────────────────────
                case 5:
                    loja.apresentarse();
                    System.out.println();
                    loja.contarGerentes();
                    loja.contarVendedores();
                    loja.contarClientes();

                    System.out.println("\nGerentes cadastrados:");
                    for (Gerente g : loja.gerentes) {
                        g.apresentarse();
                        System.out.printf("  Média salarial : R$ %.2f%n", g.calcularMedia());
                        System.out.printf("  Bônus (35%%)    : R$ %.2f%n", g.calcularBonus());
                    }

                    System.out.println("\nVendedores cadastrados:");
                    for (Vendedor v : loja.vendedores) {
                        v.apresentarse();
                        System.out.printf("  Média salarial : R$ %.2f%n", v.calcularMedia());
                        System.out.printf("  Bônus (20%%)    : R$ %.2f%n", v.calcularBonus());
                    }

                    System.out.println("\nClientes cadastrados:");
                    for (Cliente c : loja.clientes) {
                        c.apresentarse();
                    }
                    break;

                // ── case 6: cadastrar vendedor ──────────────────────────────
                case 6:
                    objScanner.nextLine();
                    System.out.println("\nCadastro de Vendedor");
                    System.out.print("Nome: ");
                    String nomeVendedor = objScanner.nextLine();
                    System.out.print("Idade: ");
                    int idadeVendedor = objScanner.nextInt();
                    objScanner.nextLine();
                    System.out.print("Estado: ");
                    String estadoV = objScanner.nextLine();
                    System.out.print("Cidade: ");
                    String cidadeV = objScanner.nextLine();
                    System.out.print("Bairro: ");
                    String bairroV = objScanner.nextLine();
                    System.out.print("Número: ");
                    String numeroV = objScanner.nextLine();
                    System.out.print("Complemento: ");
                    String complementoV = objScanner.nextLine();
                    System.out.print("Salário Base (R$): ");
                    double salarioVendedor = objScanner.nextDouble();

                    loja.adicionarVendedor(new Vendedor(
                        nomeVendedor, idadeVendedor,
                        new Endereco(estadoV, cidadeV, bairroV, numeroV, complementoV),
                        loja.nomeFantasia, salarioVendedor
                    ));
                    System.out.println("Vendedor " + nomeVendedor + " cadastrado com sucesso!");
                    break;

                // ── case 7: cadastrar cliente ───────────────────────────────
                case 7:
                    objScanner.nextLine();
                    System.out.println("\nCadastro de Cliente");
                    System.out.print("Nome: ");
                    String nomeCliente = objScanner.nextLine();
                    System.out.print("Idade: ");
                    int idadeCliente = objScanner.nextInt();
                    objScanner.nextLine();
                    System.out.print("Estado: ");
                    String estadoC = objScanner.nextLine();
                    System.out.print("Cidade: ");
                    String cidadeC = objScanner.nextLine();
                    System.out.print("Bairro: ");
                    String bairroC = objScanner.nextLine();
                    System.out.print("Número: ");
                    String numeroC = objScanner.nextLine();
                    System.out.print("Complemento: ");
                    String complementoC = objScanner.nextLine();

                    loja.adicionarCliente(new Cliente(
                        nomeCliente, idadeCliente,
                        new Endereco(estadoC, cidadeC, bairroC, numeroC, complementoC)
                    ));
                    System.out.println("Cliente " + nomeCliente + " cadastrado com sucesso!");
                    break;

                // ── case 8: editar vendedor ─────────────────────────────────
                case 8:
                    if (loja.vendedores.isEmpty()) {
                        System.out.println("Nenhum vendedor cadastrado.");
                        break;
                    }
                    System.out.println("\nQual vendedor deseja editar?");
                    for (int i = 0; i < loja.vendedores.size(); i++) {
                        System.out.println((i + 1) + "- " + loja.vendedores.get(i).nome);
                    }
                    System.out.print("Número: ");
                    int indiceEditarV = objScanner.nextInt() - 1;
                    if (indiceEditarV < 0 || indiceEditarV >= loja.vendedores.size()) {
                        System.out.println("Número inválido.");
                        break;
                    }
                    Vendedor vEditar = loja.vendedores.get(indiceEditarV);
                    objScanner.nextLine();
                    System.out.print("Novo nome (" + vEditar.nome + "): ");
                    vEditar.nome = objScanner.nextLine();
                    System.out.print("Nova idade (" + vEditar.idade + "): ");
                    vEditar.idade = objScanner.nextInt();
                    objScanner.nextLine();
                    System.out.print("Nova cidade (" + vEditar.endereco.cidade + "): ");
                    vEditar.endereco.cidade = objScanner.nextLine();
                    System.out.print("Novo bairro (" + vEditar.endereco.bairro + "): ");
                    vEditar.endereco.bairro = objScanner.nextLine();
                    System.out.print("Novo número (" + vEditar.endereco.numero + "): ");
                    vEditar.endereco.numero = objScanner.nextLine();
                    System.out.print("Novo salário base (" + vEditar.salarioBase + "): ");
                    vEditar.salarioBase = objScanner.nextDouble();
                    System.out.println("Vendedor atualizado com sucesso!");
                    break;

                // ── case 9: editar cliente ──────────────────────────────────
                case 9:
                    if (loja.clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                        break;
                    }
                    System.out.println("\nQual cliente deseja editar?");
                    for (int i = 0; i < loja.clientes.size(); i++) {
                        System.out.println((i + 1) + "- " + loja.clientes.get(i).nome);
                    }
                    System.out.print("Número: ");
                    int indiceEditarC = objScanner.nextInt() - 1;
                    if (indiceEditarC < 0 || indiceEditarC >= loja.clientes.size()) {
                        System.out.println("Número inválido.");
                        break;
                    }
                    Cliente cEditar = loja.clientes.get(indiceEditarC);
                    objScanner.nextLine();
                    System.out.print("Novo nome (" + cEditar.nome + "): ");
                    cEditar.nome = objScanner.nextLine();
                    System.out.print("Nova idade (" + cEditar.idade + "): ");
                    cEditar.idade = objScanner.nextInt();
                    objScanner.nextLine();
                    System.out.print("Nova cidade (" + cEditar.endereco.cidade + "): ");
                    cEditar.endereco.cidade = objScanner.nextLine();
                    System.out.print("Novo bairro (" + cEditar.endereco.bairro + "): ");
                    cEditar.endereco.bairro = objScanner.nextLine();
                    System.out.print("Novo número (" + cEditar.endereco.numero + "): ");
                    cEditar.endereco.numero = objScanner.nextLine();
                    System.out.println("Cliente atualizado com sucesso!");
                    break;

                // ── case 10: excluir vendedor ───────────────────────────────
                case 10:
                    if (loja.vendedores.isEmpty()) {
                        System.out.println("Nenhum vendedor cadastrado.");
                        break;
                    }
                    System.out.println("\nQual vendedor deseja excluir?");
                    for (int i = 0; i < loja.vendedores.size(); i++) {
                        System.out.println((i + 1) + "- " + loja.vendedores.get(i).nome);
                    }
                    System.out.print("Número: ");
                    int indiceExcluirV = objScanner.nextInt() - 1;
                    if (indiceExcluirV < 0 || indiceExcluirV >= loja.vendedores.size()) {
                        System.out.println("Número inválido.");
                        break;
                    }
                    System.out.println("Vendedor " + loja.vendedores.get(indiceExcluirV).nome + " excluído.");
                    loja.vendedores.remove(indiceExcluirV);
                    break;

                // ── case 11: excluir cliente ────────────────────────────────
                case 11:
                    if (loja.clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                        break;
                    }
                    System.out.println("\nQual cliente deseja excluir?");
                    for (int i = 0; i < loja.clientes.size(); i++) {
                        System.out.println((i + 1) + "- " + loja.clientes.get(i).nome);
                    }
                    System.out.print("Número: ");
                    int indiceExcluirC = objScanner.nextInt() - 1;
                    if (indiceExcluirC < 0 || indiceExcluirC >= loja.clientes.size()) {
                        System.out.println("Número inválido.");
                        break;
                    }
                    System.out.println("Cliente " + loja.clientes.get(indiceExcluirC).nome + " excluído.");
                    loja.clientes.remove(indiceExcluirC);
                    break;

                // ── case 12: criar pedido (dados de demonstração) ───────────
                case 12:
                    System.out.println("\n--- CRIANDO PEDIDO DE DEMONSTRAÇÃO ---");

                    // itens do pedido fake
                    Item[] itensPedido = {
                        new Item(1, "Orquídea Phalaenopsis", "Flor",   89.90),
                        new Item(2, "Vaso de Cerâmica P",    "Vaso",   45.00),
                        new Item(3, "Adubo Orgânico 500g",   "Insumo", 22.50),
                        new Item(4, "Samambaia Americana",   "Planta", 59.90)
                    };

                    // pega o primeiro cliente e vendedor cadastrados na loja
                    Cliente  clientePedido  = loja.clientes.get(0);
                    Vendedor vendedorPedido = loja.vendedores.get(0);

                    // reserva válida: vence em 7 dias
                    LocalDate hoje              = LocalDate.now();
                    LocalDate vencimentoReserva = hoje.plusDays(7);

                    ProcessaPedido processador = new ProcessaPedido();
                    processador.processar(
                        1001,
                        hoje,
                        hoje,
                        vencimentoReserva,
                        clientePedido,
                        vendedorPedido,
                        loja,
                        itensPedido
                    );
                    break;

                // ── case 13: sair ────────────────────────────────────────────
                case 13:
                    System.out.println("\nSistema Finalizando... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (intOpcao != 13);

        objScanner.close();
    }

    // ── métodos auxiliares ───────────────────────────────────────────────────

    public static double calcularPrecoTotal(int intQuantidade, double doubPrecoUnitario) {
        return intQuantidade * doubPrecoUnitario;
    }

    public static double calcularDesconto(int intQuantidade, double doubTotal) {
        if (intQuantidade > 10) return doubTotal * 0.05;
        return 0;
    }

    public static double calcularTroco(double doubValorRecebido, double doubValorTotal) {
        return doubValorRecebido - doubValorTotal;
    }

    public static void registrarVenda(int intQtd, double doubSubtotal,
                                      double doubDesconto, double doubTotalFinal) {
        registroQuantidades.add(new int[]{intQtd});
        registroValores.add(new double[]{doubSubtotal, doubDesconto, doubTotalFinal});
        registroDatas.add(LocalDate.now());

        System.out.printf("%nVenda registrada em %s com sucesso!%n",
                          LocalDate.now().format(FORMATO_DATA));
    }

    public static void exibirRegistro() {
        if (registroQuantidades.isEmpty()) {
            System.out.println("\nNenhuma venda registrada ainda.");
            return;
        }

        System.out.println("\n         REGISTRO DE VENDAS             ");

        double doubTotalGeral    = 0;
        double doubDescontoGeral = 0;
        int    intQtdGeral       = 0;

        for (int i = 0; i < registroQuantidades.size(); i++) {
            int    qtd      = registroQuantidades.get(i)[0];
            double subtotal = registroValores.get(i)[0];
            double desconto = registroValores.get(i)[1];
            double total    = registroValores.get(i)[2];
            String data     = registroDatas.get(i).format(FORMATO_DATA);

            System.out.printf("%nVenda #%d  [%s]:%n", i + 1, data);
            System.out.printf("  Quantidade vendida : %d plantas%n",  qtd);
            System.out.printf("  Subtotal           : R$ %.2f%n",     subtotal);
            System.out.printf("  Desconto aplicado  : R$ %.2f%n",     desconto);
            System.out.printf("  Total cobrado      : R$ %.2f%n",     total);

            doubTotalGeral    += total;
            doubDescontoGeral += desconto;
            intQtdGeral       += qtd;
        }

        System.out.println();
        System.out.printf("Total de plantas vendidas : %d%n",      intQtdGeral);
        System.out.printf("Total em descontos dados  : R$ %.2f%n", doubDescontoGeral);
        System.out.printf("Total arrecadado          : R$ %.2f%n", doubTotalGeral);
    }

    public static void buscarVendasPorMesEDia(int intMes, int intDia) {
        System.out.printf("%n=== VENDAS DO DIA %02d/%02d ===%n", intDia, intMes);

        int    intVendasEncontradas = 0;
        int    intQtdTotal          = 0;
        double doubTotalArrecadado  = 0;
        double doubTotalDescontos   = 0;

        for (int i = 0; i < registroDatas.size(); i++) {
            LocalDate data = registroDatas.get(i);

            if (data.getMonthValue() == intMes && data.getDayOfMonth() == intDia) {
                int    qtd      = registroQuantidades.get(i)[0];
                double subtotal = registroValores.get(i)[0];
                double desconto = registroValores.get(i)[1];
                double total    = registroValores.get(i)[2];

                intVendasEncontradas++;
                intQtdTotal         += qtd;
                doubTotalArrecadado += total;
                doubTotalDescontos  += desconto;

                System.out.printf("%nVenda #%d  [%s]:%n", i + 1, data.format(FORMATO_DATA));
                System.out.printf("  Quantidade vendida : %d plantas%n",  qtd);
                System.out.printf("  Subtotal           : R$ %.2f%n",     subtotal);
                System.out.printf("  Desconto aplicado  : R$ %.2f%n",     desconto);
                System.out.printf("  Total cobrado      : R$ %.2f%n",     total);
            }
        }

        if (intVendasEncontradas == 0) {
            System.out.printf("Nenhuma venda encontrada para %02d/%02d.%n", intDia, intMes);
        } else {
            System.out.println("\n--- RESUMO DO DIA ---");
            System.out.printf("Total de vendas realizadas : %d%n",      intVendasEncontradas);
            System.out.printf("Total de plantas vendidas  : %d%n",      intQtdTotal);
            System.out.printf("Total em descontos dados   : R$ %.2f%n", doubTotalDescontos);
            System.out.printf("Total arrecadado no dia    : R$ %.2f%n", doubTotalArrecadado);
        }
    }
}
