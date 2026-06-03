package Objetos;

public class Cliente extends Pessoa{

    public Cliente(){
        super();
    }

    public Cliente(String nome, int idade, String cidade, String bairro, String rua){
        super(nome, idade, cidade, bairro, rua);
    }


    public void apresentarSe(){
        System.out.printf("Nome: %s, Idade: %d\n", nome, idade);
    }

    @Override
    public String toString(){
	    return "Pessoa [nome=" + nome + 
        ", idade=" + idade + 
        ", endereco=" + endereco +
        "]";
    }
}
