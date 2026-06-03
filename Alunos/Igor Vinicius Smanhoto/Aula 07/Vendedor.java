package objects;
import java.util.ArrayList;

public class Vendedor extends Pessoa {

    private String loja;
    private double salarioBase;
    private ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Vendedor(String nome, int idade, Endereco endereco, String loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

        salarioRecebido.add(1500.0);
        salarioRecebido.add(1600.0);
        salarioRecebido.add(1550.0);
    }

    public void apresentarSe() 
    {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja);
    }

    public double calcularMedia() {
        return salarioRecebido.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}