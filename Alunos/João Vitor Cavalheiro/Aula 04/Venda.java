//package Alunos.João Vitor Cavalheiro.Aula 03;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Venda {
    int qtd;
    double total;
    LocalDate data;
    DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Venda(int qtd, double total, LocalDate data){
        this.qtd=qtd;
        this.total=total;
        this.data=data;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public void mostrarVenda(){
        System.out.println("A data dessa venda foi "+data.format(formatoBR)+" quantidade de flores compradas foi de "+qtd+ " e o valor todal foi de R$"+total);
    }
}
