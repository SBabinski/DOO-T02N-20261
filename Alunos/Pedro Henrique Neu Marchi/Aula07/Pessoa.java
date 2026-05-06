public class Pessoa {

    String nome;
    int idade;
    Endereco endereco;

    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Endereço: " + endereco);
    }
}