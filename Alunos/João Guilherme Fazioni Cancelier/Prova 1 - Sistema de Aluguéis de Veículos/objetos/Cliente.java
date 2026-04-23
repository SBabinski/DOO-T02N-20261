package objetos;

public class Cliente {
    private static int contador = 0;
    private int id;
    private String nome;
    private String cpf;
    private String cnh;
    public Cliente(String nome, String cpf, String cnh) {
        this.id=++contador;
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
    }
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCnh() {
        return cnh;
    }
    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public void exibir(){
        System.out.printf("ID: %d - Nome: %s - CPF: %s - CNH: %s.\n",id,nome,cpf,cnh);
    }
}
