
public class Gerente extends Funcionario {
    
    public Gerente (){

    }

    public Gerente(String nome, int idade, Lojas loja, double salarioBase, String cidade, String bairro, String rua){
        super(nome, idade, loja, salarioBase, cidade, bairro, rua);
    }


    public void apresentarse() {
        System.out.println("Gerente: " + this.getNome() + " | Idade: " + this.getIdade() + " | Loja: " + loja.getNomeFantasia());
    }

    @Override
    public double getBonus() {
        return 0.35;
    }

}

