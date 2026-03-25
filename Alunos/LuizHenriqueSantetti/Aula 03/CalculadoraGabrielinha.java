import java.util.Scanner;

public class CalculadoraGabrielinha {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int escolha;

        int totalPlantasVendidas = 0;
        double totalVendas = 0;
        double totalDescontos = 0;

        while (true) {
            
            System.out.print("ESCOLHA ALGUMA OPCAO");
            
            System.out.println("1 - TOTAL");
            System.out.println("2 - TROCO");
            System.out.println("3 - REGISTRO DE VENDAS");
            System.out.println("4 - FECHAR");
            
            escolha = entrada.nextInt();
            
        if (escolha == 1) {
             System.out.print("QUANTIDADE =");   
                int qtd = entrada.nextInt();
             System.out.print("PRECO =");
                double preco = entrada.nextDouble();
                
                double total = qtd * preco;
                double desconto = 0;

            if (qtd > 10) {
                    desconto = total * 0.05;
                    total -= desconto;
             System.out.println("DESCONTO DE 5% ADICIONADO");
                }

                totalPlantasVendidas += qtd;
                totalVendas += total;
                totalDescontos += desconto;

                System.out.println("TOTAL = R$" + total);

            } else if (escolha == 2) {
             System.out.print("TOTAL PAGO =");
                double pago = entrada.nextDouble();
             System.out.print("VALOR COMPRA =");
                double compra = entrada.nextDouble();
                double troco = pago - compra;
 
             System.out.println("TROCO = R$ " + troco);
            
            } else if (escolha == 3) {
             System.out.println("------------- REGISTRO DE VENDAS -------------");
             System.out.println("PLANTAS VENDIDAS: " + totalPlantasVendidas);
             System.out.println("TOTAL VENDAS: R$ " + totalVendas);
             System.out.println("DESCONTOS APLICADOS: R$ " + totalDescontos);
             System.out.println("----------------------------------------------");

            } else if (escolha == 4) {
             System.out.println("FECHAR");
                break;

            } else {
             System.out.println("INVALIDO");
             System.out.println();
            }
        }

        entrada.close();
    }
}