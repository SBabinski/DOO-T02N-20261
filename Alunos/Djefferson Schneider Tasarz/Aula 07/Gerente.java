public class Gerente extends Pessoa {
    private Loja loja;

    public Gerente(String nome, int idade, Loja loja, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco, salarioBase);
        this.loja = loja;
    }

    public void apresentarse() {
        System.out.println("Gerente: " + nome + ", Idade: " + idade + ", Loja: " + loja.getNomeFantasia());
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}