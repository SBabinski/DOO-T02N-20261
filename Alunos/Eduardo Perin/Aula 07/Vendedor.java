public class Vendedor extends Funcionario{
    double salarioBase = 1650;

    public Vendedor(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
        this.salarioBase = 1650;
    }
    public Vendedor(){this.salarioBase=1650;}

    @Override
    public void receberSalario() {
        salarioRecebido.add(salarioBase);
    }

    public static void main(String[] args) {
        Vendedor vendedor = new Vendedor();
        
        vendedor.receberSalario();
        System.out.println(vendedor.calcularMedia());
    }
    

}