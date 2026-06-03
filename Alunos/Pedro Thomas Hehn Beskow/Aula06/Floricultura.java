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

        // criando a loja com seus vendedores e clientes
        Loja loja = new Loja(
            "My Plant",
            "My Plant Comércio de Plantas Ltda.",
            "12.345.678/0001-99",
            "São Paulo", "Centro", "Rua das Flores, 100"
        );

        loja.adicionarVendedor(new Vendedor("Carlos Silva", 32, loja.nomeFantasia, "São Paulo", "Centro", "Rua das Flores, 100", 3000.00));
        loja.adicionarVendedor(new Vendedor("Ana Souza",    28, loja.nomeFantasia, "São Paulo", "Centro", "Rua das Flores, 100", 2800.00));

        loja.adicionarCliente(new Cliente("Mariana Lima",  45, "São Paulo", "Jardins",   "Alameda Santos, 500"));
        loja.adicionarCliente(new Cliente("Roberto Costa", 60, "São Paulo", "Moema",     "Av. Ibirapuera, 200"));
        loja.adicionarCliente(new Cliente("Fernanda Dias", 35, "São Paulo", "Pinheiros", "Rua Teodoro Sampaio, 10"));

        System.out.println("    Loja de Plantas da Dona Gabi        ");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1- Calcular Preço Total");
            System.out.println("2- Calcular Troco");
            System.out.println("3- Ver Registro de Vendas");
            System.out.println("4- Buscar Vendas por Mês e Dia");
            System.out.println("5- Ver Informações da Loja");
            System.out.println("6- Cadastrar Vendedor");
            System.out.println("7- Cadastrar Cliente");
            System.out.println("8- Editar Vendedor");
            System.out.println("9- Editar Cliente");
            System.out.println("10- Excluir Vendedor");
            System.out.println("11- Excluir Cliente");
            System.out.println("12- Sair");
            System.out.print("Escolha uma opção: ");
            intOpcao = objScanner.nextInt();

            switch (intOpcao) {

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

                case 3:
                    exibirRegistro();
                    break;

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

                case 5:
                    // mostra tudo sobre a loja, os vendedores e os clientes
                    loja.apresentarse();
                    System.out.println();
                    loja.contarVendedores();
                    loja.contarClientes();

                    System.out.println("\nVendedores cadastrados:");
                    for (Vendedor v : loja.vendedores) {
                        v.apresentarse();
                        System.out.printf("  Média salarial : R$ %.2f%n", v.calcularMedia());
                        System.out.printf("  Bônus          : R$ %.2f%n", v.calcularBonus());
                    }

                    System.out.println("\nClientes cadastrados:");
                    for (Cliente c : loja.clientes) {
                        c.apresentarse();
                    }
                    break;

                case 6:
                    objScanner.nextLine(); // limpa o buffer antes de ler strings
                    System.out.println("\nCadastro de Vendedor");
                    System.out.print("Nome: ");
                    String nomeVendedor = objScanner.nextLine();
                    System.out.print("Idade: ");
                    int idadeVendedor = objScanner.nextInt();
                    objScanner.nextLine();
                    System.out.print("Cidade: ");
                    String cidadeVendedor = objScanner.nextLine();
                    System.out.print("Bairro: ");
                    String bairroVendedor = objScanner.nextLine();
                    System.out.print("Rua: ");
                    String ruaVendedor = objScanner.nextLine();
                    System.out.print("Salário Base (R$): ");
                    double salarioVendedor = objScanner.nextDouble();

                    loja.adicionarVendedor(new Vendedor(nomeVendedor, idadeVendedor, loja.nomeFantasia, cidadeVendedor, bairroVendedor, ruaVendedor, salarioVendedor));
                    System.out.println("Vendedor " + nomeVendedor + " cadastrado com sucesso!");
                    break;

                case 7:
                    objScanner.nextLine(); // limpa o buffer antes de ler strings
                    System.out.println("\nCadastro de Cliente");
                    System.out.print("Nome: ");
                    String nomeCliente = objScanner.nextLine();
                    System.out.print("Idade: ");
                    int idadeCliente = objScanner.nextInt();
                    objScanner.nextLine();
                    System.out.print("Cidade: ");
                    String cidadeCliente = objScanner.nextLine();
                    System.out.print("Bairro: ");
                    String bairroCliente = objScanner.nextLine();
                    System.out.print("Rua: ");
                    String ruaCliente = objScanner.nextLine();

                    loja.adicionarCliente(new Cliente(nomeCliente, idadeCliente, cidadeCliente, bairroCliente, ruaCliente));
                    System.out.println("Cliente " + nomeCliente + " cadastrado com sucesso!");
                    break;

                case 8:
                    // editar vendedor: mostra a lista, usuário escolhe pelo número
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
                    System.out.print("Nova cidade (" + vEditar.cidade + "): ");
                    vEditar.cidade = objScanner.nextLine();
                    System.out.print("Novo bairro (" + vEditar.bairro + "): ");
                    vEditar.bairro = objScanner.nextLine();
                    System.out.print("Nova rua (" + vEditar.rua + "): ");
                    vEditar.rua = objScanner.nextLine();
                    System.out.print("Novo salário base (" + vEditar.salarioBase + "): ");
                    vEditar.salarioBase = objScanner.nextDouble();
                    System.out.println("Vendedor atualizado com sucesso!");
                    break;

                case 9:
                    // editar cliente: mesma lógica do vendedor
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
                    System.out.print("Nova cidade (" + cEditar.cidade + "): ");
                    cEditar.cidade = objScanner.nextLine();
                    System.out.print("Novo bairro (" + cEditar.bairro + "): ");
                    cEditar.bairro = objScanner.nextLine();
                    System.out.print("Nova rua (" + cEditar.rua + "): ");
                    cEditar.rua = objScanner.nextLine();
                    System.out.println("Cliente atualizado com sucesso!");
                    break;

                case 10:
                    // excluir vendedor
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

                case 11:
                    // excluir cliente
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

                case 12:
                    System.out.println("\nSistema Finalizando... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (intOpcao != 12);

        objScanner.close();
    }

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
