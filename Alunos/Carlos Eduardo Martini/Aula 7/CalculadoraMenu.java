
//aula 7
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CalculadoraMenu {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Historico> historicoVendas = new ArrayList<>();
    static ArrayList<Vendedor> listaDeVendedores = new ArrayList<>();
    static ArrayList<Cliente> listaDeClientes = new ArrayList<>();
    static Loja minhaLoja;

    public static void main(String[] args) {
        criaClasses();
        Menu();
        scan.close();
    }

    public static void ValidarResp(int resp) {
        switch (resp) {
            case 1:
                limparConsole();
                menuPedidos();
                aguardarEnter();
                break;
            case 2:
                limparConsole();
                CalcularTroco();
                aguardarEnter();
                break;
            case 3:
                limparConsole();
                exibirHistorico();
                aguardarEnter();
                break;
            case 4:
                limparConsole();
                sobreLoja();
                break;
            case 0:
                limparConsole();
                break;

            default:
                System.out.println("Insira um valor valido!!!7");
                break;
        }
    }

    public static void validarLoja(int respL) {
        switch (respL) {
            case 1:
                limparConsole();
                System.out.println("-------Lista-de-funcionarios-------");
                for (int x = 0; x < 3; x++) {
                    Vendedor v = listaDeVendedores.get(x);
                    v.apresentarse();
                    v.calcularMedia();
                    v.calcularBonus();
                    System.out.println("-----------------------------------");
                }
                aguardarEnter();
                break;
            case 2:
                limparConsole();
                System.out.println("-------Lista-de-Clientes-------");
                for (int x = 0; x < listaDeClientes.size(); x++) {
                    Cliente c = listaDeClientes.get(x);
                    c.apresentarse();
                }
                aguardarEnter();
                break;
            case 3:
                limparConsole();
                System.out.println("-------Informações-da-Loja-------");
                minhaLoja.apresentarse();
                minhaLoja.contarClientes();
                minhaLoja.contarVendedores();
                System.out.println("----------------------------------");
                aguardarEnter();
                break;

            default:
                System.out.println("Insira um valor valido!!!");
                break;
        }
    }

    public static void sobreLoja() {
        int respL = 1000;
        do {
            limparConsole();
            System.out.println("-----------Sobre-a-Loja-----------");
            System.out.println("[1] - Funcionarios");
            System.out.println("[2] - Clientes");
            System.out.println("[3] - Loja");
            System.out.println("[0] - Sair");
            respL = scan.nextInt();
            scan.nextLine();
            validarLoja(respL);
        } while (respL != 0);
    }

    public static void Menu() {
        int resp = 1000;
        do {
            limparConsole();
            System.out.println("-----------Menu-----------");
            System.out.println("[1] - Pedidos");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Historico");
            System.out.println("[4] - Sobre a Loja");
            System.out.println("[0] - Sair");
            resp = scan.nextInt();
            scan.nextLine();
            ValidarResp(resp);
        } while (resp != 0);
        System.out.println("Obrigado por utilizar a calculadora!!!");
    }

    public static void exibirHistorico() {
        double totalVendasDia = 0;
        System.out.println("-----Historico-de-Vendas-----");

        if (historicoVendas.isEmpty()) {
            System.out.println("nenhuma venda registrada");
        } else {
            System.out.println("insira o dia da venda:");
            int diaH = scan.nextInt();
            scan.nextLine();
            System.out.println("insira o mes da venda:");
            int mesH = scan.nextInt();
            scan.nextLine();
            for (Historico novaVenda : historicoVendas) {
                if (novaVenda.hoje.getDayOfMonth() == diaH && novaVenda.hoje.getMonthValue() == mesH) {
                    System.out.printf("Quantidade: %d, Total: %.2f, Desconto: %.2f%n", novaVenda.quantPlant,
                            novaVenda.resultTotal, novaVenda.valorDesc);
                    if (novaVenda.valorDesc == 0) {
                        totalVendasDia = totalVendasDia + novaVenda.resultTotal;
                    } else {
                        totalVendasDia = totalVendasDia + novaVenda.valorDesc;
                    }

                }
            }
            System.out.println("total das vendas desse dia: " + totalVendasDia);
        }
    }

    public static void menuPedidos() {
        int respP = 1000;
        do {
            System.out.println("-----------Menu-Pedidos-----------");
            System.out.println("[1] - Simular pedido");
            System.out.println("[2] - Calcular preço total");
            System.out.println("[0] - Sair");
            respP = scan.nextInt();
            scan.nextLine();
            validarPedido(respP);
        } while (respP != 0);

    }

    public static void validarPedido(int respP) {
        switch (respP) {
            case 1:
                limparConsole();
                simularPedido();
                aguardarEnter();
                break;
            case 2:
                limparConsole();
                PreçoTotal();
                aguardarEnter();
                break;
            case 0:
                break;

            default:
                System.out.println("insira uma opção valida!!!");
                break;
        }
    }

    public static void simularPedido() {
        System.out.println("--- Gerando Pedidos de Teste ---");

        Item i1 = new Item(1, "Orquidea", "Flor", 50.0, "Orquidea Phalaenopsis");
        ArrayList<Item> itensPedido1 = new ArrayList<>();
        itensPedido1.add(i1);

        Date hoje = new Date();
        long tresDias = 3L * 24 * 60 * 60 * 1000;
        Date vencimento1 = new Date(hoje.getTime() + tresDias);

        Pedidos pedido1 = new Pedidos(101, listaDeClientes.get(0), listaDeVendedores.get(0), minhaLoja, itensPedido1,
                hoje, vencimento1);
        System.out.println("Pedido 101 gerado para: " + pedido1.cliente.nome);

        Item i2 = new Item(2, "Vaso de Barro", "Acessório", 30.0, "Vaso decorativo");
        Item i3 = new Item(3, "Girassol", "Flor", 20.0, "Girassol de Jardim");

        ArrayList<Item> itensPedido2 = new ArrayList<>();
        itensPedido2.add(i2);
        itensPedido2.add(i3);

        Pedidos pedido2 = new Pedidos(102, listaDeClientes.get(1), listaDeVendedores.get(1), minhaLoja, itensPedido2,
                hoje, vencimento1);
        System.out.println("Pedido 102 gerado para: " + pedido2.cliente.nome + " (2 itens)");

        long cincoDiasAtras = 5L * 24 * 60 * 60 * 1000;
        Date dataPassada = new Date(hoje.getTime() - cincoDiasAtras);

        Date vencimentoPassado = new Date(hoje.getTime() - (2L * 24 * 60 * 60 * 1000));

        Pedidos pedidoVencido = new Pedidos(103, listaDeClientes.get(2), listaDeVendedores.get(2), minhaLoja, itensPedido1, dataPassada, vencimentoPassado);
        System.out.println("Pedido " + pedidoVencido.id + " gerado como VENCIDO para: " + pedidoVencido.cliente.nome);

        // --- PROCESSANDO OS PEDIDOS GERADOS ---
        System.out.println("\n--- Iniciando Validação de Pagamentos ---");
        ProcessaPedido processador = new ProcessaPedido();
        
        processador.processar(pedido1);
        System.out.println("-------------------------");
        processador.processar(pedido2);
        System.out.println("-------------------------");
        processador.processar(pedidoVencido);

        System.out.println("\nTodos os pedidos foram carregados na memória do sistema!");
    }

    public static void PreçoTotal() {
        System.out.println("-----Calcular-Preço-Total-----");
        System.out.println("Adicione a quantidade de plantas vendidas");
        int quantPlant = scan.nextInt();
        scan.nextLine();
        System.out.println("Adicione o preco da plantas vendida");
        double precoPlant = scan.nextDouble();
        scan.nextLine();
        double resultTotal = quantPlant * precoPlant;
        double valorDesc;
        LocalDate hoje = LocalDate.now();
        if (quantPlant < 10) {
            System.out.printf("O preco total da venda sera %.2f%n", resultTotal);
            valorDesc = 0;
        } else {
            valorDesc = resultTotal * 0.05;
            valorDesc = resultTotal - valorDesc;
            System.out.printf("O original da venda seria %.2f%n", resultTotal);
            System.out.printf("Com desconto fica %.2f%n", valorDesc);
        }

        registrarVenda(quantPlant, resultTotal, valorDesc, hoje);
    }

    public static void CalcularTroco() {

        System.out.println("-----Calcular-Troco-----");
        System.out.println("O preco que o cliente pagou");
        double precoCliente = scan.nextDouble();
        scan.nextLine();
        System.out.println("adicione o total da venda");
        double precoTotal = scan.nextDouble();
        scan.nextLine();

        double troco = precoCliente - precoTotal;
        if (troco > 0) {
            System.out.printf("Voce deve %.2f de troco!%n", troco);
        } else if (troco < 0) {
            System.out.printf("O cliente ainda deve %.2f%n", troco);
        } else if (troco == 0) {
            System.out.println("Ninguem deve nada!!!");
        }
    }

    public static void criaClasses() {

        Endereco endV1 = new Endereco("Cascavel", "Centro", "Rua Paraná", "PR", 100, "Loja 01");
        Vendedor v1 = new Vendedor("José", 30, "My Plant", endV1, 2000.0, new double[] { 1500.0, 1800.0, 2100.0 });

        Endereco endV2 = new Endereco("Cascavel", "Neva", "Rua Pio XII", "PR", 250, "Apto 12");
        Vendedor v2 = new Vendedor("Maria", 19, "My Plant", endV2, 1800.0, new double[] { 1400.0, 1600.0, 1900.0 });

        Endereco endV3 = new Endereco("Cascavel", "Cancelli", "Rua Tuiuti", "PR", 50, "Casa");
        Vendedor v3 = new Vendedor("Mauro", 60, "My Plant", endV3, 2000.0, new double[] { 2000.0, 2200.0, 2100.0 });

        listaDeVendedores.add(v1);
        listaDeVendedores.add(v2);
        listaDeVendedores.add(v3);

        Endereco endC1 = new Endereco("Cascavel", "Bairro 1", "Rua das Flores", "PR", 10, "Casa");
        Cliente c1 = new Cliente("Carlos", 19, endC1);

        Endereco endC2 = new Endereco("Cascavel", "Bairro 2", "Rua das Palmeiras", "PR", 22, "Fundos");
        Cliente c2 = new Cliente("Gabriela", 18, endC2);

        Endereco endC3 = new Endereco("Cascavel", "Bairro 1", "Rua 2", "PR", 38, "Casa");
        Cliente c3 = new Cliente("Adriani", 38, endC3);

        listaDeClientes.add(c1);
        listaDeClientes.add(c2);
        listaDeClientes.add(c3);

        Endereco endLoja = new Endereco("Cascavel", "Centro", "Rua C", "PR", 1000, "Térreo");

        Vendedor[] vends = { v1, v2, v3 };
        Cliente[] clis = { c1, c2, c3 };

        minhaLoja = new Loja("My Plant", "Flores LTDA", "00.000/0001-00", endLoja, vends, clis);
    }

    public static void registrarVenda(int quantPlant, double resultTotal, double valorDesc, LocalDate hoje) {
        Historico novaVenda = new Historico(quantPlant, resultTotal, valorDesc, hoje);
        historicoVendas.add(novaVenda);
    }

    public static void limparConsole() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void aguardarEnter() {
        System.out.println("\nPresione Enter para continuar...");
        scan.nextLine();
    }
}