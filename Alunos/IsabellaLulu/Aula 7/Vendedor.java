import java.util.ArrayList;

public class Vendedor extends Pessoa {

    protected Loja loja;
    protected double salarioBase;
    protected ArrayList<Double> salarios = new ArrayList<>();

    public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

        salarios.add(1500.0);
        salarios.add(1600.0);
        salarios.add(1700.0);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double s : salarios) soma += s;
        return soma / salarios.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }

    @Override
    public void apresentarSe() {
        System.out.println(nome + " | Loja: " + loja.getNomeFantasia());
    }
}