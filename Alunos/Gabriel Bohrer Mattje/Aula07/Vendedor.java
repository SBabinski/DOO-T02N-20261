package Aula07;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {

    String loja;
    double salarioBase;
    List<Double> salariosRecebidos = new ArrayList<>();

    public Vendedor(String nome, int idade, Endereco endereco,
                    String loja, double salarioBase) {

        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

        salariosRecebidos.add(2500.0);
        salariosRecebidos.add(2600.0);
        salariosRecebidos.add(3000.0);
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
        return salarioBase * 0.2;
    }
}