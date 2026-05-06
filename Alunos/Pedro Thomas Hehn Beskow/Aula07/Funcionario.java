// Funcionario estende Pessoa e adiciona os atributos de vínculo
// empregatício que são comuns a Vendedor e Gerente.
public abstract class Funcionario extends Pessoa {

    String   loja;
    double   salarioBase;
    double[] salarioRecebido; // ao menos três lançamentos de salário

    public Funcionario(String nome, int idade, Endereco endereco,
                       String loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja        = loja;
        this.salarioBase = salarioBase;
    }

    // média dos valores presentes em salarioRecebido
    public double calcularMedia() {
        double soma = 0;
        for (double s : salarioRecebido) soma += s;
        return soma / salarioRecebido.length;
    }

    // cada cargo define sua própria fórmula de bônus
    public abstract double calcularBonus();
}
