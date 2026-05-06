import java.util.ArrayList;

public abstract class Funcionario extends Pessoa {
    private double salarioBase;
    private double salarioRecebido;
    public Lojas loja;
    private final ArrayList<Double> salarios = new ArrayList<>();

    public Funcionario() {

    }

    public Funcionario(String nome, int idade, Lojas loja, Double salarioBase, String cidade, String bairro, String rua){
        super(nome, idade, cidade, bairro, rua);
        setLoja(loja);
        setSalarioBase(salarioBase);
    }

    public abstract double getBonus();

    public String getLoja() {
    return loja.getNomeFantasia();
    }

    public final void setLoja(Lojas loja){
        this.loja = loja;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public final void setSalarioBase(double salarioBase) {
        if (salarioBase > 0) {
            this.salarioBase = salarioBase;
        }
    }

    public double getSalarioRecebido() {
        return salarioRecebido;
    }

    public void receberSalario(double salarioRecebido) {
        if (salarioRecebido >= salarioBase){
            this.salarioRecebido = salarioRecebido;
            salarios.add(salarioRecebido);
        }
    }

    public double calcularMediaSalarial() {
        int contador=0;
        double soma = 0;
        for (int i=0; i<salarios.size(); i++){
            soma += salarios.get(i);
            contador += 1;
        }
        double media = soma / contador;
        return media;
    }

    public double calcularBonusSalarial() {
        double bonusS = this.salarioBase * this.getBonus();
        return bonusS;
    }
    
}