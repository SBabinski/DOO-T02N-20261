public class Funcionario extends Pessoa{
    double salarioBase;
    double[] salarioRecebido = new double[3];
    String loja;

        public Funcionario(String nome, int idade, String cidade, String bairro, String rua, String loja ,double salarioBase, double[] salarioRecebido) {
        super(nome, idade, cidade, bairro, rua);
        this.loja=loja;
        this.salarioBase=salarioBase;
        this.salarioRecebido=salarioRecebido;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double[] getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setSalarioRecebido(double[] salarioRecebido) {
        this.salarioRecebido = salarioRecebido;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public void apresentarFuncionario(){
        System.out.println("=======================");
        System.out.println("----------Funcionario----------");
        System.out.println("Nome: "+getNome());
        System.out.println("Idade: "+getIdade());
        System.out.println("Trabalha na Loja: "+getLoja());
        System.out.println("Com um salário de  base de R$"+getSalarioBase());
        System.out.println("Sua média salárial dos 3 últimos meses é R$"+ calcularSalario());
        System.out.println("Caso atinja a meta de venda ganha uma bonificação de R$"+calcularBonus());
        System.out.println("=======================a");
    }

    public double calcularSalario(){
    double soma;
    soma = getSalarioRecebido()[0]+getSalarioRecebido()[1]+getSalarioRecebido()[2];
    double media = soma/3;
    return media;
    }

    public double calcularBonus(){
    double bonus= getSalarioBase()*0.2;
    return bonus;
    }

}
