package objetos;

public class Cliente extends Pessoa {
    private static int contador = 0;
	private int idCliente;
    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        super(nome, idade, cidade, bairro, rua);
        this.idCliente = ++contador;    
    }
    public int getIdCliente() { 
        return idCliente; 
    }

    public void apresentarse() {
        System.out.println("Nome do Cliente: " + getNome() );
        System.out.println("Idade: " + getIdade());
    }
}

