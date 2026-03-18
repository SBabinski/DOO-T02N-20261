import java.util.Scanner;
public class Principal {
   static Scanner scan = new Scanner(System.in);
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
			System.out.println("[3] - Sair");
			escolha = scan.nextInt();
			scan.nextLine();
			validarEscolha(escolha);
		}while(escolha != 3);

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
			System.out.println("Obrigado por utilizar o sistema.👍👍");
			break;
		default:
			System.out.println("Selecione uma opção valida");
			break;
		}

	}
    private static void calculaTroco() {
        System.out.println("Valor pago pelo cliente: ");
        double pago = scan.nextDouble();
        double troco = pago - precoTot;
        System.out.println("Troco a devolver: R$ " + troco);
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

		precoTot = preco*quant;

		System.out.println("O preço totol é : R$ "+ precoTot);
		

    }

}
