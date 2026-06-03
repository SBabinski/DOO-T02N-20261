import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculadora {
    public static ArrayList<Pedido> pedidos = new ArrayList<>();
    public static ArrayList<Item> itensCarrinho = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);

    static Endereco endereco = new Endereco("PR", "Cafelandia", "98", "Parque", "Rua das Flores", "Apto 2");
    static Loja loja = new Loja("Copacol", "Copacol Ltda", "12.398.142/0001-98", endereco);
    static Vendedor vendedor = new Vendedor("Joao", 25, endereco);
    static Cliente cliente = new Cliente("Maria", 30, endereco);

    static {
        loja.adicionarFuncionario(vendedor);
    }

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int escolha = 0;
        while (escolha != 6) {
            System.out.println("\n===========================");
            System.out.println("[1] - Adicionar item ao carrinho");
            System.out.println("[2] - Finalizar compra");
            System.out.println("[3] - Mostrar pedidos");
            System.out.println("[4] - Filtrar pedidos por data");
            System.out.println("[5] - Rodar testes");
            System.out.println("[6] - Sair");
            System.out.println("===========================");
            escolha = scan.nextInt();
            scan.nextLine();
            validarEscolha(escolha);
        }
    }

    public static void validarEscolha(int escolha) {
        switch (escolha) {
            case 1:
                adicionarItem();
                break;
            case 2:
                finalizarCompra();
                break;
            case 3:
                mostrarPedidos();
                break;
            case 4:
                filtrarPedidos();
                break;
            case 5:
                rodarTestes();
                break;
            default:
                if (escolha != 6) {
                    System.out.println("Digite uma opcao valida!");
                }
                break;
        }
    }

    public static void adicionarItem() {
        System.out.println("Digite o id do item:");
        String id = scan.nextLine();
        System.out.println("Digite o nome do item:");
        String nome = scan.nextLine();
        System.out.println("Digite o tipo do item:");
        String tipo = scan.nextLine();
        System.out.println("Digite o valor do item:");
        Double valor = scan.nextDouble();
        scan.nextLine();

        Item item = new Item(id, nome, tipo, valor);
        itensCarrinho.add(item);
        System.out.println("Item adicionado: " + item.gerarDescricao());
    }

    public static void finalizarCompra() {
        if (itensCarrinho.isEmpty()) {
            System.out.println("Carrinho vazio! Adicione itens antes de finalizar.");
            return;
        }

        System.out.println("\n--- RESUMO DO CARRINHO ---");
        double total = 0;
        for (Item item : itensCarrinho) {
            System.out.println(item.gerarDescricao());
            total += item.getValor();
        }

        double desconto = 0;
        if (itensCarrinho.size() > 10) {
            desconto = total * 0.05;
            total -= desconto;
            System.out.printf("Desconto de 5%% aplicado! Voce economizou R$ %.2f%n", desconto);
        }
        System.out.printf("TOTAL: R$ %.2f%n", total);

        System.out.println("Digite o valor entregue ao caixa:");
        double valorEntregue = scan.nextDouble();
        scan.nextLine();

        double troco = valorEntregue - total;
        if (troco < 0) {
            System.out.println("Valor insuficiente! Compra cancelada.");
            return;
        }

        System.out.printf("Troco: R$ %.2f%n", troco);

        String idPedido = "PED-" + (pedidos.size() + 1);
        Pedido pedido = ProcessaPedido.processar(idPedido, LocalDate.now(),cliente, vendedor, loja, new ArrayList<>(itensCarrinho));
        ProcessaPedido.processarPagamento(pedido);
        pedidos.add(pedido);

        itensCarrinho.clear();
    }

    public static void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido realizado ainda.");
            return;
        }
        System.out.println("\n--- PEDIDOS ---");
        for (Pedido pedido : pedidos) {
            pedido.gerarDescricaoVenda();
            System.out.println("---");
        }
    }

    public static void filtrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido para filtrar.");
            return;
        }

        LocalDate dataFiltro = Date.retornaDataFiltro();
        int encontrados = 0;
        double totalDia = 0;

        System.out.println("\n--- PEDIDOS DO DIA " + dataFiltro + " ---");
        for (Pedido pedido : pedidos) {
            if (pedido.getDataCriacao().equals(dataFiltro)) {
                pedido.gerarDescricaoVenda();
                totalDia += pedido.calcularValorTotal();
                encontrados++;
                System.out.println("---");
            }
        }

        if (encontrados == 0) {
            System.out.println("Nenhum pedido encontrado nessa data.");
        } else {
            System.out.println("Total de pedidos no dia: " + encontrados);
            System.out.printf("Valor total do dia: R$ %.2f%n", totalDia);
        }
    }
    public static void rodarTestes(){
        Testes.main(null);
    }
}