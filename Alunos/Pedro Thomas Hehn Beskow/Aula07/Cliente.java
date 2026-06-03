// Cliente herda nome, idade e endereco de Pessoa.
public class Cliente extends Pessoa {

    public Cliente(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
    }

    @Override
    public void apresentarse() {
        System.out.println("\nCliente");
        System.out.println("Nome  : " + nome);
        System.out.println("Idade : " + idade + " anos");
        endereco.apresentarLogradouro();
    }
}
