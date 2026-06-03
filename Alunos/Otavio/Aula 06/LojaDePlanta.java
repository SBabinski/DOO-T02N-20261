import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LojaDePlanta {

    public static void main(String[] args) {

        Loja myPlant = new Loja(
                "My Plant",
                "My Plant",
                "12.345.678/0001-99",
                "Cascavel",
                "Centro",
                "Rua das Flores, 100",
                10,
                100
        );

        Vendedor vendedor1 = new Vendedor("Gojo satoru", 30, myPlant, "Cascavel", "Centro", "Rua das Flores, 100", 2500.00);
        Vendedor vendedor2 = new Vendedor("Suguru Geto", 25, myPlant, "Cascavel", "Centro", "Rua das Flores, 100", 2200.00);
        myPlant.adicionarVendedor(vendedor1);
        myPlant.adicionarVendedor(vendedor2);

        Cliente cliente1 = new Cliente("Yuta okkotsu", 16, "Cascavel", "Neva", "Av. Brasil, 500");
        Cliente cliente2 = new Cliente("Yuji Itadori", 16, "Cascavel", "Centro", "Rua 7 de Setembro, 200");
        myPlant.adicionarCliente(cliente1);
        myPlant.adicionarCliente(cliente2);

        Scanner scanner = new Scanner(System.in);
        CalculadoraDaLoja calculadora = new CalculadoraDaLoja();
        List<Venda> registroVendas = new ArrayList<>();

        int opcao = 0;

        while (opcao != 9) {

            System.out.println("1 - Realizar Venda");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Listar Vendas");
            System.out.println("4 - Buscar Total de Vendas por Mes e Dia");
            System.out.println("5 - Buscar Total de Vendas por Mes");
            System.out.println("6 - Informacoes da Loja");
            System.out.println("7 - Informacoes dos Vendedores");
            System.out.println("8 - Informacoes dos Clientes");
            System.out.println("9 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preco unitario da planta: ");
                    double preco = scanner.nextDouble();

                    Venda venda = calculadora.realizarVenda(quantidade, preco);
                    registroVendas.add(venda);

                    System.out.println("Venda registrada na data: " + venda.getDataFormatada());
                    System.out.println("Quantidade: " + venda.getQuantidade());
                    System.out.println("Valor bruto: R$ " + venda.getValorBruto());
                    System.out.println("Desconto: R$ " + venda.getDesconto());
                    System.out.println("Valor final: R$ " + venda.getValorFinal());
                    break;

                case 2:
                    System.out.print("Valor pago pelo cliente: ");
                    double valorPago = scanner.nextDouble();

                    System.out.print("Valor total da compra: ");
                    double valorCompra = scanner.nextDouble();

                    double troco = calculadora.calcularTroco(valorPago, valorCompra);
                    System.out.println("Troco a devolver: R$ " + troco);
                    break;

                case 3:
                    if (registroVendas.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        for (Venda v : registroVendas) {
                            System.out.println(v);
                        }
                    }
                    break;

                case 4:
                    System.out.print("Informe o mes (1-12): ");
                    int mes = scanner.nextInt();

                    System.out.print("Informe o dia (1-31): ");
                    int dia = scanner.nextInt();

                    List<Venda> vendasPorMesDia = calculadora.buscarVendasPorMesEDia(registroVendas, mes, dia);
                    System.out.println("\nVendas em " + String.format("%02d/%02d", dia, mes) + ":");

                    if (vendasPorMesDia.isEmpty()) {
                        System.out.println("Nenhuma venda encontrada para essa data.");
                    } else {
                        for (Venda v : vendasPorMesDia) {
                            System.out.println(v);
                        }
                        System.out.println("Total: " + vendasPorMesDia.size() + " venda(s)");
                    }
                    break;

                case 5:
                    System.out.print("Informe o mes (1-12): ");
                    int mesBusca = scanner.nextInt();

                    List<Venda> vendasPorMes = calculadora.buscarVendasPorMes(registroVendas, mesBusca);
                    System.out.println("\nVendas no mes " + String.format("%02d", mesBusca) + ":");

                    if (vendasPorMes.isEmpty()) {
                        System.out.println("Nenhuma venda encontrada para esse mes.");
                    } else {
                        for (Venda v : vendasPorMes) {
                            System.out.println(v);
                        }
                        System.out.println("Total: " + vendasPorMes.size() + " venda(s)");
                    }
                    break;

                case 6:
                    myPlant.Apresentar();
                    System.out.println("Total de vendedores: " + myPlant.contarVendedores());
                    System.out.println("Total de clientes: " + myPlant.contarClientes());
                    break;

                case 7:
                    System.out.println("Vendedor 1:");
                    vendedor1.Apresentar();
                    System.out.printf("Media salarial: R$ %.2f%n", vendedor1.calcularMedia());
                    System.out.printf("Bonus: R$ %.2f%n", vendedor1.calcularBonus());

                    System.out.println("\nVendedor 2:");
                    vendedor2.Apresentar();
                    System.out.printf("Media salarial: R$ %.2f%n", vendedor2.calcularMedia());
                    System.out.printf("Bonus: R$ %.2f%n", vendedor2.calcularBonus());
                    break;

                case 8:
                    System.out.println("Cliente 1:");
                    cliente1.Apresentar();
                    System.out.println("\nCliente 2:");
                    cliente2.Apresentar();
                    break;

                case 9:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }

        scanner.close();
    }
}
