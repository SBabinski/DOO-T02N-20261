public class Vendedor extends Pessoa {

    String loja;
    double salarioBase;
    double[] salarioRecebido = new double[3];

    public Vendedor(String nome, int idade, Endereco endereco, double salarioBase, String loja) {
        super(nome, idade, endereco);
        this.salarioBase = salarioBase;
        this.loja = loja;
    }

    void apresentarVendedor() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
        endereco.apresentarLogradouro();
    }

    double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.length;
    }

    double calcularBonus() {
        return salarioBase * 0.2;
    }
}