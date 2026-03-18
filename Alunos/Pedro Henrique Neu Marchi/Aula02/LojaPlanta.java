import java.util.Scanner;

public class LojaPlanta {
    
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
		do {
			System.out.println("\n=== MENU PRINCIPAL ===");
			System.out.println("1 - Calcular preço total");
			System.out.println("2 - Calcular troco");
			System.out.println("3 - Sair");
			System.out.print("Escolha: ");
			opcao = leitor.nextInt();
			leitor.nextLine();

			switch (opcao) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 3);

        leitor.close();
    }

}
