package aula7;

import java.util.Scanner;
import java.util.ArrayList;

public class Gerente {

    private String nome;
    private int idade;
    private String loja;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;
    private double salariobase;
    private double salariorecebido;
    private Endereco endereco;

    private ArrayList<Double> salariosRecebidos = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public Gerente(String nome, int idade, String loja, String estado, String cidade, String bairro, String rua,
                   int numero, String complemento, double salariobase){

        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.salariobase = salariobase;
        
        endereco = new Endereco(estado, cidade, bairro, rua, numero, complemento);

        salariosRecebidos.add(5000.);
        salariosRecebidos.add(7500.);
        salariosRecebidos.add(10000.);
    }

    public void apresentarse(){

        System.out.println("\n| Nome - " + this.nome + " | Idade - " + this.idade + " | Loja - " + this.loja + " " + endereco.apresentarLogradouro());
    }

    public double calcularMedia(){

        double total = 0.;
        int cont = 0;

        for (Double salariorecebid : salariosRecebidos){

            if (!salariorecebid.equals(null)){

                total += salariorecebido;

                cont++;
            }
        }

        return total / cont;
    }

    public double calcularBonus(){

        return salariobase * .35;
    }

    public void adicionarSalarioRecebido(){

        System.out.println("\nDigite o valor do salário recebido: ");
        salariorecebido = scan.nextDouble();

        scan.nextLine();

        salariosRecebidos.add(salariorecebido);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
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
        endereco.setCidade(cidade);
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
        endereco.setBairro(bairro);
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
        endereco.setRua(rua);
    }

    public double getSalariobase() {
        return salariobase;
    }

    public void setSalariobase(double salariobase) {
        this.salariobase = salariobase;
    }

    public ArrayList<Double> getSalariosRecebidos() {
        return salariosRecebidos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        endereco.setEstado(estado);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        endereco.setNumero(numero);
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
        endereco.setComplemento(complemento);
    }
}
