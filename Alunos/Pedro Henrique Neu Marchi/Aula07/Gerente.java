public class Gerente extends Pessoa {

    Loja loja;
    double salarioBase;
    double[] salariosRecebidos = {4500.0, 4800.0, 5100.0};

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
        return salarioBase * 0.35;
    }
}
