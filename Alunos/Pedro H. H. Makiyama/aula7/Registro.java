package aula7;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Registro {

    Scanner scan = new Scanner(System.in);
    DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private ArrayList<Venda> vendas = new ArrayList<>();
    
    public void registrarVenda(){

        int quantflores;
        double valtotal; 
        String dataS;

        System.out.println("\nDigite a data (dd/MM/yyy): ");
        dataS = scan.nextLine();

        System.out.println("\nDigite a quantidade de flores: ");
        quantflores = scan.nextInt();

        System.out.println("\nDigite o valor total da venda: ");
        valtotal = scan.nextDouble();

        scan.nextLine();
        
        Venda venda = new Venda(quantflores, valtotal, dataS);

        vendas.add(venda);
    }

    public void listarVendas(){

        for (Venda vend : vendas){

            if(!vend.equals(null)){
            
                 System.out.println("Quant. Flores: " + vend.getQuantflores() + " | Valor Total: " + vend.getValtotal() +
                                    " | Desconto: " + vend.getDesconto() + "%");
            }
        }
    }

    public void listarVendasDia(String diaS){

        LocalDate dia = LocalDate.parse(diaS, parser);

        for (Venda vend : vendas){

            if (vend.getDate().equals(dia))

                 System.out.println("Quant. Flores: " + vend.getQuantflores() + " | Valor Total: " + vend.getValtotal() +
                                    " | Desconto: " + vend.getDesconto() + "%");
        }
    }

    public void listarVendasMes(int mes, int ano){

        for (Venda vend : vendas){

            if (vend.getDate().getMonthValue() == mes && vend.getDate().getYear() == ano)

                 System.out.println("Quant. Flores: " + vend.getQuantflores() + " | Valor Total: " + vend.getValtotal() +
                                    " | Desconto: " + vend.getDesconto() + "%");
        }
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }
}
