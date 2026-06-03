package fag;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Date; 
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter; 

public class MenuLoja {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculadoraService service = new CalculadoraService();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        double ultimoTotalCalculado = 0; 
        
        List<Loja> redeDeLojas = new ArrayList<>();
        
        Endereco endMatriz = new Endereco("PR", "Cascavel", "Centro", "Av. Brasil", "1000", "Térreo");
        Loja matriz = new Loja("My Plant - Matriz", "Gabrielinha Plantas LTDA", "12.345.678/0001-99", endMatriz);
        redeDeLojas.add(matriz);

        int opcao = 0;
        do {
            System.out.println("\n=== Sistema - Rede My Plant ===");
            System.out.println("[1] Calcular Preço Total [Registrar Venda]");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Histórico Geral de Vendas");
            System.out.println("[4] Buscar Venda por Data");
            System.out.println("--- Gestão da Rede ---");
            System.out.println("[5] Cadastrar Vendedor");
            System.out.println("[6] Cadastrar Cliente");
            System.out.println("[7] Cadastrar Nova Loja (Filial)");
            System.out.println("[8] Painel da Empresa (Ver todas as Lojas)");
            System.out.println("[9] Consultar vendedores");
            System.out.println("--- Novos Módulos ---");
            System.out.println("[10] Gerar Novo Pedido (Simulação)");
            System.out.println("[0] Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
            case 1:
                System.out.print("Quantidade de plantas: ");
                int q = scanner.nextInt();
                System.out.print("Preço unitário: ");
                double p = scanner.nextDouble();
                Planta plantaVendida = new Planta(q, p);
                
                System.out.print("Data da venda (dd/MM/yyyy): ");
                String dataStr = scanner.next();
                
                LocalDate dataVenda = LocalDate.parse(dataStr, formatter);
                
                ultimoTotalCalculado = service.calcularPrecoTotal(plantaVendida, dataVenda);
                System.out.println("Total a pagar: R$ " + ultimoTotalCalculado);
                break;

            case 2:
                System.out.print("Valor Recebido: ");
                double pago = scanner.nextDouble();
                double resultado = service.processarTroco(pago, ultimoTotalCalculado);
                if (resultado > 0) {
                    System.out.printf("O troco a ser dado é: R$ %.2f%n", resultado);
                } else if(resultado == 0){
                    System.out.println("O troco está correto, não precisa devolver valor nenhum");
                } else {
                    System.out.println("O valor pago é menor que o valor da compra");
                }
                break;

            case 3:
                service.mostrarHistorico();
                break;
                
            case 4:
                System.out.print("Qual data deseja buscar? (dd/MM/yyyy): ");
                String dataBuscaStr = scanner.next();
                
                
                LocalDate dataBusca = LocalDate.parse(dataBuscaStr, formatter);
                service.buscarPorData(dataBusca);
                break;
                
            case 5:
                System.out.println("\n-- Cadastro de Vendedor --");
                System.out.println("Em qual loja este vendedor vai trabalhar?");
                for (int i = 0; i < redeDeLojas.size(); i++) {
                    System.out.println("[" + i + "] " + redeDeLojas.get(i).getNomeFantasia());
                }
                System.out.print("Digite o número da loja: ");
                int indexLojaVendedor = scanner.nextInt();
                scanner.nextLine(); 
                
                Loja lojaDoVendedor = redeDeLojas.get(indexLojaVendedor);

                System.out.print("Nome: ");
                String nomeV = scanner.nextLine();
                System.out.print("Idade: ");
                int idadeV = scanner.nextInt();
                System.out.print("Salário Base: ");
                double salBase = scanner.nextDouble();
                
                Endereco endVend = new Endereco("PR", "Cascavel", "Centro", "Rua X", "S/N", "");
                Vendedor novoVendedor = new Vendedor(nomeV, idadeV, endVend, lojaDoVendedor, salBase);
                lojaDoVendedor.adicionarVendedor(novoVendedor); 
                
                System.out.println("Vendedor cadastrado com sucesso!");
                novoVendedor.apresentarse();
                break;

            case 6:
                System.out.println("\n-- Cadastro de Cliente --");
                System.out.println("Em qual loja deseja vincular este cliente?");
                for (int i = 0; i < redeDeLojas.size(); i++) {
                    System.out.println("[" + i + "] " + redeDeLojas.get(i).getNomeFantasia());
                }
                System.out.print("Digite o número da loja: ");
                int indexLojaCliente = scanner.nextInt();
                scanner.nextLine(); 
                
                Loja lojaDoCliente = redeDeLojas.get(indexLojaCliente);

                System.out.print("Nome: ");
                String nomeC = scanner.nextLine();
                System.out.print("Idade: ");
                int idadeC = scanner.nextInt();
                
                Endereco endCli = new Endereco("PR", "Cascavel", "Bairro Y", "Rua Z", "123", "Casa");
                Cliente novoCliente = new Cliente(nomeC, idadeC, endCli, lojaDoCliente);
                lojaDoCliente.adicionarCliente(novoCliente);
                
                System.out.println("Cliente cadastrado com sucesso!");
                novoCliente.apresentarse();
                break;
                
            case 7:
                System.out.println("\n-- Cadastro de Nova Loja --");
                scanner.nextLine(); 
                System.out.print("Nome Fantasia: ");
                String nomeF = scanner.nextLine();
                System.out.print("Razão Social: ");
                String razaoS = scanner.nextLine();
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine();
                
                Endereco endNovaLoja = new Endereco("PR", "Cascavel", "Bairro", "Rua", "S/N", "");
                Loja novaLoja = new Loja(nomeF, razaoS, cnpj, endNovaLoja);
                redeDeLojas.add(novaLoja);
                System.out.println("Nova filial cadastrada com sucesso!");
                break;

            case 8:
                System.out.println("\n=== PAINEL DA REDE MY PLANT ===");
                for (Loja l : redeDeLojas) {
                    l.apresentarse();
                    l.contarVendedores();
                    l.contarClientes();
                    System.out.println("---------------------------------");
                }
                break;
                
            case 9: 
                System.out.println("\n-- Consulta de Vendedor --");
                System.out.println("Lista de Vendedores Cadastrados:");
                
                for (Loja l : redeDeLojas) {
                    for (Vendedor v : l.getVendedores()) {
                        System.out.println("- " + v.getNome() + " (Loja: " + l.getNomeFantasia() + ")");
                    }
                }
                
                System.out.print("\nDigite o nome do vendedor para ver os detalhes: ");
                scanner.nextLine(); 
                String nomeBusca = scanner.nextLine();
                
                boolean achou = false;
                for (Loja l : redeDeLojas) {
                    for (Vendedor v : l.getVendedores()) {
                        if (v.getNome().equalsIgnoreCase(nomeBusca)) {
                            v.exibirDetalhes(); 
                            achou = true;
                        }
                    }
                }
                if (!achou) {
                    System.out.println("Vendedor não encontrado!");
                }
                break;

            case 10: 
                System.out.println("\n-- Gerando Novo Pedido (Simulação) --");
                
                Endereco endFake = new Endereco("PR", "Cascavel", "Centro", "Av. Brasil", "100", "Sala 2");
                Loja lojaFake = redeDeLojas.get(0); 
                Cliente cliFake = new Cliente("Dona Benta", 65, endFake, lojaFake);
                Vendedor vendFake = new Vendedor("Carlos", 28, endFake, lojaFake, 2000.0);
                
                Item item1 = new Item(101, "Muda de Costela-de-Adão", "Planta Interna", 85.00);
                Item item2 = new Item(102, "Vaso de Cerâmica", "Acessório", 40.50);
                List<Item> itensCarrinho = new ArrayList<>();
                itensCarrinho.add(item1);
                itensCarrinho.add(item2);
                
                
                Date hoje = new Date();
                Date amanha = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)); 
                
                ProcessaPedido processador = new ProcessaPedido();
                
                System.out.println("Validando regra de negócios (Teste):");
                processador.testarConfirmacaoPagamento();
                
                Pedido pedidoGerado = processador.processar(5001, hoje, amanha, cliFake, vendFake, lojaFake, itensCarrinho);
                
                System.out.println("\nItens do Pedido:");
                for(Item i : itensCarrinho) { 
                    i.gerarDescricao(); 
                }
                pedidoGerado.gerarDescricaoVenda();
                break;

            case 0:
                System.out.println("Saindo...");
                break;  
                
            default:
                System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        scanner.close();
    }
}