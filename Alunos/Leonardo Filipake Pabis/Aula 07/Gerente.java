import java.util.ArrayList;

public class Gerente extends Funcionario {
    //cidade, bairro e rua vem endereco dentro de pessoa
    private Lojas loja;
    private double salarioBase;
    private double salarioRecebido;
    private ArrayList<Double> salarios = new ArrayList<>();


    public Gerente (){

    }

    public Gerente(String nome, int idade){
        super(nome, idade);
        setLoja(loja);
        
    }

    public String getLoja() {
        return loja.getNomeFantasia();
    }

    public void setLoja(Lojas loja){
        this.loja = loja;
    }

    public void apresentarse() {
        System.out.println("Nome: " + this.getNome() + " | Idade: " + this.getIdade() + " | ");
    }
    
    public double getSalarioBase(){
        return salarioBase;
    }

}

