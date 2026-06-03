import java.util.List;

public class Gerente extends Vendedor {

    public Gerente(double salaraio_base, String nome, int idade, Endereco endereco, List<Double> salario_recebido,
    Loja loja) {
        super(salaraio_base, nome, idade, endereco, salario_recebido, loja);
        salario_recebido.set(0, 2.300);
        salario_recebido.set(1, 2.300);
        salario_recebido.set(2, 2.300);
    }

    @Override
    public double calcularBonus() {
        // TODO Auto-generated method stub
        return getSalaraio_base() * 0.35; 
    }
}
