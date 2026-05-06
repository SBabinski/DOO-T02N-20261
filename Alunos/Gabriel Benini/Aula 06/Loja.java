package CalculadoraLoja;

import java.util.ArrayList;
import java.util.List;

public class Loja {

    private String nome;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;

    private List<Vendedor> vendedores;
    private List<Cliente> clientes;

    public Loja(String nome, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;

        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + " - " + cidade);
        System.out.println();
    }

    public void contarClientes() {
        System.out.println("Quantidade de clientes: " + clientes.size());
    }

    public void contarVendedores() {
        System.out.println("Quantidade de vendedores: " + vendedores.size());
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public String getNome() {
        return nome;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}