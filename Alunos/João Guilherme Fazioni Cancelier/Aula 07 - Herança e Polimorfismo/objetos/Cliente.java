package objetos;

public class Cliente extends Pessoa {
    private static int contador = 0;
	private int idCliente;
    
    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        this.idCliente = ++contador;
        this.setNome(nome);
        this.setIdade(idade);
        Endereco end = new Endereco();

        end.setCidade(cidade);
        end.setBairro(bairro);
        end.setRua(rua);

        this.setEndereco(end);
    ;
    }

    public int getIdCliente() { return idCliente; }

    

    public void apresentarse() {
        System.out.println("Nome do Cliente: " + getNome() );
        System.out.println("Idade: " + getIdade());
    }
}

