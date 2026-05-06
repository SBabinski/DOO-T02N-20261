package Objetos;

import java.util.List;

public class Vendedor extends Funcionario{

    public Vendedor(){
        super();
    }

    public Vendedor(String nome, int idade, Loja loja, 
                       String cidade, String bairro, String rua,
                       double salarioBase, List<Double> salarioRecebido){
        super(nome, idade, loja, cidade, bairro, rua, salarioBase, salarioRecebido);
    }
    
    @Override
    public String getTipo(){
        return "Vendedor";
    }
    
    @Override
    public void calcularBonus(){
        double bonus = 0;
        bonus = salarioBase * 0.2;
        System.out.printf("O Bonus do Vendedor e: %.2f\n", bonus);
    }

    @Override
    public void apresentarSe(){
        System.out.printf("Vendedor - %s, %d anos trabalha na %s\n", nome, idade, loja.getNomeFantasia());
    }

    @Override
    public String toString() {
        return "Vendedor [" +
            "nome=" + nome +
            ", idade=" + idade +
            ", Loja=" + loja.getNomeFantasia() +
            "]";
    }
}
