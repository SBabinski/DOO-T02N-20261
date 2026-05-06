import java.util.Scanner;

public class Cliente {

    private String nome;
    private int cpf;
    private int cnh;


    public  Cliente() {

    }

    public static void cadastroCliente(Scanner scan){

        Cliente cliente = new Cliente();

        scan.nextLine();
        System.out.println("Digite seu nome: ");
        cliente.setNome(scan.nextLine());

        System.out.println("Digite seu cpf: ");
        cliente.setCpf(scan.nextInt());
        scan.nextLine();

        System.out.println("Digite seu cnh: ");
        cliente.setCnh(scan.nextInt());
        scan.nextLine();

        Lista.clientes.add(cliente);

    }





    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getCnh() {
        return cnh;
    }

    public void setCnh(int cnh) {
        this.cnh = cnh;
    }
}


