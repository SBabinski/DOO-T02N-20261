public class Cliente {

    String nome;
    int    idade;
    String cidade;
    String bairro;
    String rua;

    public Cliente(String nome, int idade,
                   String cidade, String bairro, String rua) {
        this.nome   = nome;
        this.idade  = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua    = rua;
    }

    // printa nome e idade
    public void apresentarse() {
        System.out.println("\nCliente");
        System.out.println("Nome  : " + nome);
        System.out.println("Idade : " + idade + " anos");
    }
}
