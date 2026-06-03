public class Cliente {
    private String nome;
    private String numCnh;
    private String cpf;

    public Cliente(String nome, String numCnh, String cpf) {
        this.nome = nome;
        this.numCnh = numCnh;
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public String getNumCnh() {
        return this.numCnh;
    }

    public String getCpf() {
        return this.cpf;
    }

}