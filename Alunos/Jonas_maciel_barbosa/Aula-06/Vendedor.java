public class Vendedor {
    String nome;
    int idade;
    Loja loja;
    String cidade;
    String bairro;
    String rua;
    double SalarioBase;
    double[] SalarioRecebido;

public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, double SalarioBase, double[] SalarioRecebido){
    this.nome = nome;
    if (idade > 0 && idade < 100) {
        this.idade = idade;
    } else {
        System.out.println("Idade do vendedor inválida. Verifique e tente novamente.");
    }
    this.loja = loja;
    this.cidade = cidade;
    this.bairro = bairro;
    this.rua = rua;
    if (SalarioBase > 0) {
        this.SalarioBase = SalarioBase;
    } else {
        System.out.println("Salário base inválido. Verifique e tente novamente.");
    }
    if (SalarioRecebido != null && SalarioRecebido.length >= 3) {
        this.SalarioRecebido = SalarioRecebido;
    } else {
        System.out.println("Informe ao menos 3 lançamentos de salário.");
    }
}

public void apresentarse() {
    System.out.println("----DADOS DO VENDEDOR----");
    System.out.println("Nome: " + nome);
    System.out.println("Idade: " + idade);
    System.out.println("Loja: " + loja.nomeFantasia);
}

public double calcularMedia() {
    double soma = 0;
    for (double s : SalarioRecebido) {
        soma += s;
    }
    double media = soma / 3;
    System.out.println("Média salarial: " + media);
    return media;
}

public double calcularBonus() {
    double bonus = SalarioBase * 0.2;
    System.out.println("Bônus calculado: " + bonus);
    return bonus;
}
}