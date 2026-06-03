import java.util.ArrayList;

public class Gerente extends Pessoa {

    private ArrayList<Double> SalarioRecebido = new ArrayList<>();
    private String loja;
    private double salarioBase;

    public Gerente(String nome, int idade, Endereco endereco, String loja,
            double salarioBase) {

        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

    }

    public void apresentarse() {

        System.out.println("Nome gerente: " + this.nome + " | " + "Idade: "
                + this.idade + " | " + "Loja: " + this.loja);
        System.out.println();
    }

    public void adicionarSalario(double valor) {
        SalarioRecebido.add(valor);
    }

    public double calcularMedia() {

        double soma = 0;
        for (double salario : SalarioRecebido) {
            soma += salario;
        }

        return soma / SalarioRecebido.size();
    }

    public double calcularBonus() {
        return this.salarioBase * 0.35;
    }
}
