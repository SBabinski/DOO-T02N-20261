package Locadora.model;

public class Cliente {
    private String nome;
    private String cpf;
    private String numeroCNH;

    public Cliente(String nome, String cpf, String numeroCNH) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroCNH = numeroCNH;
    }

    public void apresentarse() {
        System.out.println("\n==== Dados do Cliente ====");
        System.out.println("\nNome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Número da CNH: " + numeroCNH);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumeroCNH() {
        return numeroCNH;
    }

    public void setNumeroCNH(String numeroCNH) {
        this.numeroCNH = numeroCNH;
    }

}
