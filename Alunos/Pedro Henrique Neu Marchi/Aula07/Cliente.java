public class Cliente extends Pessoa {

    @Override
    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        if (endereco != null) {
            endereco.apresentarLogradouro();
        }
    }
}
