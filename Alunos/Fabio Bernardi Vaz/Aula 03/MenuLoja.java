package fag;

import java.util.Scanner;

public class MenuLoja {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculadoraService service = new CalculadoraService();
        double ultimoTotalCalculado = 0; 

        int opcao = 0;
        do {
            System.out.println("\n--- Sistema Dona Gabrielinha ---");
            System.out.println("[1] Calcular Preço Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Histórico");
            System.out.println("[4] Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            
            switch (opcao) {
            case 1:
                System.out.print("Quantidade de plantas: ");
                int q = scanner.nextInt();
                System.out.print("Preço: ");
                double p = scanner.nextDouble();
                Planta plantaVendida = new Planta(q, p);
                
                ultimoTotalCalculado = service.calcularPrecoTotal(plantaVendida);
                System.out.println("Total: R$ " + ultimoTotalCalculado);
                break;

            case 2:
            	System.out.print("Valor Recebido: ");
                double pago = scanner.nextDouble();
                
                double resultado = service.processarTroco(pago, ultimoTotalCalculado);
                System.out.println(resultado);
                if (resultado > 0) {
                    System.out.printf("O troco a ser dado é: R$ %.2f%n", resultado);
                } else if(resultado == 0){
                    System.out.printf("O troco está correto, não precisa devolver valor nenhum");
                } else {
                	System.out.println("O valor pago é menor que o valor da compra");
                }
                break;

            case 3:
                service.mostrarHistorico();
                break;
            case 4:
                System.out.println("Saindo...");
                break;    
            default:
                System.out.println("Opção inválida!");
        }
        } while (opcao != 4);
        scanner.close();
    }
}
