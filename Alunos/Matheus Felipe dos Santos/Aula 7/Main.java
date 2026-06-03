import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Venda> vendas = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList<Item> produtos = new ArrayList<>();

        Loja loja = new Loja("Loja Gabrielinha", "123456", "Cascavel", "Centro", "Rua A");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int opcao = 0;

        while (opcao != 12) {

            System.out.println("\n===== MENU =====");
            System.out.println("[1] - Realizar Venda");
            System.out.println("[2] - Ver Todas Vendas");
            System.out.println("[3] - Buscar Vendas por DIA");
            System.out.println("[4] - Buscar Vendas por MÊS");
            System.out.println("[5] - Calcular Troco");
            System.out.println("[6] - Cadastrar Cliente");
            System.out.println("[7] - Cadastrar Vendedor");
            System.out.println("[8] - Criar Pedido");
            System.out.println("[9] - Cadastrar Produto");
            System.out.println("[10] - Listar Produtos");
            System.out.println("[11] - Controle de Pedidos"); 
            System.out.println("[12] - Sair");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    if (loja.clientes.isEmpty() || loja.vendedores.isEmpty()) {
                        System.out.println("Cadastre pelo menos 1 cliente e 1 vendedor!");
                        break;
                    }

                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();

                    scanner.nextLine();

                    System.out.print("Data (dd/MM/yyyy): ");
                    String dataStr = scanner.nextLine();

                    LocalDate data = LocalDate.parse(dataStr, formatter);

                    ProcessaPedido processador = new ProcessaPedido();
                    Venda venda = processador.processar(quantidade, preco);

                    venda.data = data;

                    vendas.add(venda);

                    System.out.println("Total: R$ " + venda.total);

                    break;

                case 2:
                    System.out.println("\n=== TODAS AS VENDAS ===");

                    for (Venda v : vendas) {
                        v.apresentar(formatter);
                    }
                    break;

                case 3:
                    scanner.nextLine();

                    System.out.print("Digite a data (dd/MM/yyyy): ");
                    String buscaDia = scanner.nextLine();

                    LocalDate dataBusca = LocalDate.parse(buscaDia, formatter);

                    int totalDia = 0;

                    for (Venda v : vendas) {
                        if (v.data.equals(dataBusca)) {
                            totalDia += v.quantidade;
                        }
                    }

                    System.out.println("Total no dia: " + totalDia);
                    break;

                case 4:
                    System.out.print("Mês (1-12): ");
                    int mes = scanner.nextInt();

                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();

                    int totalMes = 0;

                    for (Venda v : vendas) {
                        if (v.data.getMonthValue() == mes && v.data.getYear() == ano) {
                            totalMes += v.quantidade;
                        }
                    }

                    System.out.println("Total no mês: " + totalMes);
                    break;

                case 5:
                    System.out.print("Valor pago: ");
                    double pago = scanner.nextDouble();

                    System.out.print("Valor da compra: ");
                    double compra = scanner.nextDouble();

                    double troco = pago - compra;

                    if (troco < 0) {
                        System.out.println("Falta: R$ " + (-troco));
                    } else {
                        System.out.println("Troco: R$ " + troco);
                    }
                    break;

                case 6:
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nomeCliente = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idadeCliente = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Estado: ");
                    String estado = scanner.nextLine();

                    System.out.print("Cidade: ");
                    String cidade = scanner.nextLine();

                    System.out.print("Bairro: ");
                    String bairro = scanner.nextLine();

                    System.out.print("Número: ");
                    int numero = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Complemento: ");
                    String complemento = scanner.nextLine();

                    Endereco endCliente = new Endereco(estado, cidade, bairro, numero, complemento);

                    Cliente cliente = new Cliente(nomeCliente, idadeCliente, endCliente);
                    loja.clientes.add(cliente);

                    System.out.println("Cliente cadastrado!");
                    break;

                case 7:
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nomeVend = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idadeVend = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Estado: ");
                    String estadoVend = scanner.nextLine();

                    System.out.print("Cidade: ");
                    String cidadeVend = scanner.nextLine();

                    System.out.print("Bairro: ");
                    String bairroVend = scanner.nextLine();

                    System.out.print("Número: ");
                    int numeroVend = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Complemento: ");
                    String complementoVend = scanner.nextLine();

                    System.out.print("Salário base: ");
                    double salario = scanner.nextDouble();

                    Endereco endVendedor = new Endereco(
                            estadoVend, cidadeVend, bairroVend, numeroVend, complementoVend
                    );

                    Vendedor vendedor = new Vendedor(
                            nomeVend, idadeVend, endVendedor, salario, loja.nomeFantasia
                    );

                    loja.vendedores.add(vendedor);

                    System.out.println("Vendedor cadastrado!");
                    break;

                case 8:

                    if (loja.clientes.isEmpty() || loja.vendedores.isEmpty() || produtos.isEmpty()) {
                        System.out.println("Cadastre cliente, vendedor e produtos primeiro!");
                        break;
                    }

                    Pedido pedido = new Pedido(
                            pedidos.size() + 1,
                            loja.clientes.get(0),
                            loja.vendedores.get(0),
                            loja
                    );

                    int continuar = 1;

                    while (continuar == 1) {

                        System.out.println("\nEscolha um produto:");

                        for (int i = 0; i < produtos.size(); i++) {
                            System.out.println(i + " - " + produtos.get(i).nome);
                        }

                        int escolha = scanner.nextInt();

                        if (escolha >= 0 && escolha < produtos.size()) {

                            Item base = produtos.get(escolha);

                            System.out.print("Quantidade: ");
                            int qtd = scanner.nextInt();

                            Item itemPedido = new Item(
                                    base.id,
                                    base.nome,
                                    base.tipo,
                                    base.valor
                            );

                            itemPedido.quantidade = qtd;

                            pedido.itens.add(itemPedido);

                            System.out.println("Produto adicionado!");
                        }

                        System.out.println("Adicionar mais? (1 = sim / 0 = não)");
                        continuar = scanner.nextInt();
                    }

                    pedidos.add(pedido);

                    System.out.println("Pedido criado!");
                    pedido.gerarDescricaoVenda();

                    break;

                case 9:

                    scanner.nextLine();

                    System.out.print("ID: ");
                    int idItem = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nomeItem = scanner.nextLine();

                    System.out.print("Tipo: ");
                    String tipoItem = scanner.nextLine();

                    System.out.print("Valor: ");
                    double valorItem = scanner.nextDouble();

                    produtos.add(new Item(idItem, nomeItem, tipoItem, valorItem));

                    System.out.println("Produto cadastrado!");

                    break;

                case 10:

                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado!");
                        break;
                    }

                    System.out.println("\n=== PRODUTOS ===");

                    for (int i = 0; i < produtos.size(); i++) {
                        System.out.println("[" + i + "]");
                        produtos.get(i).gerarDescricao();
                        System.out.println("-------------------");
                    }

                    break;

                case 11:

                    if (pedidos.isEmpty()) {
                        System.out.println("Nenhum pedido!");
                        break;
                    }

                    System.out.println("\n=== CONTROLE DE PEDIDOS ===");

                    for (int i = 0; i < pedidos.size(); i++) {
                        pedidos.get(i).mostrarResumo(i + 1);
                    }

                    System.out.println("\n1 - Editar | 0 - Voltar");
                    int op = scanner.nextInt();

                    if (op == 1) {

                        System.out.print("Qual pedido: ");
                        int idx = scanner.nextInt() - 1;

                        if (idx >= 0 && idx < pedidos.size()) {

                            Pedido p = pedidos.get(idx);

                            System.out.println("1 - PROCESSANDO");
                            System.out.println("2 - PAGO");
                            System.out.println("3 - PENDENTE");

                            int s = scanner.nextInt();

                            if (s == 1) {
                                p.status = "PROCESSANDO";
                            }
                            else if (s == 2) {

                                if (p.status.equals("PAGO")) {
                                    System.out.println("Já está pago!");
                                    break;
                                }

                                p.status = "PAGO";

                                Venda novaVenda = p.gerarVenda();
                                vendas.add(novaVenda);

                                System.out.println("Pedido virou venda!");
                            }
                            else if (s == 3) {
                                p.status = "PENDENTE";
                            }
                        }
                    }

                    break;

                case 12:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}