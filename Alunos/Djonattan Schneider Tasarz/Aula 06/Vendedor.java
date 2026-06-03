public class Vendedor {
    private String nome;
    private int idade;
    private Loja loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private double[] salarioRecebido = new double[3];

    public Vendedor(String nome, int idade, Loja loja, String cidade, 
                   String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        
        salarioRecebido[0] = salarioBase * 0.95;
        salarioRecebido[1] = salarioBase * 1.02;
        salarioRecebido[2] = salarioBase * 1.05;
    }

    public void apresentarSe() {
        System.out.printf("Vendedor: %s, %d anos - Loja: %s%n", 
                         nome, idade, loja.getNomeFantasia());
    }

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / 3;
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}