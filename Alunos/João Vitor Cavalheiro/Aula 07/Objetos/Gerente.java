public class Gerente extends Funcionario {

    public Gerente(String nome, int idade, String cidade, String bairro, String rua, String loja, double salarioBase, double[] salarioRecebido) {
        super(nome, idade, cidade, bairro, rua, loja, salarioBase, salarioRecebido);
    }

    @Override
    public double calcularBonus(){
    double bonus= getSalarioBase()*0.35;
    return bonus;
    }

}
