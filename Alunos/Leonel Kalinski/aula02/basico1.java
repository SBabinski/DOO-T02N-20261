import java.util.Scanner;

public class basico1{

    
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
        
    }
    public static void menu(){
        int opcao;
        do {
            System.out.println("------- MENU ------");
            System.out.println("1-precoTotal");
            System.out.println("2-troco");
            System.out.println("3-Sair");
            opcao = scan.nextInt();
            validarEscolha(opcao);
          
        } while (opcao != 0);
    }
    public static void validarEscolha(int opcao) {
		switch (opcao) {
		case 1: 
			precoTotal();
			break;
		case 2:
			troco();
			break;
		case 3:
        System.out.println("saindo...");			
        break;
	
		}	

	}
    private static void troco() {
		System.out.println("Quanto voce pagou?");
		int pago = scan.nextInt();
		System.out.println("qual era o preco total?");
		int preco = scan.nextInt();
		System.out.println("O seu troco e:"+(pago-preco));
		
	}
	public static void precoTotal() {
        System.out.println("quantas plantas voce comprou");
        int qtd=scan.nextInt();
        System.out.println("qual o valor da planta");
        int preco= scan.nextInt();
        System.out.println("o valor total e:"+ (preco + qtd));
    }
}