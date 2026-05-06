package Objetos;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    
    String nome;
    String cpf;
    String cnh;

    public Cliente(String nome, String cpf, String cnh) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
    }

    List <Cliente> clientes = new ArrayList<>();

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

    public void AddCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public void apresentarCliente(){
        System.out.println("Nome: "+getNome());
        System.out.println("CPF "+getCpf());
    }

}
