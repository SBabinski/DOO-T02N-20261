public class Cliente {
    private String nome;
    private String cpf;
    private String numeroCnh;

    public Cliente(String nome, String cpf, String numeroCnh) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroCnh = numeroCnh;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumeroCnh() {
        return numeroCnh;
    }

    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("CNH: " + numeroCnh);
    }
}