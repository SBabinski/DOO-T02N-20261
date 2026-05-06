public class Cliente {

    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;

    public void apresentarSe() {
        System.out.println("Nome da cliente: " + nome);
        System.out.println("Idade da cliente: " + idade);
    }
}