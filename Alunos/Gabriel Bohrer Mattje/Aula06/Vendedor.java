package Aula06;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {
    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;

    List<Double> salariosRecebidos = new ArrayList<>();

    public Vendedor(String nome, int idade, String loja,
                    String cidade, String bairro, String rua, 
                    double salarioBase){
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;

        salariosRecebidos.add(2500.00);
        salariosRecebidos.add(2600.00);
        salariosRecebidos.add(3000.00);
    }

    public void apresentarse(){
        System.out.println("Nome: "+ nome);
        System.out.println("Idade: "+ idade);
        System.out.println("Loja: "+ loja);
    }

    public double calcularMedia(){
        double soma = 0;
        
        for(double salario : salariosRecebidos){
            soma+=salario;
        }

        return soma/salariosRecebidos.size();
    }

    public double calcularBonus(){
        return salarioBase*0.2;
    }
}
