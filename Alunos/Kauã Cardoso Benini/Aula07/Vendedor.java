import java.util.ArrayList;

public class Vendedor extends Pessoa {

    String loja;
    double salarioBase;
    ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Vendedor(String nome, int idade, Endereco endereco, String loja, double salarioBase){
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

        salarioRecebido.add(2000.0);
        salarioRecebido.add(2100.0);
        salarioRecebido.add(2200.0);
    }

    @Override
    public void apresentarSe(){
        super.apresentarSe();
        System.out.println("Loja: " + loja);
    }

    public double calcularMedia(){
        double soma = 0;
        for(double s : salarioRecebido){
            soma += s;
        }
        return soma / salarioRecebido.size();
    }

    public double calcularBonus(){
        return salarioBase * 0.2;
    }
}