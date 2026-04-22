package Calculadora.model;

public abstract class DadosPessoa {
    private String nome;
    private int idade;
    private DadosEndereco endereco;

    public DadosPessoa(String nome, int idade, DadosEndereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public abstract void apresentarse();

    public String getNome() {
        return nome;
    }
    
    public int getIdade() {
        return idade;
    }

    public DadosEndereco getEndereco() {
        return endereco;
    }
}