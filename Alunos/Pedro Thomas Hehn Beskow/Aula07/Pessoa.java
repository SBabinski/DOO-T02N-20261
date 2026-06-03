// Superclasse que centraliza os atributos comuns a qualquer pessoa
// do sistema (cliente, vendedor, gerente).
public abstract class Pessoa {

    String   nome;
    int      idade;
    Endereco endereco;

    public Pessoa(String nome, int idade, Endereco endereco) {
        this.nome     = nome;
        this.idade    = idade;
        this.endereco = endereco;
    }

    // cada subclasse implementa sua própria apresentação
    public abstract void apresentarse();
}
