package aula3;

import java.util.Scanner;
import java.util.ArrayList;

public class Registro {

    Scanner scan = new Scanner(System.in);

    ArrayList<Venda> vendas = new ArrayList<>();

    public void registrarVenda(){

        int quant_flores;
        double val_total; 

        System.out.println("\nDigite a quantidade de flores: ");
        quant_flores = scan.nextInt();

        System.out.println("\nDigite o valor total da venda: ");
        val_total = scan.nextDouble();

        Venda venda = new Venda(quant_flores, val_total);

        vendas.add(venda);

        return;
    }

    public void listarVendas(){

        for (Venda vend : vendas){

            System.out.println("Quant. Flores: " + vend.getQuant_Flores() + " | Valor Total: " + vend.getVal_Total() +
             " | Desconto: " + vend.getDesconto() + "%");
        }

        return;
    }
}
