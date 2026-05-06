package aula2;

import java.util.Scanner;

public class Calculadora {

    Scanner scan = new Scanner(System.in);

    public void calcInterface(){
        
        int opcao;
        double total, troco;

        while (true){

            System.out.println("[1] - Calcular Preço Total\n" +
                               "[2] - Calcular Troco\n" +
                               "[3] - Sai\n");

            opcao = scan.nextInt();

            switch (opcao){

                case 1:

                    total = this.Total();

                    System.out.println("Total: " + total);

                    break;

                case 2:

                    troco = this.Troco();

                    System.out.println("Troco: " + troco);

                    break;

                case 3:

                    System.out.println("Saindo...");

                    return;
                
                default:

                    System.out.println("Opção Inválida!");
            }
        }
    }

    public double Total(){

        int quant;
        double prc_unit;

        System.out.println("Digite a quantidade de itens: ");
        quant = scan.nextInt();

        System.out.println("Digite o preço de cada unidade: ");
        prc_unit = scan.nextDouble();

        return quant * prc_unit; 
    }

    public double Troco(){

        double val_cliente, total;

        System.out.println("Digite o valor recebido do cliente: ");
        val_cliente = scan.nextDouble();

        System.out.println("Digite o valor total da compra: ");
        total = scan.nextDouble();

        return val_cliente - total;
    }
}