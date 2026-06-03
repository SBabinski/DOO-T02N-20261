public class Funcionarios extends Pessoa{
    public String Loja;
    public double salarioBase;
    public double[] salarioRecebido = new double[3];

    public Funcionarios(String nome, int idade, String Loja, Endereco endereco, double salarioBase, double[] salarioRecebido){
        super(nome, idade, endereco);
        this.Loja = Loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = salarioRecebido;
    }
}