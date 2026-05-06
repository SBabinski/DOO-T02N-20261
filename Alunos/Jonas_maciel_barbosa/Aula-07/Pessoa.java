public class Pessoa {
    String nome;
    int idade;
    Endereco endereco;

    public Pessoa(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        if (idade > 0 && idade < 100) {
            this.idade = idade;
        } else {
            System.out.println("Idade inválida. Verifique e tente novamente.");
        }
        this.endereco = endereco;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        endereco.apresentarLogradouro();
    }
}
