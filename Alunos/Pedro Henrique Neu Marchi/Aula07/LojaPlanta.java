import java.util.Date;
import java.util.Scanner;

public class LojaPlanta {

    public static void main(String[] args) {

        Endereco endLoja = new Endereco();
        endLoja.estado = "PR";
        endLoja.cidade = "Caixa Prego";
        endLoja.bairro = "Centro";
        endLoja.numero = "111";
        endLoja.complemento = "Loja principal";

        Loja loja = new Loja();
        loja.nomeFantasia = "My Plant";
        loja.cnpj = "12.345.678/0001-9";
        loja.endereco = endLoja;

        Endereco endVendedor = new Endereco();
        endVendedor.estado = "PR";
        endVendedor.cidade = "Caixa Prego";
        endVendedor.bairro = "Jardim das Rosas";
        endVendedor.numero = "42";
        endVendedor.complemento = "Apto 3";

        Vendedor v1 = new Vendedor();
        v1.nome = "Choso";
        v1.idade = 30;
        v1.loja = loja;
        v1.salarioBase = 2000;
        v1.endereco = endVendedor;
    
        Endereco endCliente = new Endereco();
        endCliente.estado = "PR";
        endCliente.cidade = "Caixa Prego";
        endCliente.bairro = "Vila Nova";
        endCliente.numero = "88";
        endCliente.complemento = "Casa";

        Cliente c1 = new Cliente();
        c1.nome = "Yuki";
        c1.idade = 25;
        c1.endereco = endCliente;

        loja.vendedores = new Vendedor[]{v1};
        loja.clientes = new Cliente[]{c1};


        Scanner leitor = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n=== SISTEMA MY PLANT ===");
            System.out.println("1 - Calcular venda");
            System.out.println("2 - Calcular troco");
            System.out.println("3 - Buscar vendas por mês");
            System.out.println("4 - Buscar vendas por dia");
            System.out.println("5 - Mostrar clientes");
            System.out.println("6 - Mostrar vendedores");
            System.out.println("7 - Dados da loja");
            System.out.println("8 - Criar pedido");
            System.out.println("9 - Sair");
            System.out.print("Escolha: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    Metodos.calcularPrecoComDesconto();
                    break;

                case 2:
                    Metodos.calcularTroco();
                    break;

                case 3:
                    Metodos.buscarPorMes();
                    break;

                case 4:
                    Metodos.buscarPorDia();
                    break;

                case 5:
                    loja.contarClientes();
                    break;

                case 6:
                    loja.contarVendedores();
                    break;

                case 7:
                    loja.apresentarSe();
                    break;

                case 8:
                    criarPedidoFake(loja, v1, c1);
                    break;

                case 9:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);

        leitor.close();
    }

    private static void criarPedidoFake(Loja loja, Vendedor vendedor, Cliente cliente) {
        Item item1 = new Item();
        item1.id = 1;
        item1.nome = "Samambaia";
        item1.tipo = "Planta";
        item1.valor = 45.90;

        Item item2 = new Item();
        item2.id = 2;
        item2.nome = "Vaso Cerâmica";
        item2.tipo = "Acessório";
        item2.valor = 29.90;

        System.out.println("\n--- Itens do pedido ---");
        item1.gerarDescricao();
        item2.gerarDescricao();

        Date vencimentoReserva = new Date(System.currentTimeMillis() + 2 * 3_600_000L);

        ProcessaPedido pp = new ProcessaPedido();
        pp.processar(1001, cliente, vendedor, loja,
                new Item[]{item1, item2}, vencimentoReserva);
    }
}