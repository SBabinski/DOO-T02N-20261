public class Cliente {

    String nome;
    int idade;
    Endereco endereco;

    void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}