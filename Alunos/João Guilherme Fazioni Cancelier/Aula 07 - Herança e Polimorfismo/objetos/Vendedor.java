package objetos;
import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Funcionario {
    private static int contador = 0;
    private int idFuncionario;

    
    
    public Vendedor(String nome, int idade, String cidade, String bairro, String rua, double salarioBase, Loja loja) {
       
        super(nome, idade, cidade, bairro, rua, salarioBase, loja);
        this.idFuncionario = ++contador;
    }

    @Override
    public void apresentarse() {
        super.apresentarse(); // Chama a apresentação base de funcionário
        System.out.println("ID Vendedor: " + idFuncionario);
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }
}