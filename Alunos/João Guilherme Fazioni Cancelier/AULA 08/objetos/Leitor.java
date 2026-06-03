package objetos;

public class Leitor {
    private static int contador =0;
    private int id;
    private String nome;
    private String cpf;
    private String email;
    
    public Leitor(String nome, String cpf, String email) {
        this.id = ++contador;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

}
