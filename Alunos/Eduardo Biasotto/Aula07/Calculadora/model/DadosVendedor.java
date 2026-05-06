package Calculadora.model;

import java.util.ArrayList;

public class DadosVendedor extends DadosPessoa {
    
    private DadosLoja loja = null;
    private double salarioBase;
    
    private ArrayList<Double> salariosRecebidos;

    public DadosVendedor(String nome, int idade, DadosLoja loja, DadosEndereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

        this.salariosRecebidos = new ArrayList<>();

        salariosRecebidos.add(1200.0);
        salariosRecebidos.add(1300.0);  
        salariosRecebidos.add(1250.0);
    }

    @Override
    public void apresentarse() {
        System.out.println("\nNome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Loja: " + (loja != null ? loja.getNomeFantasia() : "Sem loja"));
    }

    public void adicionarSalario(double salario) {
        salariosRecebidos.add(salario);
    }

    public double calcularSalarioMedia(){
        double soma = 0;
    
        if (salariosRecebidos.isEmpty()) {
            return 0;
        }
        else{
        for (double salario : salariosRecebidos){
            soma += salario;
        }
    }
            return soma / salariosRecebidos.size();
    }
    
    public double calcularBonus(){
        return salarioBase *0.2;
    }

    @Override
    public String toString() {
        return getNome();
}

}
