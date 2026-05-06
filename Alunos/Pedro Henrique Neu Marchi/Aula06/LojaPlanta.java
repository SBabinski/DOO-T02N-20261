import java.util.Scanner;

public class LojaPlanta {
	
    public static void main(String[] args) {
	Loja loja = new Loja();
	loja.nomeFantasia = "My Plant";
	loja.cnpj = "12.345.678/0001-9";
	loja.cidade = "Caixa Prego";
	loja.bairro = "Centro";
	loja.rua = "Rua das Flores";
	loja.numero = "111";

	Vendedor v1 = new Vendedor();
	v1.nome = "Choso";
	v1.idade = 30;
	v1.loja = loja;
	v1.salarioBase = 2000;

	Cliente c1 = new Cliente();
	c1.nome = "Yuki";
	c1.idade = 25;

	loja.vendedores = new Vendedor[]{v1};
	loja.clientes = new Cliente[]{c1};
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
		do {
			System.out.println("\n=== SISTEMA MY PLANT ===");
			System.out.println("1 - Calcular venda");
			System.out.println("2 - Calcular troco");
			System.out.println("3 - Buscar vendas por mês");
			System.out.println("4 - Buscar vendas por dia");
			System.out.println("5 - Mostrar clientes");
			System.out.println("6 - Mostrar vendedores");
			System.out.println("7 - Dados da loja");
			System.out.println("8 - Sair");
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
				loja.contarClientes();
				break;

			case 6:
				loja.contarVendedores();
				break;

			case 7:
				loja.apresentarSe();
				break;

			case 8:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Opção inválida!");
		}
		} while (opcao != 5);
        leitor.close();
    }

}
