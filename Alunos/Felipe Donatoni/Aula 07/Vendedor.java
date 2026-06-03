import java.util.ArrayList;

public class Vendedor extends Pessoa{
    
    String Loja;
    double salarioBase;
    ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Vendedor (String nome, int idade, String Loja, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.Loja = Loja;
        this.salarioBase = salarioBase;

    }
}
