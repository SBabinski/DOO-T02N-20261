public class Gerente extends Pessoa {
    Loja loja;
    double salarioBase;
    double[] salarioRecebido;

    public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase, double[] salarioRecebido) {
        super(nome, idade, endereco);
        this.loja = loja;
        if (salarioBase > 0) {
            this.salarioBase = salarioBase;
        } else {
            System.out.println("Salário base inválido. Verifique e tente novamente.");
        }
        if (salarioRecebido != null && salarioRecebido.length >= 3) {
            this.salarioRecebido = salarioRecebido;
        } else {
            System.out.println("Informe ao menos 3 lançamentos de salário.");
        }
    }

    @Override
    public void apresentarse() {
        System.out.println("----DADOS DO GERENTE----");
        super.apresentarse();
        System.out.println("Loja: " + loja.nomeFantasia);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double s : salarioRecebido) {
            soma += s;
        }
        double media = soma / 3;
        System.out.println("Média salarial: " + media);
        return media;
    }

    public double calcularBonus() {
        double bonus = salarioBase * 0.35;
        System.out.println("Bônus calculado: " + bonus);
        return bonus;
    }
}
