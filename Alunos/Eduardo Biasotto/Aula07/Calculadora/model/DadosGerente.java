package Calculadora.model;

import java.util.ArrayList;

public class DadosGerente extends DadosPessoa {

    private DadosLoja loja;
    private double salarioBase;
    private ArrayList<Double> salariosRecebidos;

    public DadosGerente(String nome, int idade, DadosLoja loja, DadosEndereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

        this.salariosRecebidos = new ArrayList<>();
        salariosRecebidos.add(1500.0);
        salariosRecebidos.add(1600.0);
        salariosRecebidos.add(1550.0);
    }

    @Override
    public void apresentarse() {
        System.out.println("\n======= DADOS DO GERENTE - " + getNome() + " =======");
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Loja: " + (loja != null ? loja.getNomeFantasia() : "Sem loja"));
    }

    public double calcularSalarioMedia() {
        double soma = 0;
        if (salariosRecebidos.isEmpty()) {
            return 0;
        }
        for (double salario : salariosRecebidos) {
            soma += salario;
        }
        return soma / salariosRecebidos.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }

    @Override
    public String toString() {
        return getNome();
    }
}