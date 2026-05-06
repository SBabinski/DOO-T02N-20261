public class Vendedor {

    String nome;
    int idade;
    Loja loja;

    String cidade;
    String bairro;
    String rua;

    double salarioBase;
    double[] salariosRecebidos = {1500, 1600, 1700}; // exemplo

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