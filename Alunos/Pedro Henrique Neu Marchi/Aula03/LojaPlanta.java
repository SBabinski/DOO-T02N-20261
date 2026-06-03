import java.util.Scanner;

public class LojaPlanta {
    
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
		do {
			System.out.println("\n=== MENU PRINCIPAL ===");
			System.out.println("1 - Calcular preço final");
			System.out.println("2 - Calcular troco");
			System.out.println("3 - Mostrar vendas");
			System.out.println("4 - Sair");
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
				Metodos.mostrarVendas();
				break;
			case 4:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 4);
        leitor.close();
    }

}
