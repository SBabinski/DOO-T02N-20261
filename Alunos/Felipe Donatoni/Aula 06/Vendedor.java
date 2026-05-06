import java.util.ArrayList;

public class Vendedor {

    String nome;
    int idade;
    Loja loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;

    ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Vendedor() {
        salarioRecebido.add(1500.0);
        salarioRecebido.add(1600.0);
        salarioRecebido.add(1700.0);
    }

    public void apresentarSe() {
        System.out.println("Nome do vendedor: " + nome);
        System.out.println("Idade do vendedor: " + idade);

        if (loja != null) {
            System.out.println("Loja: " + loja.nomeFantasia);
        } else {
            System.out.println("Loja: Não definida");
        }
    }

    public double calcularMedia() {
        double soma = 0;
        for (double s : salarioRecebido) {
            soma += s;
        }
        return soma / salarioRecebido.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}