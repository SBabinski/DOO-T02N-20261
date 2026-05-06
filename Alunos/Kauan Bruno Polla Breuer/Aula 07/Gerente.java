public class Gerente extends Pessoa {

    Loja loja;
    double salarioBase;
    double[] salarioRecebido;

    public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        this(nome, idade, endereco, loja, salarioBase, new double[] { 4500, 4700, 5000 });
    }

    public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase, double[] salarioRecebido) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

        if (salarioRecebido == null || salarioRecebido.length < 3) {
            this.salarioRecebido = new double[] { 4500, 4700, 5000 };
        } else {
            this.salarioRecebido = salarioRecebido.clone();
        }
    }

    @Override
    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + (loja != null ? loja.nomeFantasia : "Sem loja"));
    }

    public double calcularMedia() {
        double soma = 0;

        for (double salario : salarioRecebido) {
            soma += salario;
        }

        return soma / salarioRecebido.length;
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}