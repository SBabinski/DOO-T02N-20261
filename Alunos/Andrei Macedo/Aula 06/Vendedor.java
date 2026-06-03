import java.util.ArrayList;
import java.util.List;

public class Vendedor {

    private String nome;
    private int idade;
    private String loja;
    private String endereco;
    private double salaraio_base;
    private List<Double> salario_recebido = new ArrayList<>(List.of(1.560, 1.621, 1.550));


    public Vendedor(String nome, int idade, String loja, String endereco, double salaraio_base) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.endereco = endereco;
        this.salaraio_base = salaraio_base;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    @Override
    public String toString() {
        return "Nome = " + nome + ", Idade = " + idade + ", Loja = " + loja;
    }
}
