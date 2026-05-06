package objetos;

public class Vendedor extends Funcionario {
    private static int contador = 0;
    private int idVendedor;

    public Vendedor(String nome, int idade, String cidade, String bairro, String rua, double salarioBase, Loja loja) {
        super(nome, idade, cidade, bairro, rua, salarioBase, loja);
        this.idVendedor = ++contador;
    }

    @Override
    public void apresentarse() {
        super.apresentarse();
        System.out.println("ID Vendedor: " + idVendedor);
    }

    @Override
    public double calcularBonus() {
        return getSalarioBase() * 0.20;
    }

    public int getIdVendedor() {
        return idVendedor;
    }
}
