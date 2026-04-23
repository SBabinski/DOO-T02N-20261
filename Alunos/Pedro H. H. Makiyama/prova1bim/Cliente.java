package prova1bim;

public class Cliente {

    private String nome;
    private int cpf;
    private int cnh;

    public Cliente(String nome, int cpf, int cnh){

        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
    }

    public String getNome() {
        return nome;
    }

    public int getCpf() {
        return cpf;
    }

    public int getCnh() {
        return cnh;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setCnh(int cnh) {
        this.cnh = cnh;
    }

    
}
