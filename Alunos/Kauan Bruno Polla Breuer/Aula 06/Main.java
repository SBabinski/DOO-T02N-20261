import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Loja loja = new Loja("My Plant", "My Plant LTDA", "123456789", "Cascavel", "Centro", "Rua A");

        int opcao;

        do {
            System.out.println("\n=== My Plant ===");
            System.out.println("[1] Realizar Venda");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Cadastrar Cliente");
            System.out.println("[4] Cadastrar Vendedor");
            System.out.println("[5] Listar Clientes");
            System.out.println("[6] Listar Vendedores");
            System.out.println("[7] Mostrar Loja");
            System.out.println("[8] Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    realizarVenda();
                    break;

                case 2:
                    calcularTroco();
                    break;

                case 3:
                    cadastrarCliente(loja);
                    break;

                case 4:
                    cadastrarVendedor(loja);
                    break;

                case 5:
                    for (Cliente c : loja.clientes) {
                        c.apresentarSe();
                    }
                    break;

                case 6:
                    for (Vendedor v : loja.vendedores) {
                        v.apresentarSe();
                        System.out.println("Média salarial: " + v.calcularMedia());
                        System.out.println("Bônus: " + v.calcularBonus());
                    }
                    break;

                case 7:
                    loja.apresentarSe();
                    System.out.println("Clientes: " + loja.contarClientes());
                    System.out.println("Vendedores: " + loja.contarVendedores());
                    break;

            }

        } while (opcao != 8);
    }

    public static void realizarVenda() {
        System.out.print("Quantidade: ");
        int qtd = scanner.nextInt();

        System.out.print("Preço unitário: ");
        double preco = scanner.nextDouble();

        double total = qtd * preco;
        double desconto = 0;

        if (qtd > 10) {
            desconto = total * 0.05;
        }

        double totalFinal = total - desconto;

        if (desconto > 0) {
            System.out.println("Total sem desconto: " + total);
            System.out.println("Desconto: " + desconto);
        }

        System.out.println("Total final: " + totalFinal);
    }

    public static void calcularTroco() {
        System.out.print("Valor pago: ");
        double pago = scanner.nextDouble();

        System.out.print("Valor da compra: ");
        double compra = scanner.nextDouble();

        if (pago < compra) {
            System.out.println("Valor insuficiente");
        } else {
            System.out.println("Troco: " + (pago - compra));
        }
    }

    public static void cadastrarCliente(Loja loja) {
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();

        System.out.print("Cidade: ");
        scanner.nextLine();
        String cidade = scanner.nextLine();

        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();

        System.out.print("Rua: ");
        String rua = scanner.nextLine();

        loja.clientes.add(new Cliente(nome, idade, cidade, bairro, rua));
    }

    public static void cadastrarVendedor(Loja loja) {
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();

        System.out.print("Cidade: ");
        scanner.nextLine();
        String cidade = scanner.nextLine();

        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();

        System.out.print("Rua: ");
        String rua = scanner.nextLine();

        System.out.print("Salário base: ");
        double salario = scanner.nextDouble();

        loja.vendedores.add(new Vendedor(nome, idade, loja.nomeFantasia, cidade, bairro, rua, salario));
    }
}