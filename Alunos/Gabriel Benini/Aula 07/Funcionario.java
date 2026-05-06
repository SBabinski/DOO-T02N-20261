package CalculadoraLoja;

public class Funcionario extends Pessoa{

    protected Loja loja;
    protected float salarioBase;
    protected float[] salarioRecebido;

    public Funcionario(String nome, int idade, Loja loja, Endereco endereco, float salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
    }

    public float calcularMedia() {
        float soma = 0;

        for (float salario : salarioRecebido) {
            soma += salario;
        }

        return soma / salarioRecebido.length;
    }

    @Override
    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja.getNome());
    }

    public float calcularBonus() {
        return 0;
    }

}
