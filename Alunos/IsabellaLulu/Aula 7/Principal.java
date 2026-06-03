import java.util.*;

public class Principal {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Loja> lojas = new ArrayList<>();

    public static void main(String[] args) {
        lojas.add(new Loja("My Plant"));
        menuPrincipal();
    }

    public static void menuPrincipal() {
        int op;
        do {
            System.out.println("\n=== SISTEMA MY PLANT ===");
            System.out.println("[1] Gerenciar Lojas");
            System.out.println("[0] Sair");

            op = sc.nextInt();
            sc.nextLine();

            if (op == 1) menuLojas();

        } while (op != 0);
    }

    public static void menuLojas() {
        int op;
        do {
            System.out.println("\n=== LOJAS ===");
            System.out.println("[1] Adicionar Loja");
            System.out.println("[2] Listar Lojas");
            System.out.println("[3] Selecionar Loja");
            System.out.println("[0] Voltar");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    System.out.print("Nome: ");
                    lojas.add(new Loja(sc.nextLine()));
                }
                case 2 -> {
                    for (int i = 0; i < lojas.size(); i++) {
                        System.out.println((i + 1) + " - " + lojas.get(i).getNomeFantasia());
                    }
                }
                case 3 -> {
                    for (int i = 0; i < lojas.size(); i++) {
                        System.out.println((i + 1) + " - " + lojas.get(i).getNomeFantasia());
                    }

                    System.out.print("Escolha: ");
                    int escolha = sc.nextInt();
                    sc.nextLine();

                    Loja loja = lojas.get(escolha - 1);
                    menuLoja(loja);
                }
            }

        } while (op != 0);
    }

    public static void menuLoja(Loja loja) {
        int op;
        do {
            System.out.println("\n=== LOJA: " + loja.getNomeFantasia() + " ===");
            System.out.println("[1] Clientes");
            System.out.println("[2] Vendedores");
            System.out.println("[3] Criar Venda");
            System.out.println("[4] Histórico");
            System.out.println("[0] Voltar");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> menuCliente(loja);
                case 2 -> menuVendedor(loja);
                case 3 -> criarPedido(loja);
                case 4 -> loja.listarPedidos();
            }

        } while (op != 0);
    }

    public static void menuCliente(Loja loja) {
        System.out.print("Nome: ");
        Cliente c = new Cliente(sc.nextLine(), 20,
                new Endereco("PR", "Cascavel", "Centro", 1, "Casa"));
        loja.adicionarCliente(c);
    }

    public static void menuVendedor(Loja loja) {
        System.out.print("Nome: ");
        Vendedor v = new Vendedor(sc.nextLine(), 20,
                new Endereco("PR", "Cascavel", "Centro", 1, "Casa"),
                loja, 2000);
        loja.adicionarVendedor(v);
    }

   public static void criarPedido(Loja loja) {

    if (loja.getClientes().isEmpty() || loja.getVendedores().isEmpty()) {
        System.out.println("Precisa ter cliente e vendedor!");
        return;
    }

    Cliente cliente = loja.getClientes().get(0);
    Vendedor vendedor = loja.getVendedores().get(0);

    System.out.print("Quantidade: ");
    int qtd = sc.nextInt();

    System.out.print("Preço unitário: ");
    double preco = sc.nextDouble();

    ProcessaPedido processador = new ProcessaPedido();

    Pedido pedido = processador.processar(cliente, vendedor, loja, qtd, preco);

    loja.adicionarPedido(pedido);

    double total = pedido.calcularValorTotal();

    System.out.print("Valor recebido: ");
    double recebido = sc.nextDouble();

    if (recebido < total) {
        System.out.println("Valor insuficiente! Faltam R$ " + (total - recebido));
        return;
    }

    double troco = processador.calcularTroco(recebido, total);

    pedido.gerarDescricaoVenda();
    System.out.println("Troco: " + troco);

}
}