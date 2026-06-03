import java.util.Scanner;

public class CalculadoraPlantasDona {

    public static void main(String[] args) {
        Scanner objScanner = new Scanner(System.in);
        int intOpcao;

        System.out.println(" Loja de Plantas da Dona Gabi ");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1- Calcular Preço Total");
            System.out.println("2- Calcular Troco");
            System.out.println("3- Sair");
            System.out.print("Escolha uma opção: ");
            intOpcao = objScanner.nextInt();

            switch (intOpcao) {
                case 1:
                    System.out.print("\nQuantidade de plantas: ");
                    int intQuantidade = objScanner.nextInt();
                    System.out.print("Preço unitário (R$): ");
                    double doubPreco = objScanner.nextDouble(); 

                    double doubTotal = calcularPrecoTotal(intQuantidade, doubPreco);
                    System.out.printf("Preço Total: R$ %.2f%n", doubTotal);
                    break;

                case 2:
                    System.out.print("\nValor recebido (R$): ");
                    double doubRecebido = objScanner.nextDouble();
                    System.out.print("Valor total da compra (R$): ");
                    double doubCompra = objScanner.nextDouble();

                    double doubTroco = calcularTroco(doubRecebido, doubCompra); 

                    if (doubTroco < 0) {
                        System.out.printf("Valor insuficiente! Faltam R$ %.2f%n", doubTroco * -1);
                    } else {
                        System.out.printf("Troco: R$ %.2f%n", doubTroco);
                    }
                    break;

                case 3:
                    System.out.println("\nSistema Finalizando...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (intOpcao != 3); 

        objScanner.close();
    }

    public static double calcularPrecoTotal(int intQuantidade, double doubPrecoUnitario) {
        return intQuantidade * doubPrecoUnitario;
    }

    public static double calcularTroco(double doubValorRecebido, double doubValorTotal) {
        return doubValorRecebido - doubValorTotal;
    }

}