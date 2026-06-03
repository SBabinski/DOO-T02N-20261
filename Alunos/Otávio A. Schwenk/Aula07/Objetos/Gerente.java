package Objetos;

import java.util.List;

public class Gerente extends Funcionario{

    public Gerente(){
        super();
    }

    public Gerente(String nome, int idade, Loja loja, 
                       String cidade, String bairro, String rua,
                       double salarioBase, List<Double> salarioRecebido){
        super(nome, idade, loja, cidade, bairro, rua, salarioBase, salarioRecebido);
    }
    
    @Override
    public String getTipo(){
        return "Gerente";
    }

    @Override
    public void calcularBonus(){
        double bonus = 0;
        bonus = salarioBase * 0.35;
        System.out.printf("O Bonus do Gerente e: %.2f\n", bonus);
    }

    @Override
    public void apresentarSe(){
        System.out.printf("Gerente - %s, %d anos trabalha na %s\n", nome, idade, loja.getNomeFantasia());
    }

    @Override
    public String toString() {
        return "Gerente [" +
            "nome=" + nome +
            ", idade=" + idade +
            ", Loja=" + loja.getNomeFantasia() +
            "]";
    }
}