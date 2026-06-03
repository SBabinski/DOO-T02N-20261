public class Gerente extends Vendedor {

    public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco, loja, salarioBase);
    }

    @Override
    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}