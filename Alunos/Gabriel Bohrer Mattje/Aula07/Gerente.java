package Aula07;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Pessoa {

    String loja;
    double salarioBase;
    List<Double> salariosRecebidos = new ArrayList<>();

    public Gerente(String nome, int idade, Endereco endereco,
                   String loja, double salarioBase) {

        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

        salariosRecebidos.add(4000.0);
        salariosRecebidos.add(4200.0);
        salariosRecebidos.add(4500.0);
    }

    @Override
    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double s : salariosRecebidos) soma += s;
        return soma / salariosRecebidos.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}