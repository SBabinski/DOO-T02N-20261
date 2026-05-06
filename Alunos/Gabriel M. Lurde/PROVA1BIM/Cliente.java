public class Cliente {

    String nome;
    String cpf;
    String cnh;

    public Cliente(String nome, String cpf, String cnh) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
    }

    void apresentar() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("CNH: " + cnh);
    }
}