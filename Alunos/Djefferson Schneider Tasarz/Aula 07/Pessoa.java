import java.util.ArrayList;
import java.util.List;

public abstract class Pessoa {
    protected String nome;
    protected int idade;
    protected Endereco endereco;
    protected double salarioBase;
    protected List<Double> salarioRecebido;

    public Pessoa(String nome, int idade, Endereco endereco, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
        this.salarioRecebido.add(2500.0);
        this.salarioRecebido.add(2700.0);
        this.salarioRecebido.add(2900.0);
    }

    public abstract void apresentarse();

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    public double getSalarioBase() {
        return salarioBase;
    }
}