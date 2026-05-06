package aula3;

import java.util.Scanner;

public class Calculadora {

    Scanner scan = new Scanner(System.in);

    public double Total(){

        int quant;
        double prc_unit;

        System.out.println("Digite a quantidade de itens: ");
        quant = scan.nextInt();

        System.out.println("Digite o preço de cada unidade: ");
        prc_unit = scan.nextDouble();

        if (quant >= 10)

            return quant * prc_unit * 0.95;

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