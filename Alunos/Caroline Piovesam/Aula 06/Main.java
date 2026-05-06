import java.util.ArrayList;
import java.util.Scanner;

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

                    Vendedor v1 = new Vendedor("Vicente", 25, "My Plant - Matriz",
                            "Cascavel", "Centro", "Rua A", 2000);

                    Vendedor v2 = new Vendedor("Théo", 30, "My Plant - Matriz",
                            "Marechal", "Centro", "Rua B", 2500);

                    Vendedor v3 = new Vendedor("Cecília", 20, "My Plant - Matriz",
                            "Cascavel", "Centro", "Rua C", 1800);

                    Vendedor v4 = new Vendedor("Aurora", 22, "My Plant - Filial",
                            "Toledo", "Centro", "Rua D", 2100);

                    Vendedor v5 = new Vendedor("Franz", 28, "My Plant - Filial",
                            "Toledo", "Centro", "Rua E", 2600);

                    Vendedor v6 = new Vendedor("Caroline", 19, "My Plant - Filial",
                            "Marechal", "Centro", "Rua F", 1700);

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
                        System.out.println("Média salários: " + v.calcularMedia());
                        System.out.println("Bônus: " + v.calcularBonus());
                        System.out.println();
                    }

                    break;

                case 6:
                    System.out.println("===Clientes===");
                    Cliente c1 = new Cliente("Sandra", 48, "Marechal", "Centro", "Rua F");
                    Cliente c2 = new Cliente("Bruno", 30, "Toledo", "Centro", "Rua Y");

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
                    Loja loja = new Loja("My Plant", "My Plant LTDA", "123456",
                            "Cascavel", "Centro", "Rua Principal");

                    loja.apresentarse();

                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opc != 0);

        sc.close();
    }
}
