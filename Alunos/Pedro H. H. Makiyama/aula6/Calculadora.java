package aula6;

import java.util.Scanner;

public class Calculadora {

    Scanner scan = new Scanner(System.in);

    public double Total(){

        int quant;
        double prcunit;

        System.out.println("Digite a quantidade de itens: ");
        quant = scan.nextInt();

        System.out.println("Digite o preço de cada unidade: ");
        prcunit = scan.nextDouble();

        scan.nextLine();

        if (quant >= 10)

            return quant * prcunit * 0.95;

        return quant * prcunit; 
    }

    public double Troco(){

        double valcliente, total;

        System.out.println("Digite o valor recebido do cliente: ");
        valcliente = scan.nextDouble();

        System.out.println("Digite o valor total da compra: ");
        total = scan.nextDouble();

        scan.nextLine();
        
        return valcliente - total;
    }
}