import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int ultimaQuantidade = 0;
        double ultimoTotal = 0;
        double ultimoDesconto = 0;

        LocalDate[] datas = new LocalDate[100];
        int[] vendas = new int[100];
        int contador = 0;

        int opcao = 0;

        Endereco endLoja = new Endereco();
        endLoja.cidade = "Cascavel";
        endLoja.bairro = "Centro";
        endLoja.estado = "PR";
        endLoja.numero = 100;
        endLoja.complemento = "Loja";

        Loja loja = new Loja();
        loja.nomeFantasia = "My Plant";
        loja.razaoSocial = "My Plant LTDA";
        loja.cnpj = "123456789";
        loja.endereco = endLoja;

        Endereco endVendedor = new Endereco();
        endVendedor.cidade = "Cascavel";
        endVendedor.bairro = "Centro";
        endVendedor.estado = "PR";
        endVendedor.numero = 200;
        endVendedor.complemento = "Casa";

        Vendedor v1 = new Vendedor();
        v1.nome = "Joao";
        v1.idade = 25;
        v1.loja = loja.nomeFantasia;
        v1.endereco = endVendedor;
        v1.salarioBase = 2000;
        v1.salarioRecebido = new double[]{2000, 2100, 2200};

        Gerente g1 = new Gerente();
        g1.nome = "Carlos";
        g1.idade = 40;
        g1.loja = loja.nomeFantasia;
        g1.endereco = endVendedor;
        g1.salarioBase = 3000;
        g1.salarioRecebido = new double[]{3000, 3200, 3500};

        Endereco endCliente = new Endereco();
        endCliente.cidade = "Cascavel";
        endCliente.bairro = "Centro";
        endCliente.estado = "PR";
        endCliente.numero = 300;
        endCliente.complemento = "Ap";

        Cliente c1 = new Cliente();
        c1.nome = "Maria";
        c1.idade = 30;
        c1.endereco = endCliente;

        loja.vendedores = new Vendedor[]{v1};
        loja.clientes = new Cliente[]{c1};

        while (opcao != 3) {

            System.out.println("\n | MENU - MY PLANT |");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            System.out.println("[4] - Registrar Venda");
            System.out.println("[5] - Consultar Vendas");
            System.out.println("[6] - Mostrar Dados da Loja");
            System.out.println("[7] - Criar Pedido");

            opcao = sc.nextInt();

            if (opcao == 1) {

                System.out.println("Quantidade da planta:");
                int quantidade = sc.nextInt();

                System.out.println("Preço da planta:");
                double preco = sc.nextDouble();
            
                double totalSemDesconto = quantidade * preco;

                double desconto = 0;
                double totalComDesconto = totalSemDesconto;

                if (quantidade > 10) {
                    desconto = totalSemDesconto * 0.05;
                    totalComDesconto = totalSemDesconto - desconto;
                }

                ultimaQuantidade = quantidade;
                ultimoTotal = totalComDesconto;
                ultimoDesconto = desconto;

                System.out.println("Quantidade Vendida: " + ultimaQuantidade);
                System.out.println("Valor total da Compra: " + totalSemDesconto);
                System.out.println("Desconto Aplicado: " + ultimoDesconto);
                System.out.println("Total com Desconto: " + ultimoTotal);
            }

            if (opcao == 2) {

                if (ultimoTotal == 0) {
                    System.out.println("Nenhuma venda registrada ainda.");
                } else {

                    System.out.println("Valor recebido:");
                    double recebido = sc.nextDouble();

                    double compra = ultimoTotal;
                    System.out.println("Valor da compra: " + compra);

                    double troco = recebido - compra;

                    System.out.println("Troco: " + troco);
                    System.out.println("\nÚltima venda:");
                    System.out.println("Quantidade: " + ultimaQuantidade);
                    System.out.println("Total: " + ultimoTotal);
                    System.out.println("Desconto: " + ultimoDesconto);
                }
            }

            if (opcao == 4) {

                System.out.println("Digite a data (dd/MM/yyyy):");
                String dataTexto = sc.next();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(dataTexto, formatter);

                datas[contador] = data;
                vendas[contador] = 1;
                contador++;

                System.out.println("Venda registrada.");
            }

            if (opcao == 5) {

                System.out.println("Digite a data (dd/MM/yyyy):");
                String dataTexto = sc.next();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataBusca = LocalDate.parse(dataTexto, formatter);

                int totalVendas = 0;

                for (int i = 0; i < contador; i++) {
                    if (datas[i].equals(dataBusca)) {
                        totalVendas = totalVendas + vendas[i];
                    }
                }

                System.out.println("Total de vendas: " + totalVendas);
            }

            if (opcao == 6) {
                loja.apresentarse();
                v1.apresentarse();
                c1.apresentarse();
                System.out.println("Media salarios: " + v1.calcularMedia());
                System.out.println("Bonus vendedor: " + v1.calcularBonus());
                System.out.println("Bonus gerente: " + g1.calcularBonus());
                System.out.println("Total clientes: " + loja.contarClientes());
                System.out.println("Total vendedores: " + loja.contarVendedores());
            }

            if (opcao == 7) {

                Item item1 = new Item();
                item1.id = 1;
                item1.nome = "Planta A";
                item1.tipo = "Flor";
                item1.valor = 50;

                Item item2 = new Item();
                item2.id = 2;
                item2.nome = "Planta B";
                item2.tipo = "Decorativa";
                item2.valor = 70;

                Item[] itens = new Item[]{item1, item2};

                ProcessaPedido proc = new ProcessaPedido();
                Pedido pedido = proc.processar(c1, v1, loja, itens);

                pedido.gerarDescricaoVenda();
            }

            if (opcao == 3) {
                System.out.println("Obrigado pela visita, volte sempre!");
            }
        }

        sc.close();
    }
}