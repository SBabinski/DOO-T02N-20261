package objetos;
import java.util.ArrayList;
import java.util.List;


public class Funcionario extends Pessoa  {
    private double salarioBase;
    private List<Double> salarioRecebido;
    private Loja loja;

    public Funcionario(String nome, int idade, String cidade, String bairro, String rua, double salarioBase, Loja loja) {
        
        super(nome, idade, cidade, bairro, rua); 
        
        this.salarioBase = salarioBase;
        this.loja = loja;
        this.salarioRecebido = new ArrayList<>();

        this.salarioRecebido.add(1500.0);
        this.salarioRecebido.add(1800.0);
        this.salarioRecebido.add(2100.0);
    }
      public void apresentarse() {
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Loja: " + (loja != null ? loja.getNomeFantasia() : "Nenhuma"));
    }
        public double getSalarioBase() {
        return salarioBase;
    }
    public double calcularBonus() {
        return salarioBase * 0.2;
    }


    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public List<Double> getSalarioRecebido() {
        return salarioRecebido;
    }

     public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    public void setSalarioRecebido(List<Double> salarioRecebido) {
        this.salarioRecebido = salarioRecebido;
    }
}
