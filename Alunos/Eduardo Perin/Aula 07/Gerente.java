public class Gerente extends Funcionario {
    double salarioBase = 2500;

    public Gerente(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
        this.salarioBase = 2500;
    }
    public Gerente(){this.salarioBase=2500;}

    @Override
    public void receberSalario() {
        salarioRecebido.add(salarioBase);
    }
    @Override
    public double calcularBonus(){
        return salarioBase * 0.35;
    }

    public static void main(String[] args) {
        Gerente gerente = new Gerente();
        System.out.println(gerente.calcularBonus());
    }
    

}
