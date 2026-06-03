
public class Vendedor extends Funcionario{
    public Vendedor() {

    }

    public Vendedor (String nome, int idade, Lojas loja, Double salarioBase, String cidade, String bairro, String rua) {
        super(nome, idade, loja, salarioBase, cidade, bairro, rua);
    }

    @Override
    public double getBonus() {
        return 0.2;
    }

    public void apresentarse() {
        System.out.println("Vendedor: " + this.getNome() + " | Idade: "+ this.getIdade() + " | Loja: " + this.loja.getNomeFantasia());
    }
    
    
}
