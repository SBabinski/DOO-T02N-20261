import java.util.Scanner;
import objetos.Venda;
import objetos.Vendedor;
import objetos.VinculoCli;
import objetos.VinculoGer;
import objetos.VinculoVen;
import objetos.Cliente;
import objetos.Gerente;
import objetos.Item;
import objetos.Loja;
import objetos.Pedido;
import objetos.ProcessaPedido;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Principal {
	static Scanner scan = new Scanner(System.in);
	static List<Venda> venda = new ArrayList<>();
	static List<Vendedor> vendedores = new ArrayList<>();
	static List<Gerente> gerentes = new ArrayList<>(); // Nova lista
	static List<Cliente> clientes = new ArrayList<>();
	static List<Loja> loja = new ArrayList<>();
	static List<Item> itensDoPedido = new ArrayList<>();
	static List<VinculoVen> vinculosVendedores = new ArrayList<>();
	static List<VinculoGer> vinculosGerentes = new ArrayList<>();
	static List<VinculoCli> vinculosClientes = new ArrayList<>();
	private static double precoTot=0;
    public static void main(String[] args) {
		popular();
		menuPrincipal();
		
    }
    private static void menuPrincipal() {
		int escolha=1111111;
		do {
			System.out.println("-----Menu-----");
			System.out.println("[1] - Calculadora");
			System.out.println("[2] - Painel de gestão");
			System.out.println("[0] - Sair");
			
			escolha = scan.nextInt();
			scan.nextLine();
			validarEscolhaPrincipal(escolha);
		}while(escolha != 0);
	}
	private static void validarEscolhaPrincipal(int escolha) {
		switch (escolha) {
		case 1:
			menuCalculadora();
			break;
		case 2:
			menuGestor();
			break;	
		case 0:
			System.out.println("Obrigado por utilizar o sistema.");
			break;
		default:
			System.out.println("Selecione uma opção valida");
			break;
		}
	}
	private static void menuGestor() {
		int escolha = 1111111;
		do {
			System.out.println("----- Menu Gestor -----");
			System.out.println("[1] - Lojas");
			System.out.println("[2] - Calcular média salarial (Vendedores e Gerentes)");
			System.out.println("[3] - Bônus (Vendedores e Gerentes)");
			System.out.println("[0] - Voltar");
			
			escolha = scan.nextInt();
			scan.nextLine();
			validarEscolhaGestor(escolha);
		} while(escolha != 0);
	}

	private static void validarEscolhaGestor(int escolha) {
		switch (escolha) {
			case 1:
				mostrarLojas();
				break;
			case 2:
				System.out.println("\n--- MÉDIAS SALARIAIS ---");
				for(Vendedor v : vendedores) {
					System.out.println("Vendedor: " + v.getNome() + " - Média: " + v.calcularMedia());
				}
				for(Gerente g : gerentes) {
					System.out.println("Gerente: " + g.getNome() + " - Média: " + g.calcularMedia());
				}
				break;
			case 3:
				System.out.println("\n--- BÔNUS DOS COLABORADORES ---");
				for(Vendedor v : vendedores) {
					System.out.println("Vendedor: " + v.getNome() + " - Bônus (20%): " + v.calcularBonus());
				}
				for(Gerente g : gerentes) {
					System.out.println("Gerente: " + g.getNome() + " - Bônus (35%): " + g.calcularBonus());
				}
				break;
			case 0:
				System.out.println("Voltando ao menu principal...");
				break;
			default:
				System.out.println("Selecione uma opção válida.");
				break;
		}
	}

	private static void mostrarLojas() {
		System.out.println("\n--- LISTA DE LOJAS ---");
		for (Loja l : loja) {
			System.out.println("[" + l.getIdLoja() + "] " + l.getNomeFantasia());
		}
		System.out.println("[0] Voltar");

		System.out.print("Selecione uma loja pelo ID: ");
		int idEscolhido = scan.nextInt();
		scan.nextLine();

		if (idEscolhido == 0) return;

		
		Loja selecionada = null;
		for (Loja l : loja) {
			if (l.getIdLoja() == idEscolhido) {
				selecionada = l;
				break;
			}
		}

		if (selecionada != null) {
			menuUnidadeLoja(selecionada);
		} else {
			System.out.println("ID inválido!");
		}
	}

	private static void menuUnidadeLoja(Loja selecionada) {
		int op;
		do {
			exibirResumoLoja(selecionada);
			
			System.out.println("[1] Listar Vendedores (Detalhado)");
			System.out.println("[2] Listar Gerentes (Detalhado)"); // Nova opção
			System.out.println("[3] Listar Clientes (Detalhado)");
			System.out.println("[0] Voltar");
			
			op = scan.nextInt();
			scan.nextLine();

			switch (op) {
				case 1 -> listarVendedoresDaLoja(selecionada);
				case 2 -> listarGerentesDaLoja(selecionada); // Novo método
				case 3 -> listarClientesDaLoja(selecionada);
				case 0 -> System.out.println("Voltando...");
				default -> System.out.println("Opção inválida!");
			}
		} while (op != 0);
	}

	private static void exibirResumoLoja(Loja selecionada) {
		long totalVend = vinculosVendedores.stream().filter(v -> v.getIdLo() == selecionada.getIdLoja()).count();
		long totalCli = vinculosClientes.stream().filter(v -> v.getIdLo() == selecionada.getIdLoja()).count();
		long totalGer = vinculosGerentes.stream().filter(v -> v.getIdLo() == selecionada.getIdLoja()).count();

		System.out.println("\n==============================");
		selecionada.apresentarse();
		System.out.println("Total de Gerentes: " + totalGer); 
		System.out.println("Total de Vendedores: " + totalVend);
		System.out.println("Total de Clientes: " + totalCli);
		System.out.println("==============================");
	}

	private static void listarGerentesDaLoja(Loja selecionada) {
		System.out.println("\n--- QUADRO DE GERÊNCIA ---");
		vinculosGerentes.stream()
			.filter(vg -> vg.getIdLo() == selecionada.getIdLoja())
			.forEach(vg -> {
				gerentes.stream()
					.filter(g -> g.getIdGerente() == vg.getIdGer())
					.forEach(g -> {
						g.apresentarse();
						System.out.println("--------------------");
					});
			});
	}

	
	private static void listarVendedoresDaLoja(Loja selecionada) {
		System.out.println("\n--- QUADRO DE FUNCIONÁRIOS ---");
		vinculosVendedores.stream()
			.filter(vv -> vv.getIdLo() == selecionada.getIdLoja())
			.forEach(vv -> {
				vendedores.stream()
					.filter(v -> v.getIdFuncionario() == vv.getIdVen())
					.forEach(v -> {
						v.apresentarse();
						System.out.println("--------------------");
					});
			});
	}


	private static void listarClientesDaLoja(Loja selecionada) {
		System.out.println("\n--- CARTEIRA DE CLIENTES ---");
		vinculosClientes.stream()
			.filter(vc -> vc.getIdLo() == selecionada.getIdLoja())
			.forEach(vc -> {
				clientes.stream()
					.filter(c -> c.getIdCliente() == vc.getIdCli())
					.forEach(c -> {
						c.apresentarse();
						System.out.println("--------------------");
					});
			});
	}

	private static void menuCalculadora() {
		int escolha=1111111;
		do {
			System.out.println("-----Menu-----");
			System.out.println("[1] - Calcular Preço Total");
			System.out.println("[2] - Calcular Troco");
			System.out.println("[3] - Registro de Vendas");
			System.out.println("[4] - Vendas por dia");
			System.out.println("[5] - Vendas por mês");
			System.out.println("[6] - Criar Pedido");
			System.out.println("[0] - Sair");
			
			escolha = scan.nextInt();
			scan.nextLine();
			validarEscolhaCalculadora(escolha);
		}while(escolha != 0);

	}

	private static void validarEscolhaCalculadora(int escolha) {
		switch (escolha) {
		case 1:
			calculaPreco();
			break;
		case 2:
			calculaTroco();
			break;
		case 3:
			mostraRegistroVendas();
			break;
		case 4:
			mostraVendasDia();
			break;
		case 5:
			mostraVendasMes();
			break;	
		case 6:
    		criarPedido();
    	break;

		case 0:
			System.out.println("Obrigado por utilizar o sistema.");
			break;
		default:
			System.out.println("Selecione uma opção valida");
			break;
		}

	}
    private static void criarPedido() {
		ProcessaPedido processador = new ProcessaPedido();

		Cliente c = clientes.get(0);
		Vendedor v = vendedores.get(0);
		Loja l = loja.get(0);
		
		List<Item> itensTeste = new ArrayList<>();
		itensTeste.add(itensDoPedido.get(0)); // Notebook
		
	
		System.out.println("\n--- CRIAÇÃO ---");
		Pedido p = processador.processar(LocalDate.now(), c, v, l, itensTeste);
		p.gerarDescricaoVenda();

		// 2. Teste de Pagamento
		System.out.println("\n--- TESTANDO PAGAMENTO ---");
		processador.confirmarPagamento(p);
	}
	
	private static void mostraVendasMes() {
    int contV = 0;
    System.out.println("Digite o mês que deseja pesquisar (MM/yyyy):");
    String input = scan.nextLine();

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("MM/yyyy");
    YearMonth dataPesquisada = YearMonth.parse(input, formato);

    for (int i = 0; i < venda.size(); i++) {
        
        if (venda.get(i).getMesAno().equals(dataPesquisada)) {
            contV++;
        }
    }
    System.out.printf("Quantidade de vendas encontradas: %d \n", contV);
}

	private static void mostraVendasDia() {
		int contV=0;
		System.out.println("Digite uma data que deseja pesquisar (dd/MM/yyyy):");
        String input = scan.nextLine();

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(input, formato);

		for (int i = 0; i < venda.size(); i++) {
				if (venda.get(i).getData().equals(data)) {
					contV++;
				}
		}
		System.out.printf("Quantidade de vendas encontradas: %d \n",contV);
	}
	private static void mostraRegistroVendas() {
		for (int i = 0; i < venda.size(); i++) {
			System.out.printf("Venda %d ",i+1);
			venda.get(i).MostrarVenda();;
			
		}
	}
	private static void calculaTroco() {
		if (precoTot>0) {		
			System.out.println("Valor pago pelo cliente: ");
			double pago = scan.nextDouble();

			if (precoTot>pago) {
				System.out.println("Valor faltate: R$ "+ (precoTot-pago)) ;
			} else if(precoTot<pago){
				double troco = pago - precoTot;
				System.out.println("Troco a devolver: R$ " + troco);
			}else{
				System.out.println("Não ha troco");
			} 
			if (confirmaPagamento()) {
			precoTot=0;
			}
		}else{
			System.out.println("Sem produtos comprados");
		}
		
	}
        
    private static boolean confirmaPagamento() {
		int escolha;
		System.out.printf("Deseja comfirma o pagamento\n");
		System.out.printf("1[SIM] 2[NÃO]\n");
		escolha = scan.nextInt();
		scan.nextLine();
		if (escolha==1) {
			return true;
		}else{
			return false;
		}
	}
	private static void calculaPreco() {
        double preco;
		int quant;

		System.out.println("Informe o preço do produto: ");
		preco = scan.nextDouble();
		scan.nextLine();

		System.out.println("Informe a quantidade do produto: ");
		quant = scan.nextInt();
		scan.nextLine();

		System.out.println("Digite a data da compra (dd/MM/yyyy):");
        String input = scan.nextLine();

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(input, formato);

		if (quant>10) {
			precoTot = (preco*quant)*0.95;
		}else{
			precoTot = preco*quant;
		}

		if (comfirmaCompra(precoTot)) {
			venda.add(new Venda(quant, precoTot,data));
		}else{
			precoTot=0;
		}

		System.out.printf("O preço totol é : R$ %.2f  \n", precoTot);
		
   
    }
	private static boolean comfirmaCompra(double precoTot) {
		int escolha;
		System.out.printf("O preço totol é : R$ %.2f  \n", precoTot);
		System.out.printf("Deseja comfirma a compra\n");
		System.out.printf("1[SIM] 2[NÃO]\n");
		escolha = scan.nextInt();
		scan.nextLine();
		if (escolha==1) {
			return true;
		}else{
			return false;
		}
		
	}
	

	private static void popular() {
		
		Loja l1 = new Loja("Loja Centro", "Comércio Silva LTDA", "12.345.678/0001-01", "São Paulo", "Sé", "Rua Direita");
		Loja l2 = new Loja("Loja Sul", "Vendas Costa S.A.", "98.765.432/0001-99", "Porto Alegre", "Ipanema", "Av. Guaíba");
		loja.add(l1);
		loja.add(l2);

		Vendedor v1 = new Vendedor("Ana Silva", 28, "São Paulo", "Sé", "Rua Direita", 2500.0, l1);
		Vendedor v2 = new Vendedor("Bruno Costa", 34, "Porto Alegre", "Ipanema", "Av. Guaíba", 3000.0, l2);
		Vendedor v3 = new Vendedor("Carla Souza", 22, "Manaus", "Adrianópolis", "Rua Maceió", 2200.0, l1);
		vendedores.add(v1);
		vendedores.add(v2);
		vendedores.add(v3);

		Cliente c1 = new Cliente("Marcos Oliveira", 30, "São Paulo", "Centro", "Rua A");
		Cliente c2 = new Cliente("Julia Santos", 25, "Porto Alegre", "Sul", "Rua B");
		Cliente c3 = new Cliente("Roberto Lima", 45, "São Paulo", "Moema", "Rua C");
		Cliente c4 = new Cliente("Fernanda Cruz", 31, "Manaus", "Adrianópolis", "Rua D");
		Cliente c5 = new Cliente("Pedro Rocha", 19, "São Paulo", "Sé", "Rua E");
		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		clientes.add(c4);
		clientes.add(c5);


		Gerente g1 = new Gerente("Ricardo Souza", 40, "São Paulo", "Centro", "Rua Direita", 6000.0, l1);
		Gerente g2 = new Gerente("Helena Reis", 38, "Porto Alegre", "Ipanema", "Av. Guaíba", 6500.0, l2);
		gerentes.add(g1);
		gerentes.add(g2);

		Item i1 = new Item("Notebook", "Eletrônicos", 3500.00);
		Item i2 = new Item("Mouse Gamer", "Periféricos", 150.00);
		Item i3 = new Item("Monitor 24'", "Eletrônicos", 900.00);
		itensDoPedido.add(i1);
		itensDoPedido.add(i2);
		itensDoPedido.add(i3);

		vinculosVendedores.add(new VinculoVen(v1.getIdFuncionario(), l1.getIdLoja())); 
		vinculosVendedores.add(new VinculoVen(v2.getIdFuncionario(), l2.getIdLoja())); 
		vinculosVendedores.add(new VinculoVen(v3.getIdFuncionario(), l1.getIdLoja())); 

		vinculosClientes.add(new VinculoCli(c1.getIdCliente(), l1.getIdLoja())); 
		vinculosClientes.add(new VinculoCli(c2.getIdCliente(), l2.getIdLoja())); 
		vinculosClientes.add(new VinculoCli(c3.getIdCliente(), l1.getIdLoja())); 
		vinculosClientes.add(new VinculoCli(c4.getIdCliente(), l2.getIdLoja())); 
		vinculosClientes.add(new VinculoCli(c5.getIdCliente(), l1.getIdLoja())); 

		vinculosGerentes.add(new VinculoGer(g1.getIdGerente(), l1.getIdLoja()));
		vinculosGerentes.add(new VinculoGer(g2.getIdGerente(), l2.getIdLoja()));

		List<Item> itensDestaVenda = new ArrayList<>();
		itensDestaVenda.add(i1);
		itensDestaVenda.add(i2);

		Pedido p1 = new Pedido(
			LocalDate.now(),           
			LocalDate.now(),           
			LocalDate.now().plusDays(3),
			c1, v1, l1, 
			itensDestaVenda
		);
		
	}

}
