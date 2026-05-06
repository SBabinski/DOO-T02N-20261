import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Calculadora calc = new Calculadora();
        Scanner sc = new Scanner(System.in);

        int opc;
        do {
            System.out.println("\n===MENU===");
            System.out.println("[1] Calcular preço total");
            System.out.println("[2] Calcular troco");
            System.out.println("[3] Consultar vendas");
            System.out.println("[4] Consultar vendas por data");
            System.out.println("[5] Consultar Vendedores");
            System.out.println("[6] Consultar Clientes");
            System.out.println("[7] Consultar Loja");
            System.out.println("[8] Consultar Gerentes");
            System.out.println("[9] Criar Pedido");
            System.out.println("[0] Sair");

            System.out.println("Entre com a opção desejada: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {

                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    calc.calcularPrecoTotal();
                    break;
                case 2:
                    calc.calcularTroco();
                    break;
                case 3:
                    calc.registrarVendas();
                    break;

                case 4:
                    calc.consultarVendasPorData();
                    break;

                case 5:

                    System.out.println("===Vendedores===");

                    Endereco end3 = new Endereco("PR", "Marechal", "Líder",
                            369, "Casa");

                    Endereco end4 = new Endereco("PR", "Cascavel", "Floresta",
                            487, "Casa");

                    Vendedor v1 = new Vendedor("Vicente", 25, "My Plant - Matriz",
                            3600, end3);

                    Vendedor v2 = new Vendedor("Théo", 30, "My Plant - Matriz",
                            2500, end4);

                    Vendedor v3 = new Vendedor("Cecília", 20, "My Plant - Matriz",
                            1900, end3);

                    Vendedor v4 = new Vendedor("Aurora", 22, "My Plant - Filial",
                            1800, end4);

                    Vendedor v5 = new Vendedor("Franz", 28, "My Plant - Filial",
                            2600, end3);

                    Vendedor v6 = new Vendedor("Caroline", 19, "My Plant - Filial",
                            1700, end4);

                    v1.adicionarSalario(2000);
                    v1.adicionarSalario(2100);
                    v1.adicionarSalario(2200);

                    v2.adicionarSalario(2300);
                    v2.adicionarSalario(2400);
                    v2.adicionarSalario(2500);

                    v3.adicionarSalario(1800);
                    v3.adicionarSalario(1900);
                    v3.adicionarSalario(2000);

                    v4.adicionarSalario(2100);
                    v4.adicionarSalario(2200);
                    v4.adicionarSalario(2300);

                    v5.adicionarSalario(2500);
                    v5.adicionarSalario(2600);
                    v5.adicionarSalario(2700);

                    v6.adicionarSalario(1700);
                    v6.adicionarSalario(1800);
                    v6.adicionarSalario(1900);

                    ArrayList<Vendedor> vendedores = new ArrayList<>();

                    vendedores.add(v1);
                    vendedores.add(v2);
                    vendedores.add(v3);
                    vendedores.add(v4);
                    vendedores.add(v5);
                    vendedores.add(v6);

                    for (Vendedor v : vendedores) {
                        v.apresentarse();
                        System.out.println("Média salários: " + String.format("%.2f", v.calcularMedia()));
                        System.out.println("Bônus: " + String.format("%.2f", v.calcularBonus()));
                        System.out.println();
                    }

                    break;

                case 6:
                    System.out.println("===Clientes===");

                    Endereco end1 = new Endereco("PR", "Marechal", "Líder",
                            123, "Casa");

                    Cliente c1 = new Cliente("Sandra", 48, end1);

                    Endereco end2 = new Endereco("PR", "Toledo", "Recanto",
                            456, "Casa");

                    Cliente c2 = new Cliente("Bruno", 30, end2);

                    ArrayList<Cliente> clientes = new ArrayList<>();
                    clientes.add(c1);
                    clientes.add(c2);

                    for (Cliente c : clientes) {
                        c.apresentarse();
                        System.out.println();
                    }

                    break;

                case 7:
                    System.out.println("===Loja===");

                    Endereco end5 = new Endereco("PR", "Cascavel", "Interlagos", 124,
                            "prédio comercial");

                    Loja loja = new Loja("My Plant", "My Plant LTDA", "123456",
                            end5);

                    loja.apresentarse();

                    break;

                case 8:
                    Endereco end6 = new Endereco("PR", "Marechal", "Centro",
                            349, "Casa");

                    Endereco end7 = new Endereco("PR", "Cascavel", "Centro",
                            437, "Casa");

                    Gerente g1 = new Gerente("Carlos", 45, end6, "My Plant - Matriz", 7000);
                    Gerente g2 = new Gerente("Ana", 42, end7, "My Plant - Filial", 8000);

                    g1.adicionarSalario(7500);
                    g1.adicionarSalario(7000);
                    g1.adicionarSalario(7800);

                    g2.adicionarSalario(7800);
                    g2.adicionarSalario(7500);
                    g2.adicionarSalario(8000);

                    ArrayList<Gerente> gerentes = new ArrayList<>();

                    gerentes.add(g1);
                    gerentes.add(g2);

                    for (Gerente g : gerentes) {
                        g.apresentarse();
                        System.out.println("Média salários: " +
                                String.format("%.2f", g.calcularMedia()));

                        System.out.println("Bônus: " + String.format("%.2f", g.calcularBonus()));
                        System.out.println();
                    }

                    break;

                case 9:

                    System.out.println("===Criar Pedido===");

                    Endereco end = new Endereco("PR", "Cascavel", "Centro", 100, "Casa");

                    Cliente cliente = new Cliente("João", 30, end);

                    Vendedor vendedor = new Vendedor("Maria", 28, "My Plant", 2000, end);

                    Loja lojaPedido = new Loja("My Plant", "My Plant LTDA", "123456", end);

                    Date dataCriacao = new Date();
                    Date dataVencimento = new Date(dataCriacao.getTime() + (3L * 24 * 60 * 60 * 1000));

                    ProcessaPedido processador = new ProcessaPedido();

                    Pedido pedido = processador.processar(
                            1, dataCriacao, dataVencimento, cliente, vendedor, lojaPedido);

                    Item i1 = new Item(1, "Girassol", "Planta", 50);
                    Item i2 = new Item(2, "Vaso", "Acessório", 30);

                    pedido.adicionarItem(i1);
                    pedido.adicionarItem(i2);

                    pedido.gerarDescricaoVenda();

                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opc != 0);

        sc.close();
    }
}
