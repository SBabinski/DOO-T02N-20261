package Objetos;

import java.util.Scanner;

public class Cliente {
 
    static Scanner scan = new Scanner(System.in);

    private String nome;
    private String cpf;
    private String cnh;

    public Cliente() {

    }

    public Cliente(String nome, String cpf, String cnh) {
        setNome(nome);
        setCpf(cpf);
        setCnh(cnh);
    }

    public void setNome(String nome) {
        
        if (nome == null || nome.isBlank()) {
            System.out.println("Valor invalido");
            System.out.printf("Informe o nome do cliente: ");
            nome = scan.nextLine();
        }

        this.nome = nome;
    }

    public void setCpf(String cpf) {

        if (cpf == null || cpf.isBlank()) {
            System.out.println("Valor invalido");
            System.out.printf("Informe o CPF do cliente: ");
            cpf = scan.nextLine();
        }

        this.cpf = cpf;
    }

    public void setCnh(String cnh) {

        if (cnh == null || cnh.isBlank()) {
            System.out.println("Valor invalido");
            System.out.printf("Informe a CNH do cliente: ");
            cnh = scan.nextLine();
        }

        this.cnh = cnh;
    }


    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnh() {
        return cnh;
    }


    public void infoCliente() {
        System.out.println("=================================");
        System.out.printf("Nome: %s \n", nome);
        System.out.printf("CPF: %s \n", cpf);
        System.out.printf("CNH: %s \n", cnh);
    }


}
