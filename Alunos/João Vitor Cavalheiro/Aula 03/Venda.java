//package Alunos.João Vitor Cavalheiro.Aula 03;

public final class Venda {
    int qtd;
    double total;

    public Venda(){

    }
    public Venda(int qtd, double total){
        setQtd(qtd);
        setTotal(total);
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
    
    public void mostrarVenda(){
        System.out.println("A quantidade de flores compradas foi de "+qtd+ " e o valor todal foi de R$"+total);
    }
}
