package Calculadora.model;

import java.util.ArrayList;

import Calculadora.repository.BancoDados;

public class Vendedor {
    static ArrayList<Vendedor> vendedores = BancoDados.vendedores;
    
    private String nome;
    private int idade;
    private Loja loja = null;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private double salarioBase;
    
    private ArrayList<Double> salariosRecebidos;

    public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, String numero, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.salarioBase = salarioBase;

        this.salariosRecebidos = new ArrayList<>();

        salariosRecebidos.add(1200.0);
        salariosRecebidos.add(1300.0);  
        salariosRecebidos.add(1250.0);
    }

    public static void apresentarse() {
        for(int i =0; i < BancoDados.vendedores.size();i++){
            System.out.printf("Nome: %s | Idade: %d", BancoDados.vendedores.get(i).getNome(), BancoDados.vendedores.get(i).idade);
        }
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

    public String getNome() {
        return nome;
    }

}
