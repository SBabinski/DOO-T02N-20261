import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Vendedor> vendedores = new ArrayList<>();
    private static ArrayList<Gerente> gerentes = new ArrayList<>();
    private static ArrayList<Item> itensDisponiveis = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static int proximoIdPedido = 1;
    
    public static void main(String[] args) {
        inicializarDados();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            exibirMenu();
            System.out.print("\n➤ Escolha uma opção: ");
            
            int opcao = -1;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 
            } catch (Exception e) {
                scanner.nextLine(); 
                System.out.println("\n✗ Opção inválida! Digite um número.");
                continue;
            }
            
            System.out.println();
            
            switch (opcao) {
                case 1:
                    listarClientes();
                    break;
                case 2:
                    listarVendedores();
                    break;
                case 3:
                    listarGerentes();
                    break;
                case 4:
                    listarItens();
                    break;
                case 5:
                    criarPedido(scanner);
                    break;
                case 6:
                    listarPedidos();
                    break;
                case 7:
                    testarConfirmacaoPagamento();
                    break;
                case 0:
                    System.out.println("Encerrando sistema de Dona Gabrielinha. Até logo!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
            
            System.out.println("\n" + "=".repeat(60));
            System.out.print("Pressione ENTER para continuar...");
            scanner.nextLine();
        }
    }
    
    private static void exibirMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SISTEMA DE DONA GABRIELINHA - MENU PRINCIPAL");
        System.out.println("=".repeat(60));
        System.out.println("1. Listar Clientes");
        System.out.println("2. Listar Vendedores");
        System.out.println("3. Listar Gerentes");
        System.out.println("4. Listar Itens Disponíveis");
        System.out.println("5. Criar Novo Pedido");
        System.out.println("6. Listar Pedidos");
        System.out.println("7. Testar Confirmação de Pagamento");
        System.out.println("0. Sair");
        System.out.println("=".repeat(60));
    }
    
    private static void inicializarDados() {
        
        Endereco end1 = new Endereco("PR", "Cascavel", "Centro", 123, "Apto 101");
        Endereco end2 = new Endereco("SP", "São Paulo", "Jardins", 456, "Casa");
        Endereco end3 = new Endereco("RJ", "Rio de Janeiro", "Copacabana", 789, "Cobertura");
        Endereco end4 = new Endereco("MG", "Belo Horizonte", "Savassi", 321, "Sala 5");
        Endereco end5 = new Endereco("PR", "Curitiba", "Batel", 654, "Loja térrea");
        
        clientes.add(new Cliente("Maria Silva", 35, end1));
        clientes.add(new Cliente("João Santos", 42, end2));
        clientes.add(new Cliente("Ana Costa", 28, end3));
        
        vendedores.add(new Vendedor("Carlos Pereira", 30, "My Plant Cascavel", end4, 2500.0));
        vendedores.add(new Vendedor("Juliana Oliveira", 27, "My Plant São Paulo", end2, 2800.0));
        
        gerentes.add(new Gerente("Roberto Almeida", 45, "My Plant Cascavel", end5, 5000.0));
        gerentes.add(new Gerente("Patricia Souza", 38, "My Plant Curitiba", end5, 5500.0));
        
        itensDisponiveis.add(new Item(1, "Orquídea Phalaenopsis", "Planta Ornamental", 89.90));
        itensDisponiveis.add(new Item(2, "Suculenta Echeveria", "Planta Suculenta", 25.50));
        itensDisponiveis.add(new Item(3, "Samambaia", "Planta Pendente", 45.00));
        itensDisponiveis.add(new Item(4, "Rosa do Deserto", "Planta Ornamental", 120.00));
        itensDisponiveis.add(new Item(5, "Fertilizante Orgânico 1kg", "Insumo", 35.00));
        itensDisponiveis.add(new Item(6, "Vaso Cerâmica Médio", "Acessório", 55.00));
        itensDisponiveis.add(new Item(7, "Substrato Premium 5L", "Insumo", 28.90));
        itensDisponiveis.add(new Item(8, "Cacto San Pedro", "Planta Suculenta", 65.00));
    }
    
    private static void listarClientes() {
        System.out.println("LISTA DE CLIENTES");
        System.out.println("-".repeat(60));
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("\n[" + (i + 1) + "] ");
            clientes.get(i).apresentarSe();
        }
    }
    
    private static void listarVendedores() {
        System.out.println("LISTA DE VENDEDORES");
        System.out.println("-".repeat(60));
        for (int i = 0; i < vendedores.size(); i++) {
            Vendedor v = vendedores.get(i);
            System.out.println("\n[" + (i + 1) + "] ");
            v.apresentarSe();
            System.out.println("Loja: " + v.Loja);
            System.out.println("Salário Base: R$ " + String.format("%.2f", v.salarioBase));
        }
    }
    
    private static void listarGerentes() {
        System.out.println("LISTA DE GERENTES");
        System.out.println("-".repeat(60));
        for (int i = 0; i < gerentes.size(); i++) {
            Gerente g = gerentes.get(i);
            System.out.println("\n[" + (i + 1) + "] ");
            g.apresentarSe();
            System.out.println("Salário Base: R$ " + String.format("%.2f", g.salarioBase));
            System.out.println("Média Salarial: R$ " + String.format("%.2f", g.calcularMedia()));
            System.out.println("Bônus: R$ " + String.format("%.2f", g.calcularBonus()));
        }
    }
    
    private static void listarItens() {
        System.out.println("ITENS DISPONÍVEIS");
        System.out.println("-".repeat(60));
        for (int i = 0; i < itensDisponiveis.size(); i++) {
            System.out.println("\n[" + (i + 1) + "] ");
            itensDisponiveis.get(i).gerarDescricao();   
        }
    }
    
    private static void criarPedido(Scanner scanner) {
        System.out.println("CRIAR NOVO PEDIDO");
        System.out.println("-".repeat(60));
        
        System.out.println("\nClientes disponíveis:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).nome);
        }
        System.out.print("Escolha o cliente (1-" + clientes.size() + "): ");
        int idxCliente = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (idxCliente < 0 || idxCliente >= clientes.size()) {
            System.out.println("Cliente inválido!");
            return;
        }
        
        System.out.println("\nVendedores disponíveis:");
        for (int i = 0; i < vendedores.size(); i++) {
            System.out.println((i + 1) + ". " + vendedores.get(i).nome + " - " + vendedores.get(i).Loja);
        }
        System.out.print("Escolha o vendedor (1-" + vendedores.size() + "): ");
        int idxVendedor = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (idxVendedor < 0 || idxVendedor >= vendedores.size()) {
            System.out.println("Vendedor inválido!");
            return;
        }
        
        Cliente cliente = clientes.get(idxCliente);
        Vendedor vendedor = vendedores.get(idxVendedor);
        
        ProcessaPedido processador = new ProcessaPedido();
        Pedido pedido = processador.processar(proximoIdPedido++, cliente, vendedor);
        
        System.out.println("\nAdicionar itens ao pedido:");
        while (true) {
            System.out.println("\nItens disponíveis:");
            for (int i = 0; i < itensDisponiveis.size(); i++) {
                Item item = itensDisponiveis.get(i);
                System.out.println((i + 1) + ". " + item.nome + " - R$ " + String.format("%.2f", item.valor));
            }
            System.out.print("Escolha um item (1-" + itensDisponiveis.size() + ") ou 0 para finalizar: ");
            int idxItem = scanner.nextInt() - 1;
            scanner.nextLine();
            
            if (idxItem == -1) break;
            
            if (idxItem < 0 || idxItem >= itensDisponiveis.size()) {
                System.out.println("Item inválido!");
                continue;
            }
            
            pedido.itens.add(itensDisponiveis.get(idxItem));
            System.out.println("✓ Item adicionado: " + itensDisponiveis.get(idxItem).nome);
        }
        
        pedidos.add(pedido);
        
        System.out.println("\nPedido criado com sucesso!");
        System.out.println();
        pedido.gerarDescricaoVenda();
    }
    
    private static void listarPedidos() {
        System.out.println("LISTA DE PEDIDOS");
        System.out.println("-".repeat(60));
        
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }
        
        for (Pedido p : pedidos) {
            p.gerarDescricaoVenda();
            System.out.println();
        }
    }
    
    private static void testarConfirmacaoPagamento() {
        System.out.println("TESTE DE CONFIRMAÇÃO DE PAGAMENTO");
        System.out.println("-".repeat(60));
        
        Endereco endTeste = new Endereco("PR", "Cascavel", "Centro", 100, "Test");
        Cliente clienteTeste = new Cliente("Cliente Teste", 30, endTeste);
        Vendedor vendedorTeste = vendedores.get(0);
        
        ProcessaPedido processador = new ProcessaPedido();
        
        System.out.println("\nProcessando pedido de teste...");
        Pedido pedido = processador.processar(9999, clienteTeste, vendedorTeste);
        pedido.itens.add(itensDisponiveis.get(0));
        pedido.itens.add(itensDisponiveis.get(1));
        
        System.out.println();
        pedido.gerarDescricaoVenda();
        System.out.println("\nTeste concluído!");
    }
}