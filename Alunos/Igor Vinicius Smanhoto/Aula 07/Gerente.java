package objects;

import java.util.ArrayList;

public class Gerente extends Pessoa {

    private String loja;
    private double salarioBase;
    private ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Gerente(String nome, int idade, Endereco endereco, String loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

        salarioRecebido.add(3000.0);
        salarioRecebido.add(3200.0);
        salarioRecebido.add(3100.0);
    }

    @Override
    public void apresentarSe() {
        System.out.println("Gerente: " + nome + ", Idade: " + idade + ", Loja: " + loja);
    }

    public double calcularMedia() {
        return salarioRecebido.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public double calcularBonus() {
        return salarioBase * 0.35; // ✔ conforme enunciado
    }
}