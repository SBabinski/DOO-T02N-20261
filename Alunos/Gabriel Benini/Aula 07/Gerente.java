package CalculadoraLoja;

public class Gerente extends Funcionario {

    public Gerente(String nome, int idade, Loja loja, Endereco endereco, float salarioBase) {
        super(nome, idade, loja, endereco, salarioBase);
        this.salarioRecebido = new float[]{
                salarioBase,
                salarioBase * 1.1f,
                salarioBase * 1.2f
        };
    }

    @Override
    public float calcularBonus() {
        return salarioBase * 0.35f;
    }
}