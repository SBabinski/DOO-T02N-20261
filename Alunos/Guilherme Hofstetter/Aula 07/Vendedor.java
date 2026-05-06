public class Vendedor extends Pessoa {
    private Loja loja;
    private double salarioBase;
    private double[] salarioRecebido = new double[3];

    public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido[0] = salarioBase;
        this.salarioRecebido[1] = salarioBase + 300.00;
        this.salarioRecebido[2] = salarioBase + 150.00;
    }

    @Override
    public void apresentarse() {
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Loja: " + loja.getNomeFantasia());
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

    public Loja getLoja() { return loja; }
    public double getSalarioBase() { return salarioBase; }
    public double[] getSalarioRecebido() { return salarioRecebido; }
}
