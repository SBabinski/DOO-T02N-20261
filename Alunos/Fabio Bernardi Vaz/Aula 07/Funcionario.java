package fag;
import java.util.ArrayList;
import java.util.List;

public abstract class Funcionario extends Pessoa {
    protected Loja loja;
    protected double salarioBase;
    protected List<Double> salarioRecebido = new ArrayList<>();

    public Funcionario(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        
        this.salarioRecebido.add(1500.00);
        this.salarioRecebido.add(1650.50);
        this.salarioRecebido.add(1550.00);
    }

    @Override
    public void apresentarse() {
        String nomeDaLoja = (loja != null) ? loja.getNomeFantasia() : "Sem Loja Definida";
        System.out.println("Nome: " + nome + " | Idade: " + idade + " anos | Loja: " + nomeDaLoja);
    }

    public double calcularMedia() {
        if (salarioRecebido.isEmpty()) return 0;
        double soma = 0;
        for (double salario : salarioRecebido) { soma += salario; }
        return soma / salarioRecebido.size();
    }

    public abstract double calcularBonus();
    
    public Loja getLoja() { return loja; }
}