public class Clientes {
    private String nome;
    private String cpf;
    private String numCnh;

    public Clientes(String nome, String cpf, String numCnh) {
        this.nome = nome;
        this.cpf = cpf;
        this.numCnh = numCnh;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumCnh() {
        return numCnh;
    }
}