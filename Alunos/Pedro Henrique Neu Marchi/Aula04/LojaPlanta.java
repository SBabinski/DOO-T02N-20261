import java.util.Scanner;

public class LojaPlanta {
    
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
		do {
			System.out.println("\n=== MENU PRINCIPAL ===");
			System.out.println("1 - Calcular preço final");
			System.out.println("2 - Calcular troco");
			System.out.println("3 - Buscar vendas por mês");
			System.out.println("4 - Buscar vendas por dia");
			System.out.println("5 - Sair");
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
				Metodos.buscarPorMes();
				break;
			case 4:
				Metodos.buscarPorDia();
				break;
			case 5:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 5);
        leitor.close();
    }

}
