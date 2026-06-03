public class Vendedor extends Pessoa {
    private Loja loja;
    private double salarioBase;
    private double[] salarioRecebido = new double[3];

    public Vendedor(String nome, int idade, Loja loja, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        salarioRecebido[0] = salarioBase * 0.95;
        salarioRecebido[1] = salarioBase * 1.02;
        salarioRecebido[2] = salarioBase * 1.05;
    }

    public void apresentarSe() {
        System.out.printf("Vendedor: %s, %d anos - Loja: %s%n", nome, idade, loja.getNomeFantasia());
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
        return salarioBase * 0.2;
    }
}