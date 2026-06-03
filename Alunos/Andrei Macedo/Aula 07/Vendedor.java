import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {

    private double salaraio_base;
    private Loja loja;
    private List<Double> salario_recebido = new ArrayList<>(List.of(1.560, 1.621, 1.550));

    public Vendedor(double salario_base, String nome, int idade, Endereco endereco, List<Double> salario_recebido,
    Loja loja) {
        super(nome, idade, endereco);
        this.salaraio_base = salario_base;
        this.salario_recebido = salario_recebido;
        this.loja = loja;
    }

    public Vendedor(String nome, int idade, double salario, Endereco endereco){
        super(nome, idade, endereco);
        this.salaraio_base = salario;
    }

    public void apresentarse() {
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Salario base: " + getSalaraio_base());
    }

    public double getSalaraio_base() {
        return salaraio_base;
    }

    public void setSalaraio_base(double salaraio_base) {
        this.salaraio_base = salaraio_base;
    }

    public List<Double> getSalario_recebido() {
        return salario_recebido;
    }

    public void setSalario_recebido(List<Double> salario_recebido) {
        this.salario_recebido = salario_recebido;
    }

    public double CalcularMedia() {
        double soma = 0;
        for (Double d : salario_recebido) {
            soma += d;
        }

        return soma / salario_recebido.size();

    }

    public double calcularBonus() {
        return salaraio_base * 0.2;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
