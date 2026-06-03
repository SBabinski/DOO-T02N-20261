public class Vendedor extends Funcionario{

    public Vendedor(String nome, int idade, String cidade, String bairro, String rua, String loja, double salarioBase,double[] salarioRecebido) {
        super(nome, idade, cidade, bairro, rua, loja, salarioBase, salarioRecebido);
    }

    @Override
    public void apresentarFuncionario(){
        System.out.println("=======================");
        System.out.println("----------Vendedor----------");
        System.out.println("Nome: "+getNome());
        System.out.println("Idade: "+getIdade());
        System.out.println("Trabalha na Loja: "+getLoja());
        System.out.println("Com um salário de  base de R$"+getSalarioBase());
        System.out.println("Sua média salárial dos 3 últimos meses é R$"+ calcularSalario());
        System.out.println("Caso atinja a meta de venda ganha uma bonificação de R$"+calcularBonus());
        System.out.println("=======================a");
    }
}