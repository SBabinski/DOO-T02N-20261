package Calculadora.model;

public class Cliente {
    private String nome;
    private int idade;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;

    public Cliente(String nome, int idade, String cidade, String bairro, String rua, String numero) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public void apresentarse() {
        System.out.println("\n======= DADOS DO CLIENTE - " + nome + " =======");
        System.out.println("\nNome: " + nome);
        System.out.println("Idade: " + idade);
    }
}
