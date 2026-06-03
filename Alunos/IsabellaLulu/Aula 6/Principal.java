
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Venda> vendas = new ArrayList<>();
    static Loja loja = new Loja(
            "My Plant",
            "My Plant LTDA",
            "123456789",
            "Cascavel",
            "Centro",
            "Rua Principal"
    );

    public static void main(String[] args) {
        mostrarMenu();

    }

    public static void mostrarMenu() {
        int opcao;

        do {
            System.out.printf("\n====My Plants====\n");
            System.out.println("[1] Calcular Preço Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Histórico de Vendas");
            System.out.println("[4] Menu Vendedor");
            System.out.println("[5] Menu Clientes");
            System.out.println("[6] Menu Loja");
            System.out.println("[0] Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            validarOpcao(opcao);

        } while (opcao != 0);
    }

    public static void validarOpcao(int opcao) {
        switch (opcao) {
            case 1 ->
                calcularTotal();
            case 2 ->
                calcularTroco();
            case 3 ->
                mostrarVendas();
            case 4 ->
                mostrarVendedor(loja);
            case 5 ->
                menuCliente(loja);
            case 6 ->
                menuLoja(loja);
            case 0 ->
                System.out.println("Encerrando...");
            default ->
                System.out.println("Opção inválida!");
        }

    }

    public static void calcularTotal() {

        System.out.println("Quantidade de planta:");
        int quantidade = scanner.nextInt();

        System.out.println("Preço da planta:");
        double preco = scanner.nextDouble();

        double subtotal = quantidade * preco;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = subtotal * 0.05;
        }

        double total = subtotal - desconto;

        LocalDate dataAtual = LocalDate.now();

        Venda venda = new Venda(quantidade, preco, desconto, total, dataAtual);
        vendas.add(venda);

        System.out.println("Total: R$ " + total);
        System.out.println("Desconto: R$ " + desconto);

    }

    public static void calcularTroco() {
        System.out.print("Valor recebido: ");
        double recebido = scanner.nextDouble();

        System.out.print("Valor da compra: ");
        double total = scanner.nextDouble();

        double troco = recebido - total;

        System.out.println("Troco: R$ " + troco);
    }

    public static void mostrarVendas() {
        int opcao;

        do {
            System.out.println("\n=== HISTÓRICO DE VENDAS ===");
            System.out.println("[1] Ver registro de Vendas");
            System.out.println("[2] Ver vendas por data");
            System.out.println("[0] Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            validarVendas(opcao);

        } while (opcao != 0);
    }

    public static void validarVendas(int opcao) {
        switch (opcao) {
            case 1 ->
                verVendas();
            case 2 ->
                buscarPorData();
            case 0 ->
                mostrarMenu();
            default ->
                System.out.println("Opção inválida!");
        }
    }

    public static void verVendas() {
        System.out.printf("\n====Registro de Vendas====\n");
        for (int i = 0; i < vendas.size(); i++) {
            System.out.printf("%d | ", i + 1);
            vendas.get(i).mostrarVendas();
        }
    }

    public static void buscarPorData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Digite a data (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();

        LocalDate dataBusca = LocalDate.parse(dataStr, formatter);

        int totalVendas = 0;

        for (Venda v : vendas) {
            if (v.getData().equals(dataBusca)) {
                totalVendas++;
            }
        }

        System.out.println("Total de vendas nesta data: " + totalVendas);
    }

    public static void mostrarVendedor(Loja loja) {
        int op;
        do {
            System.out.println("\n=== MENU VENDEDOR ===");
            System.out.println("[1] Adicionar vendedor");
            System.out.println("[2] Listar vendedores");
            System.out.println("[3] Calcular média salarial");
            System.out.println("[4] Calcular bônus");
            System.out.println("[0] Voltar");

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();

                    Vendedor v = new Vendedor(nome, idade, loja, "Cidade", "Bairro", "Rua", 2000);
                    loja.adicionarVendedor(v);
                }
                case 2 -> {
                    for (Vendedor v : loja.getVendedores()) {
                        v.apresentarSe();
                    }
                }
                case 3 -> {
                    for (Vendedor v : loja.getVendedores()) {
                        System.out.println("Média de " + v.getNome() + ": " + v.calcularMedia());
                    }
                }
                case 4 -> {
                    for (Vendedor v : loja.getVendedores()) {
                        System.out.println("Bônus de " + v.getNome() + ": " + v.calcularBonus());
                    }
                }
            }
        } while (op != 0);
    }

    public static void menuCliente(Loja loja) {
        int op;
        do {
            System.out.println("\n=== MENU CLIENTE ===");
            System.out.println("[1] Adicionar cliente");
            System.out.println("[2] Listar clientes");
            System.out.println("[0] Voltar");

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();

                    Cliente c = new Cliente(nome, idade, "Cidade", "Bairro", "Rua");
                    loja.adicionarCliente(c);
                }
                case 2 -> {
                    for (Cliente c : loja.getClientes()) {
                        c.apresentarSe();
                    }
                }
            }
        } while (op != 0);
    }

    public static void menuLoja(Loja loja) {
        int op;
        do {
            System.out.println("\n=== MENU LOJA ===");
            System.out.println("[1] Apresentar loja");
            System.out.println("[2] Contar clientes");
            System.out.println("[3] Contar vendedores");
            System.out.println("[0] Voltar");

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 ->
                    loja.apresentarSe();
                case 2 ->
                    loja.contarClientes();
                case 3 ->
                    loja.contarVendedores();
            }
        } while (op != 0);
    }
}
