import java.util.ArrayList;
public class Gerente extends Pessoa {
    
    String Loja;
    double salarioBase; 

    ArrayList<Double> salarioRecebido;

    public Gerente (String nome, int idade,String Loja,Endereco endereco,double salarioBase) {
        super (nome, idade, endereco);
        this.Loja = Loja;
        this.salarioBase = salarioBase;

        this.salarioRecebido = new ArrayList<>();

        salarioRecebido.add(2500.0);
        salarioRecebido.add(2600.0);
        salarioRecebido.add(2700.0);

    }
    @Override
    public void apresentarSe () {
        System.out.println("Gerente");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + Loja);
    }
    public double calcularMedia() {
        double soma = 0;
        for (Double salario : salarioRecebido) {
            soma = soma + salario;
        }
        return soma / salarioRecebido.size();
    }
    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}