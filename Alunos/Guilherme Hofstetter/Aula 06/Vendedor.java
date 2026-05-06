public class Vendedor {
    private String nome;
    private int idade;
    private Loja loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private double[] salarioRecebido = new double[3];

    public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        //Salários: mês 1, mês 2 (com hora extra), mês 3 (com bônus)
        this.salarioRecebido[0] = salarioBase;
        this.salarioRecebido[1] = salarioBase + 300.00;
        this.salarioRecebido[2] = salarioBase + 150.00;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
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

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public double getSalarioBase() { return salarioBase; }
    public double[] getSalarioRecebido() { return salarioRecebido; }
    public Loja getLoja() { return loja; }
}
