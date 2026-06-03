public class Cliente extends Pessoa {

    private String cpf;
    private Loja loja;

    public Cliente(String nome, int idade, Endereco endereco, String cpf, Loja loja) {
        super(nome, idade, endereco);
        this.cpf = cpf;
        this.loja = loja;
    }

    public Cliente(String nome, int idade, String cpf, Endereco endereco) {
        super(nome, idade, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public void apresentarse() {
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("CPF: " + getCpf());
    }
}
