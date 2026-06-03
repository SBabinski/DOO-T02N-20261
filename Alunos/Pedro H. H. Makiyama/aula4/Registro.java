package aula4;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Registro {

    Scanner scan = new Scanner(System.in);
    DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    ArrayList<Venda> vendas = new ArrayList<>();
    
    public void registrarVenda(){

        int quant_flores;
        double val_total; 
        String dataS;

        

        System.out.println("\nDigite a data (dd/MM/yyy): ");
        dataS = scan.nextLine();

        System.out.println("\nDigite a quantidade de flores: ");
        quant_flores = scan.nextInt();

        System.out.println("\nDigite o valor total da venda: ");
        val_total = scan.nextDouble();

        scan.nextLine();
        
        Venda venda = new Venda(quant_flores, val_total, dataS);

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

    public void listarVendasDia(String diaS){

        LocalDate dia = LocalDate.parse(diaS, parser);

        for (Venda vend : vendas){

            if (vend.getDate().equals(dia))

                 System.out.println("Quant. Flores: " + vend.getQuant_Flores() + " | Valor Total: " + vend.getVal_Total() +
                 " | Desconto: " + vend.getDesconto() + "%");
        }
    }

    public void listarVendasMes(int mes, int ano){

        for (Venda vend : vendas){

            if (vend.getDate().getMonthValue() == mes && vend.getDate().getYear() == ano)

                 System.out.println("Quant. Flores: " + vend.getQuant_Flores() + " | Valor Total: " + vend.getVal_Total() +
                 " | Desconto: " + vend.getDesconto() + "%");
        }

    }
}
