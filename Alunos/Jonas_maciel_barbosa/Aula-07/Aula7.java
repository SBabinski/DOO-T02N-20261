import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Aula7 {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        projetoSamuca();
        testarProcessaPedido();

        ArrayList<Venda> vendas = new ArrayList<>();

        double total = 0;
        int qtd = 0;
        double valor = 0;
        double desconto = 0;
        int opc = 0;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        while (opc != 6) {
            menu(opc);
            opc = scan.nextInt();
            scan.nextLine();
            switch (opc) {
                case 1:
                    System.out.println("Digite a quantidade de plantas");
                    qtd = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Digite o valor da planta");
                    valor = scan.nextDouble();
                    scan.nextLine();

                    total = qtd * valor;
                    desconto = 0;

                    if (qtd > 10) {
                        desconto = total * 0.05;
                        total = total - desconto;
                        System.out.println("Desconto de 5% por ter mais que 10 plantas aplicado.");
                    }

                    System.out.println("O valor total é: " + total);
                    break;

                case 2:
                    System.out.println("Digite o valor pago pelo cliente.");
                    double pago = scan.nextDouble();
                    scan.nextLine();

                    double troco = pago - total;

                    if (troco < 0) {
                        System.out.println("Saldo Insuficiente.");
                        break;
                    }
                    System.out.println("O troco é: " + troco);
                    System.out.println("Digite a data: (DD/MM/YYYY)");
                    String datavenda = scan.nextLine();
                    try {
                        Date data = formatter.parse(datavenda);
                        Venda v = new Venda(total, valor, qtd, desconto, data);
                        vendas.add(v);
                    } catch (ParseException e) {
                        System.out.println("Data inválida. Venda não registrada.");
                    }
                    break;

                case 3:
                    System.out.println("Registro de vendas:");
                    int count = 0;
                    for (Venda venda : vendas) {
                        count++;
                        System.out.println("----------------------------------");
                        System.out.println(count + " Venda");
                        System.out.println("Total da Venda: " + venda.total);
                        System.out.println("Quantidade de Planta: " + venda.qtd);
                        System.out.println("Valor de cada Planta: " + venda.valor);
                        System.out.println("Desconto aplicado: " + venda.desconto);
                        System.out.println("Data: " + formatter.format(venda.data));
                    }
                    break;

                case 4:
                    System.out.println("Digite a data: (DD/MM/YYYY)");
                    String databuscar = scan.nextLine();
                    try {
                        Date data1 = formatter.parse(databuscar);
                        for (Venda venda : vendas) {
                            if (formatter.format(venda.data).equals(formatter.format(data1))) {
                                System.out.println("----------------------------------");
                                System.out.println("Venda Encontrada:");
                                System.out.println("Total da Venda: " + venda.total);
                                System.out.println("Quantidade de Planta: " + venda.qtd);
                                System.out.println("Valor de cada Planta: " + venda.valor);
                                System.out.println("Desconto aplicado: " + venda.desconto);
                                System.out.println("Data: " + formatter.format(venda.data));
                            }
                        }
                    } catch (ParseException e) {
                        System.out.println("Data inválida.");
                    }
                    break;

                case 5:
                    Endereco endLoja = new Endereco("PR", "Curitiba", "Batel", "300", "Avenida do Iguacu");
                    Loja lojaFake = new Loja("Verde Vivo", "Verde Vivo Comercio de Plantas Ltda", "98765432000188", endLoja);

                    Endereco endVend = new Endereco("PR", "Curitiba", "Batel", "310", "Avenida do Iguacu");
                    double[] salFake = {3100.00, 3250.00, 3050.00};
                    Vendedor vendFake = new Vendedor("Ana", 27, endVend, lojaFake, 3100.00, salFake);

                    Endereco endCli = new Endereco("PR", "Curitiba", "Batel", "150", "Rua Comendador Araujo");
                    Cliente cliFake = new Cliente("Lucas", 25, endCli);

                    Item[] itensFake = {
                        new Item(1, "Samambaia", "Planta", 45.00),
                        new Item(2, "Vaso Ceramica", "Acessorio", 30.00)
                    };

                    Date vencimento = new Date(System.currentTimeMillis() + 3600000L);

                    ProcessaPedido processador = new ProcessaPedido();
                    processador.processar(1, new Date(), new Date(), vencimento, cliFake, vendFake, lojaFake, itensFake);
                    break;

                case 6:
                    System.out.println("Saindo.");
                    break;

                default:
                    System.out.println("Número errado, tente novamente.");
                    break;
            }
        }
        scan.close();
    }

    public static void menu(int opc) {
        System.out.println("----------------------------------");
        System.out.println("Digite o número do que deseja:");
        System.out.println("1 - Calcular Preço Total.");
        System.out.println("2 - Calcular Troco.");
        System.out.println("3 - Registro de Vendas.");
        System.out.println("4 - Buscar Data.");
        System.out.println("5 - Criar Pedido.");
        System.out.println("6 - Sair.");
    }

    public static void testarProcessaPedido() {
        Endereco endLoja = new Endereco("PR", "Curitiba", "Batel", "300", "Avenida do Iguacu");
        Loja loja = new Loja("Verde Vivo", "Verde Vivo Comercio de Plantas Ltda", "98765432000188", endLoja);

        Endereco endVend = new Endereco("PR", "Curitiba", "Batel", "310", "Avenida do Iguacu");
        double[] sal = {3100.00, 3250.00, 3050.00};
        Vendedor vend = new Vendedor("Ana", 27, endVend, loja, 3100.00, sal);

        Endereco endCli = new Endereco("PR", "Curitiba", "Batel", "150", "Rua Comendador Araujo");
        Cliente cli = new Cliente("Lucas", 25, endCli);

        Item[] itens = {
            new Item(1, "Samambaia", "Planta", 45.00),
            new Item(2, "Vaso Ceramica", "Acessorio", 30.00)
        };

        ProcessaPedido processador = new ProcessaPedido();

        System.out.println("--- Teste reserva valida ---");
        Date vencValido = new Date(System.currentTimeMillis() + 3600000L);
        processador.processar(1, new Date(), new Date(), vencValido, cli, vend, loja, itens);

        System.out.println("--- Teste reserva vencida ---");
        Date vencExpirado = new Date(System.currentTimeMillis() - 3600000L);
        processador.processar(2, new Date(), new Date(), vencExpirado, cli, vend, loja, itens);
    }

    public static void projetoSamuca() {
        Endereco endLoja = new Endereco("PR", "Curitiba", "Batel", "300", "Avenida do Iguacu");
        Loja loja = new Loja(
                "Verde Vivo",
                "Verde Vivo Comercio de Plantas Ltda",
                "98765432000188",
                endLoja
        );

        double[] salarioAna = {3100.00, 3250.00, 3050.00};
        double[] salarioCarlos = {2900.00, 3100.00, 2800.00};
        double[] salarioMariana = {5500.00, 5700.00, 5600.00};

        Endereco endAna = new Endereco("PR", "Curitiba", "Batel", "310", "Avenida do Iguacu");
        Endereco endCarlos = new Endereco("PR", "Curitiba", "Agua Verde", "220", "Rua Itupava");
        Endereco endMariana = new Endereco("PR", "Curitiba", "Centro", "10", "Rua XV de Novembro");

        Vendedor ana = new Vendedor("Ana", 27, endAna, loja, 3100.00, salarioAna);
        Vendedor carlos = new Vendedor("Carlos", 33, endCarlos, loja, 2900.00, salarioCarlos);
        Gerente mariana = new Gerente("Mariana", 42, endMariana, loja, 5500.00, salarioMariana);

        loja.vendedores.add(ana);
        loja.vendedores.add(carlos);
        loja.gerentes.add(mariana);

        Endereco endC1 = new Endereco("PR", "Curitiba", "Batel", "150", "Rua Comendador Araujo");
        Endereco endC2 = new Endereco("PR", "Curitiba", "Batel", "160", "Rua Comendador Araujo");
        Endereco endC3 = new Endereco("PR", "Curitiba", "Batel", "170", "Rua Comendador Araujo");

        Cliente c1 = new Cliente("Lucas", 25, endC1);
        Cliente c2 = new Cliente("Fernanda", 31, endC2);
        Cliente c3 = new Cliente("Ricardo", 40, endC3);

        loja.clientes.add(c1);
        loja.clientes.add(c2);
        loja.clientes.add(c3);

        loja.apresentarse();
        loja.contarClientes();
        loja.contarVendedores();
        loja.contarGerentes();

        ana.apresentarse();
        ana.calcularMedia();
        ana.calcularBonus();

        carlos.apresentarse();
        carlos.calcularMedia();
        carlos.calcularBonus();

        mariana.apresentarse();
        mariana.calcularMedia();
        mariana.calcularBonus();

        c1.apresentarse();
        c2.apresentarse();
        c3.apresentarse();
    }
}
