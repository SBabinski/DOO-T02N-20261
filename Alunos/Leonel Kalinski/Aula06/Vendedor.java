package objetos;

import java.util.ArrayList;

import fag.Floricultura;

public class Vendedor {
    private String nome;
    private int idade;
    private Loja loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private double salarioRecebido; 
    private ArrayList<Double> salarios = new ArrayList<>();

    public Vendedor() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if ((nome != null) && (!nome.isBlank())){
            this.nome = nome;
        }
        else {
            System.out.println("Digite um nome válido:");
            String novoNome = Floricultura.scan.nextLine();
            setNome(novoNome);
        }
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade > 0){
            this.idade = idade;
        }
      
    }

    public String getLoja() {
        return loja.getNomeFantasia();
    }

    public void setLoja(Loja loja){
        this.loja = loja;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if ((cidade != null) && (!cidade.isBlank())){
            this.cidade = cidade;
        }
        else {
            System.out.println("Digite uma cidade válida:");
            String novoCidade = Floricultura.scan.nextLine();
            setCidade(novoCidade);
        }
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if ((bairro != null) && (!bairro.isBlank())){
            this.bairro = bairro;
        }
        else {
            System.out.println("Digite um bairro válido:");
            String novoBairro = Floricultura.scan.nextLine();
            setBairro(novoBairro);
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        if ((rua != null) && (!rua.isBlank())){
            this.rua = rua;
        }
        else {
            System.out.println("Digite uma rua válida:");
            String novoRua = Floricultura.scan.nextLine();
            setRua(novoRua);
        }
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase > 0){
            this.salarioBase = salarioBase;
        }

    }

    public double getSalarioRecebido() {
        return salarioRecebido;
    }

    public void receberSalario(double salarioRecebido) {
        if (salarioRecebido >= this.salarioBase){
            this.salarioRecebido = salarioRecebido;
            salarios.add(salarioRecebido);
        }
        else {
            System.out.println("Digite um salario recebido válido:");
            double novoSalarioRecebido = Floricultura.scan.nextDouble();
            Floricultura.scan.nextLine();
            receberSalario(novoSalarioRecebido);
        }
        
    }

    public void apresentarse() {
        System.out.println("Vendedor: " + this.nome + " | Idade: "+ this.idade + " | Loja: " + this.loja.getNomeFantasia());
    }
    
    public double calcularMediaSalarial() {
        int contador=0;
        double soma = 0;
        for (int i=0; i<salarios.size(); i++){
            soma += salarios.get(i);
            contador += 1;
        }
        double media = soma / contador;
        return media;
    }

    public double calcularBonusSalarial() {
        double bonus = this.salarioBase * 0.2;
        return bonus;
    }
    
}
