package fag;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MenuLoja {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculadoraService service = new CalculadoraService();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        double ultimoTotalCalculado = 0; 

        int opcao = 0;
        do {
            System.out.println("\n--- Sistema Dona Gabrielinha ---");
            System.out.println("[1] Calcular Preço Total [Registrar Venda]");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Histórico");
            System.out.println("[4] Buscar venda por Data");
            System.out.println("[5] Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            
            switch (opcao) {
            case 1:
                System.out.print("Quantidade de plantas: ");
                int q = scanner.nextInt();
                System.out.print("Preço: ");
                double p = scanner.nextDouble();
                Planta plantaVendida = new Planta(q, p);
                System.out.print("Data da venda (dd/MM/yyyy): ");
                String dataStr = scanner.next();
                LocalDate dataVenda = LocalDate.parse(dataStr, formatter);
                
                ultimoTotalCalculado = service.calcularPrecoTotal(plantaVendida, dataVenda);
                System.out.println("Total a pagar: R$ " + ultimoTotalCalculado);
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
            	System.out.print("Qual data deseja buscar? (dd/MM/yyyy): ");
                String dataBuscaStr = scanner.next();
                LocalDate dataBusca = LocalDate.parse(dataBuscaStr, formatter);
                
                service.buscarPorData(dataBusca);
                break;
            case 5:
                System.out.println("Saindo...");
                break;  
            default:
                System.out.println("Opção inválida!");
        }
        } while (opcao != 5);
        scanner.close();
    }
}
