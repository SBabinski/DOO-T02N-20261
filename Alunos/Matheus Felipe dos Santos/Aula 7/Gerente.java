import java.util.ArrayList;

public class Gerente extends Pessoa {

    String loja;
    double salarioBase;
    ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Gerente(String nome, int idade, Endereco endereco, double salarioBase, String loja) {
        super(nome, idade, endereco);
        this.salarioBase = salarioBase;
        this.loja = loja;

        salarioRecebido.add(3000.0);
        salarioRecebido.add(3200.0);
        salarioRecebido.add(3100.0);
    }

    void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
        endereco.apresentarLogradouro();
    }

    double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    double calcularBonus() {
        return salarioBase * 0.35;
    }
}