// Vendedor herda de Funcionario (que herda de Pessoa).
// Bônus = 20% do salário base.
public class Vendedor extends Funcionario {

    public Vendedor(String nome, int idade, Endereco endereco,
                    String loja, double salarioBase) {
        super(nome, idade, endereco, loja, salarioBase);

        // mês normal | mês com desconto por falta | mês com bônus
        this.salarioRecebido = new double[]{
            salarioBase,
            salarioBase * 0.95,
            salarioBase + calcularBonus()
        };
    }

    @Override
    public void apresentarse() {
        System.out.println("\nVendedor");
        System.out.println("Nome  : " + nome);
        System.out.println("Idade : " + idade + " anos");
        System.out.println("Loja  : " + loja);
        endereco.apresentarLogradouro();
    }

    // bônus do vendedor: 20% do salário base
    @Override
    public double calcularBonus() {
        return salarioBase * 0.20;
    }
}
