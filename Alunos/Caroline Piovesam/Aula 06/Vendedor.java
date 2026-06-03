import java.util.ArrayList;

public class Vendedor {

    private ArrayList<Double> SalarioRecebido = new ArrayList<>();
    private String nome;
    private int idade;
    private String loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;

    public Vendedor(String nome, int idade, String loja,
            String cidade, String bairro, String rua, double salarioBase) {

        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
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
