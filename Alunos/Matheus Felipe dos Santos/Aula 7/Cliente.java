public class Cliente extends Pessoa {

    public Cliente(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
    }

    @Override
    void apresentar() {
        super.apresentar();
        endereco.apresentarLogradouro();
    }
}