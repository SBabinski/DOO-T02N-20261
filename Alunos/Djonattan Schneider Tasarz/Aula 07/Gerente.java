public class Gerente extends Pessoa {
    private Loja loja;
    private double salarioBase;
    private double[] salarioRecebido = new double[3];

    public Gerente(String nome, int idade, Loja loja, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        salarioRecebido[0] = salarioBase * 1.10;
        salarioRecebido[1] = salarioBase * 1.15;
        salarioRecebido[2] = salarioBase * 1.20;
    }

    public void apresentarse() {
        System.out.printf("Gerente: %s, %d anos - Loja: %s%n", nome, idade, loja.getNomeFantasia());
        endereco.apresentarLogradouro();
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