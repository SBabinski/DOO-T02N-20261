package Objetos;

import java.util.List;

public class Funcionario extends Pessoa {
    
    protected Loja loja;
    protected double salarioBase;
    protected List<Double> salarioRecebido;

    public Funcionario(){
        super();
    }

    public Funcionario(String nome, int idade, Loja loja, 
                       String cidade, String bairro, String rua,
                       double salarioBase, List<Double> salarioRecebido){
        super(nome, idade, cidade, bairro, rua);

        setLoja(loja);
        setSalarioBase(salarioBase);
        setSalarioRecebido(salarioRecebido);
    }

    public String getLoja() {
	    return loja.getNomeFantasia();
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public List<Double> getSalarioRecebido() {
        return salarioRecebido;
    }

    public String getTipo(){
        return "Funcionario";
    }

    public void setLoja(Loja loja) {
        if(loja != null) {
            this.loja = loja;
        }
    }

    public void setSalarioBase(double salarioBase) {
        if(salarioBase >= 0) {
            this.salarioBase = salarioBase;
        }
    }

    public void setSalarioRecebido(List<Double> salarioRecebido) {
        if (salarioRecebido != null) {
            this.salarioRecebido = salarioRecebido;
        }
    }

    public void calcularMedia(){
        double soma = 0;
        double media = 0;

        if(salarioRecebido == null || salarioRecebido.isEmpty()) {
            System.out.println("ERRO: Sem media salarial");
            return;
        }
        
        for(int i = 0; i < salarioRecebido.size(); i++){
            soma+=salarioRecebido.get(i);
        }
        media = soma/salarioRecebido.size();
        
        if(salarioRecebido.size() < 3){
            System.out.printf("A media dos salarios do Funcionario e: %.2f (Menos de 03 salários registrados)\n", media);
        }else{
            System.out.printf("A media dos salarios do Funcionario e: %.2f\n", media);
        }
    }

    public void calcularBonus(){
        System.out.println("Bonus não definido para o Funcionário");
    }

    public void apresentarSe(){
        System.out.printf("Funcionário - %s, %d anos trabalha na %s\n", nome, idade, loja.getNomeFantasia());
    }

    @Override
    public String toString() {
        return "Funcionario [" +
            "nome=" + nome +
            ", idade=" + idade +
            ", Loja=" + loja.getNomeFantasia() +
            "]";
    }
}
