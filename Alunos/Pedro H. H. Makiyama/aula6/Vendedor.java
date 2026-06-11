package aula6;
import java.util.ArrayList;
import java.util.Scanner;

public class Vendedor {
    
    private int idade;
    private double salariobase;
    private double salariorecebido;
    private String nome;
    private String loja;
    private String cidade;
    private String bairro;
    private String rua;

    private ArrayList<Double> salariosRecebidos = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salariobase){

        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salariobase = salariobase;

        salariosRecebidos.add(1000.);
        salariosRecebidos.add(4500.);
        salariosRecebidos.add(2500.);
    }

    public void apresentarse(){

        System.out.println("\nNome: " + this.nome + " - Idade: " + this.idade + " - Loja: " + this.loja);
    }

    public double calcularMedia(){

        int cont = 0;
        double somaSalariosRecebidos = 0;

        for (Double salrec : salariosRecebidos){

            somaSalariosRecebidos += salrec;

            cont++;
        }

        return somaSalariosRecebidos / cont;
    }

    public void calcularBonus(){

        System.out.println("\nBônus: " + this.salariobase * .2);
    }

    public void adicionarSalarioRecebido(){

        System.out.println("\nDigite o valor do salário recebido: ");
        salariorecebido = scan.nextDouble();

        scan.nextLine();

        salariosRecebidos.add(salariorecebido);
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getSalarioBase() {
        return salariobase;
    }

    public void setSalarioBase(double salariobase) {
        this.salariobase = salariobase;
    }

    public double getSalarioRecebido() {
        return salariorecebido;
    }

    public void setSalarioRecebido(double salariorecebido) {
        this.salariorecebido = salariorecebido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro(){
        return bairro;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }

    public double getSalariobase() {
        return salariobase;
    }

    public void setSalariobase(double salariobase) {
        this.salariobase = salariobase;
    }

    public double getSalariorecebido() {
        return salariorecebido;
    }

    public void setSalariorecebido(double salariorecebido) {
        this.salariorecebido = salariorecebido;
    }
}   
