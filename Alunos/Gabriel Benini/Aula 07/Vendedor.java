package CalculadoraLoja;

public class Vendedor extends Funcionario {

    public Vendedor(String nome, int idade, Loja loja, Endereco endereco, float salarioBase) {
        super(nome, idade, loja, endereco, salarioBase);
        this.salarioRecebido = new float[] {
                salarioBase * 0.9f,
                salarioBase,
                salarioBase * 1.1f
        };
    }

    @Override
    public float calcularBonus() {
        return salarioBase * 0.2f;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + (loja != null ? loja.getNome() : "Não vinculada"));
        System.out.println();
    }

    public float calcularMedia() {

        if (salarioRecebido == null || salarioRecebido.length == 0) return 0.0f;

        float soma = 0;
        for (float salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.length;
    }

    public String getNome() {
        return nome;
    }

    public Loja getLoja() {
        return loja;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public float[] getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setSalarioRecebido(float[] salarioRecebido) {
        this.salarioRecebido = salarioRecebido;
    }
}