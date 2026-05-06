package Objetos;

import java.util.List;

public class Vendedor {

    private String nome;
    private int idade;
    private String Loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private List<Double> salarioRecebido;

    public Vendedor() {
    }

    public Vendedor(String nome, int idade, String Loja, String cidade, String bairro, String rua, double salarioBase, List<Double> salarioRecebido) {
        setNome(nome);
        setIdade(idade);
        setLoja(Loja);
        setCidade(cidade);
        setBairro(bairro);
        setRua(rua);
        setSalarioBase(salarioBase);
        setSalarioRecebido(salarioRecebido);
    }

    public String getNome() {
	    return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getLoja() {
	    return Loja;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public List<Double> getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setNome(String nome) {
        if(nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
    }

    public void setIdade(int idade) {
        if(idade >= 0) {
            this.idade = idade;
        }
    }

    public void setLoja(String Loja) {
        if(Loja != null && !Loja.isBlank()) {
            this.Loja = Loja;
        }
    }

    public void setCidade(String cidade) {
        if(cidade != null && !cidade.isBlank()) {
            this.cidade = cidade;
        }
    }

    public void setBairro(String bairro) {
        if(bairro != null && !bairro.isBlank()) {
            this.bairro = bairro;
        }
    }

    public void setRua(String rua) {
        if(rua != null && !rua.isBlank()) {
            this.rua = rua;
        }
    }

    public void setSalarioBase(double salarioBase) {
        if(salarioBase >= 0) {
            this.salarioBase = salarioBase;
        }
    }

    public void setSalarioRecebido(List<Double> salarioRecebido) {
        if (salarioRecebido != null && salarioRecebido.size() >= 3) {
            this.salarioRecebido = salarioRecebido;
        }
    }

    public void calcularMedia(){
        double soma = 0;
        double media = 0;

        if (salarioRecebido == null || salarioRecebido.isEmpty()) {
            System.out.println("ERRO: Sem media salarial");;
        }

        for(int i = 0; i < salarioRecebido.size(); i++){
            soma+=salarioRecebido.get(i);
        }
        media = soma/salarioRecebido.size();
        
        System.out.printf("A media dos salarios do Funcionario e: %.2f\n", media);
    }

    public void calcularBonus(){
        double bonus = 0;
        bonus = salarioBase * 0.02;
        System.out.printf("O Bonus do Funcionario e: %.2f\n", bonus);
    }

    public void apresentarSe(){
        System.out.printf("Vendedor: %s, %d anos trabalha na %s\n", nome, idade, Loja);
    }

    @Override
    public String toString() {
        return "Pessoa [nome=" + nome +
        ", idade=" + idade +
        ", cidade=" + cidade +
        ", bairro=" + bairro +
        ", rua=" + rua +
        ", salarioBase=" + salarioBase +
        "]";
    }
}
