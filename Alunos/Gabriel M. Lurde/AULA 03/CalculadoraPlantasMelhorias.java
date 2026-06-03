import java.util.Scanner;

public class CalculadoraPlantasMelhorias {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int ultimaQuantidade = 0;
        double ultimoTotal = 0;
        double ultimoDesconto = 0;

        int opcao = 0;

        while (opcao != 3) {

            System.out.println("\n | Calculadora da Dona Gabrielinha |");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");

            opcao = sc.nextInt();

            if (opcao == 1) {

                System.out.println("Quantidade da planta:");
                int quantidade = sc.nextInt();

                System.out.println("Preço da planta:");
                double preco = sc.nextDouble();
            
                double totalSemDesconto = quantidade * preco;

                double desconto = 0;
                double totalComDesconto = totalSemDesconto;

                if (quantidade > 10) {
                    desconto = totalSemDesconto * 0.05;
                    totalComDesconto = totalSemDesconto - desconto;
                }

                ultimaQuantidade = quantidade;
                ultimoTotal = totalComDesconto;
                ultimoDesconto = desconto;

                System.out.println("Quantidade Vendida: " + ultimaQuantidade);
                System.out.println("Valor total da Compra: " + totalSemDesconto);
                System.out.println("Desconto Aplicado: " + ultimoDesconto);
                System.out.println("Total com Desconto: " + ultimoTotal);
            }

            if (opcao == 2) {

                if (ultimoTotal == 0) {
                    System.out.println("Nenhuma venda registrada ainda.");
                } else {

                    System.out.println("Valor recebido:");
                    double recebido = sc.nextDouble();

                    double compra = ultimoTotal;
                    System.out.println("Valor da compra: " + compra);

                    double troco = recebido - compra;

                    System.out.println("Troco: " + troco);
                    System.out.println("\nÚltima venda:");
                    System.out.println("Quantidade: " + ultimaQuantidade);
                    System.out.println("Total: " + ultimoTotal);
                    System.out.println("Desconto: " + ultimoDesconto);
                }
            }

            if (opcao == 3) {
                System.out.println("Obrigado pela visita, volte sempre!");
            }
        }

        sc.close();
    }
}