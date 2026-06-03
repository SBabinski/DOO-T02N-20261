import java.util.Scanner;
import objetos.Venda;
import objetos.Vendedor;
import objetos.VinculoCli;
import objetos.VinculoVen;
import objetos.Cliente;
import objetos.Loja;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Principal {
   static Scanner scan = new Scanner(System.in);
   static List <Venda> venda = new ArrayList<>();
   static List<Vendedor> vendedores = new ArrayList<>();
   static List<Cliente> clientes = new ArrayList<>();
   static List<Loja> loja = new ArrayList<>();
   static List<VinculoVen> vinculosVendedores = new ArrayList<>();
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
		int escolha=1111111;
		do {
			System.out.println("-----Menu-----");
			System.out.println("[1] - Lojas");
			System.out.println("[2] - Calcular media de salário de cada vendedor");
			System.out.println("[3] - Bonus vendedores");
			System.out.println("[0] - Sair");
			
			escolha = scan.nextInt();
			scan.nextLine();
			validarEscolhaGestor(escolha);
		}while(escolha != 0);
	}
	private static void validarEscolhaGestor(int escolha) {
		switch (escolha) {
		case 1:
			mostrarLojas();
			break;
		case 2:
			for(Vendedor v : vendedores) {
                System.out.println("Vendedor: " + v.getNome() + " - Média: " + v.calcularMedia());
            }
			break;
		case 3:
			for(Vendedor v : vendedores) {
                System.out.println("Vendedor: " + v.getNome() + " - Bônus: " + v.calcularBonus());
            }
			break;
		case 0:
			System.out.println("Obrigado por utilizar o sistema.");
			break;
		default:
			System.out.println("Selecione uma opção valida");
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
			System.out.println("[2] Listar Clientes (Detalhado)");
			System.out.println("[0] Voltar");
			
			op = scan.nextInt();
			scan.nextLine();

			switch (op) {
				case 1 -> listarVendedoresDaLoja(selecionada);
				case 2 -> listarClientesDaLoja(selecionada);
				case 0 -> System.out.println("Voltando...");
				default -> System.out.println("Opção inválida!");
			}
		} while (op != 0);
	}

	
	private static void exibirResumoLoja(Loja selecionada) {
		long totalVend = vinculosVendedores.stream().filter(v -> v.getIdLo() == selecionada.getIdLoja()).count();
		long totalCli = vinculosClientes.stream().filter(v -> v.getIdLo() == selecionada.getIdLoja()).count();

		System.out.println("\n==============================");
		selecionada.apresentarse();
		System.out.println("Total de Vendedores: " + totalVend);
		System.out.println("Total de Clientes: " + totalCli);
		System.out.println("==============================");
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
		case 0:
			System.out.println("Obrigado por utilizar o sistema.");
			break;
		default:
			System.out.println("Selecione uma opção valida");
			break;
		}

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

	private static void popular(){

	Loja l1 = new Loja("Loja Centro", "Comércio Silva LTDA", "12.345.678/0001-01", "São Paulo", "Sé", "Rua Direita");
    Loja l2 = new Loja("Loja Sul", "Vendas Costa S.A.", "98.765.432/0001-99", "Porto Alegre", "Ipanema", "Av. Guaíba");
    loja.add(l1);
    loja.add(l2);

    vendedores.add(new Vendedor("Ana Silva", 28, l1.getNomeFantasia(), "São Paulo", "Sé", "Rua Direita", 2500.0));
    vendedores.add(new Vendedor("Bruno Costa", 34, l2.getNomeFantasia(), "Porto Alegre", "Ipanema", "Av. Guaíba", 3000.0));
    vendedores.add(new Vendedor("Carla Souza", 22, l1.getNomeFantasia(), "Manaus", "Adrianópolis", "Rua Maceió", 2200.0));

    clientes.add(new Cliente("Marcos Oliveira", 30, "São Paulo", "Centro", "Rua A"));
    clientes.add(new Cliente("Julia Santos", 25, "Porto Alegre", "Sul", "Rua B"));
    clientes.add(new Cliente("Roberto Lima", 45, "São Paulo", "Moema", "Rua C"));
    clientes.add(new Cliente("Fernanda Cruz", 31, "Manaus", "Adrianópolis", "Rua D"));
    clientes.add(new Cliente("Pedro Rocha", 19, "São Paulo", "Sé", "Rua E"));

	vinculosVendedores.add(new VinculoVen(1, 1)); 
    vinculosVendedores.add(new VinculoVen(2, 2)); 
    vinculosVendedores.add(new VinculoVen(3, 1)); 

    
    vinculosClientes.add(new VinculoCli(1, 1)); 
    vinculosClientes.add(new VinculoCli(2, 2)); 
	vinculosClientes.add(new VinculoCli(3, 1)); 
	vinculosClientes.add(new VinculoCli(4, 2)); 
	vinculosClientes.add(new VinculoCli(5, 1)); 
	

	}

}
