public class Cliente extends Pessoa {

    public Cliente(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
    }

    public void apresentarSe() {
        System.out.printf("Cliente: %s, %d anos%n", nome, idade);
        endereco.apresentarLogradouro();
    }
}