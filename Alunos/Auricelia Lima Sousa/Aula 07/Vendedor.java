
public class Vendedor extends Funcionario { 

    public Vendedor(String nome, int idade, String loja, double salarioBase, Endereco endereco) {
      
        super(nome, idade, loja, salarioBase, endereco);
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}