package Objetos;

public class Pessoa {

    protected String nome;
    protected int idade;
    protected Endereco endereco;

    public Pessoa(){
    }

    public Pessoa(String nome, int idade, String cidade, String bairro, String rua){
        setNome(nome);
        setIdade(idade);

        setEndereco(new Endereco(cidade, bairro, rua));
    }

    public Pessoa(String nome, int idade, String estado,
                  String cidade, String bairro, String rua,
                  int numero, String complemento){
        setNome(nome);
        setIdade(idade);

        setEndereco(new Endereco(estado, cidade, bairro, rua, numero, complemento));
    }

    public String getNome() {
	    return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setNome(String nome){
        if(nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
    }

    public void setIdade(int idade){
        if(idade >= 0) {
            this.idade = idade;
        }
    }

    public void setEndereco(Endereco endereco) {
        if (endereco != null) {
            this.endereco = endereco;
        }
    }
}
