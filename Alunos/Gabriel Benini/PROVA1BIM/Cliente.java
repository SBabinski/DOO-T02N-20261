import java.util.Scanner;

public class Cliente {

    static Scanner scan = new Scanner(System.in);

    private String nome;
    private String cpf;
    private String cnh;

    public Cliente() {

    }

    public Cliente(String nome, String cpf, String cnh) {

        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;

    }

    public void cadastrarCliente() {

        System.out.println("Insira o nome do cliente: ");
        setNome(scan.next());
        System.out.println("Insira o cpf do cliente: ");
        setCpf(scan.next());
        System.out.println("Insira o cnh do cliente: ");
        setCnh(scan.next());
        System.out.println("Cadastrado com sucesso!");
        System.out.println();

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
}