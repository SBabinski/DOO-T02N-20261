import java.util.ArrayList;

public class Vendedor extends Pessoa {

    private ArrayList<Double> SalarioRecebido = new ArrayList<>();
    private String loja;
    private double salarioBase;

    public Vendedor(String nome, int idade, String loja,
            double salarioBase, Endereco endereco) {

        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

    }

    public void apresentarse() {

        System.out.println("Nome vendedor: " + this.nome + " | " + "Idade: "
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
        return this.salarioBase * 0.2;
    }
}
