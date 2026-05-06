public class Vendedor {

    String   nome;
    int      idade;
    String   loja;
    String   cidade;
    String   bairro;
    String   rua;
    double   salarioBase;
    double[] salarioRecebido; // três lançamentos de salário

    public Vendedor(String nome, int idade, String loja,
                    String cidade, String bairro, String rua,
                    double salarioBase) {
        this.nome        = nome;
        this.idade       = idade;
        this.loja        = loja;
        this.cidade      = cidade;
        this.bairro      = bairro;
        this.rua         = rua;
        this.salarioBase = salarioBase;

        // mês normal, mês com desconto por falta, mês com bônus
        this.salarioRecebido = new double[]{
            salarioBase,
            salarioBase * 0.95,
            salarioBase + calcularBonus()
        };
    }

    // printa nome, idade e loja
    public void apresentarse() {
        System.out.println("\nVendedor");
        System.out.println("Nome  : " + nome);
        System.out.println("Idade : " + idade + " anos");
        System.out.println("Loja  : " + loja);
    }

    // média dos três salários recebidos
    public double calcularMedia() {
        double soma = 0;
        for (double s : salarioRecebido) soma += s;
        return soma / salarioRecebido.length;
    }

    // 20% do salário base
    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}
