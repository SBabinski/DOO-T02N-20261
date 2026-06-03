import java.util.Scanner;
import objetos.Venda;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Principal {
   static Scanner scan = new Scanner(System.in);
   static List <Venda> venda = new ArrayList<>();
   private static double precoTot=0;
    public static void main(String[] args) {
        menuPrincipal();
    }
    private static void menuPrincipal() {
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
			validarEscolha(escolha);
		}while(escolha != 0);

	}

	private static void validarEscolha(int escolha) {
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

}
