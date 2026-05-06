package aula3;

import java.util.Scanner;

public class Venda {

    int quant_flores;
    double val_total, desconto;

    Scanner scan = new Scanner(System.in);

    public Venda(int quant_flores, double val_total){

        this.quant_flores = quant_flores;
        this.val_total = val_total;

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
}
