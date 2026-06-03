public class Cliente {

    private String nome;
    private String cpf;
    private String numeroCNH;

    public Cliente(String nome, String cpf, String numeroCNH) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroCNH = numeroCNH;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumeroCNH() {
        return numeroCNH;
    }

    public void exibirInfo() {
        System.out.println("Cliente: " + nome
                + " | CPF: " + cpf
                + " | CNH: " + numeroCNH);
    }
}
