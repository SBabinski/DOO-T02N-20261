import java.util.ArrayList;

public class Funcionario {
    String nome;
    int idade;
    String loja;
    double salarioBase;
    Endereco endereco;
    ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Funcionario(String nome, int idade, String loja, double salarioBase, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.endereco = endereco;
    }

    
    public void apresentarse() {
        System.out.println("Nome: " + nome + " | Loja: " + loja);
    }

    public void adicionarSalario(double valor) {
        this.salarioRecebido.add(valor);
    }

    public double calcularMedia() {
        double soma = 0;
        for (Double s : salarioRecebido) {
            soma += s;
        }
        return salarioRecebido.isEmpty() ? 0 : soma / salarioRecebido.size();
    }
}