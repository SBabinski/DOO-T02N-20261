package aula4;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

    int quant_flores;
    double val_total, desconto;
    LocalDate data;
    DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    Scanner scan = new Scanner(System.in);

    public Venda(int quant_flores, double val_total, String dataS){

        this.quant_flores = quant_flores;
        this.val_total = val_total;
        this.data = LocalDate.parse(dataS, parser);

        if (quant_flores >= 10)

            this.desconto = 5;
        
        else

            this.desconto = 0;
    }

    public int getQuant_Flores(){

        return this.quant_flores;
    }

    public double getVal_Total(){

        return this.val_total;
    }

    public double getDesconto(){

        return this.desconto;
    }

    public LocalDate getDate(){

        return this.data;
    }
}
