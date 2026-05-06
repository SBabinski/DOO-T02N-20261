package CalculadoraLoja;

public class Vendedor {

    private String nome;
    private int idade;
    private Loja loja;
    private String cidade;
    private String bairro;
    private String rua;
    private float salarioBase;
    private float[] salarioRecebido;

    public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, float salarioBase) {

        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;

        this.salarioRecebido = new float[]{salarioBase * 0.9f, salarioBase, salarioBase * 1.1f};
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + (loja != null ? loja.getNome() : "Não vinculada"));
        System.out.println();
    }

    public float calcularMedia() {

        if (salarioRecebido == null || salarioRecebido.length == 0) return 0.0f;

        float soma = 0;
        for (float salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.length;
    }

    public float calcularBonus() {
        return salarioBase * 0.2f;
    }

    public String getNome() {
        return nome;
    }

    public Loja getLoja() {
        return loja;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public float[] getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setSalarioRecebido(float[] salarioRecebido) {
        this.salarioRecebido = salarioRecebido;
    }
}