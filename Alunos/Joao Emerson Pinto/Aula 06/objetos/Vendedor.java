package objetos;

public class Vendedor {

    private String nome;
    private int idade;
    private String loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private double[] salarioRecebido;

    public Vendedor(
        String nome,
        int idade,
        String loja,
        String cidade,
        String bairro,
        String rua,
        double salarioBase,
        double[] salarioRecebido
    ) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salarioRecebido = salarioRecebido != null && salarioRecebido.length >= 3
            ? salarioRecebido
            : new double[] { 1800.0, 1950.0, 2000.0 };
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getLoja() {
        return loja;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double[] getSalarioRecebido() {
        return salarioRecebido;
    }

    public void apresentarse() {
        System.out.println("Vendedor: " + nome + ", idade: " + idade + ", loja: " + loja);
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
