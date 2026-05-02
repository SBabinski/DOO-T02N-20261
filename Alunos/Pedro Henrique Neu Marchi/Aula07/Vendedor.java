public class Vendedor extends Pessoa {

    Loja loja;
    double salarioBase;
    double[] salariosRecebidos = {1500.0, 1600.0, 1700.0};

    @Override
    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja.nomeFantasia);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salariosRecebidos) {
            soma += salario;
        }
        return soma / salariosRecebidos.length;
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}
