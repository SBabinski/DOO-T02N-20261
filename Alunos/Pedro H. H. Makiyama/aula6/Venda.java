package aula6;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

    private int quantflores;
    private double valtotal;
    private double desconto;
    LocalDate data;
    DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    Scanner scan = new Scanner(System.in);

    public Venda(int quantflores, double valtotal, String dataS){

        this.quantflores = quantflores;
        this.valtotal = valtotal;
        this.data = LocalDate.parse(dataS, parser);

        if (quantflores >= 10)

            this.desconto = 5;
        
        else

            this.desconto = 0;
    }

    public int getQuantflores(){
        return quantflores;
    }

    public double getValtotal(){
        return valtotal;
    }

    public double getDesconto(){
        return desconto;
    }

    public LocalDate getDate(){

        return data;
    }

    public void setQuantflores(int quantflores) {
        this.quantflores = quantflores;
    }

    public void setValtotal(double valtotal) {
        this.valtotal = valtotal;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
}
