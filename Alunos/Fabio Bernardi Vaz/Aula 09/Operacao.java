package fag;

public abstract class Operacao {
    protected String simbolo;
 
    public Operacao(String simbolo) {
        this.simbolo = simbolo;
    }
 
    public abstract double executar(double a, double b) throws CalculadoraException;
 
    public String getSimbolo() {
        return simbolo;
    }
}
