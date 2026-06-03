
public class Gerente extends Funcionario {

    public Gerente(String nome, int idade, String loja, double salarioBase, Endereco endereco) {
        super(nome, idade, loja, salarioBase, endereco);
    }

    // O bônus do Gerente é diferente do Vendedor
    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}