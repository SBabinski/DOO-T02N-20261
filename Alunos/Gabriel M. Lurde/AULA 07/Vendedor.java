public class Vendedor {

    String nome;
    int idade;
    String loja;
    Endereco endereco;
    double salarioBase;
    double[] salarioRecebido;

    void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }

    double calcularMedia() {
        double soma = 0;
        for (int i = 0; i < salarioRecebido.length; i++) {
            soma += salarioRecebido[i];
        }
        return soma / salarioRecebido.length;
    }

    double calcularBonus() {
        return salarioBase * 0.2;
    }
}