package Calculadora.model;

import java.util.ArrayList;

public class Loja {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;

    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua, int numero) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    // 🔹 APRESENTAR LOJA COMPLETA
    public void apresentarse() {
        System.out.println("\n======= DADOS DA LOJA - " + nomeFantasia + " =======");
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + cidade + ", " + bairro + ", " + rua + ", " + numero);

        // CLIENTES
        System.out.println("\nTotal de clientes: " + contarClientes());

        // VENDEDORES
        System.out.println("Total de vendedores: " + contarVendedores());

        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado nesta loja.");
        } else {
            System.out.println("\nVendedores:");
            for (Vendedor v : vendedores) {
                System.out.println("- " + v.getNome());
            }
        }
    }

    // 🔹 CONTADORES
    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    // 🔹 GETTERS
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    @Override
    public String toString() {
        return nomeFantasia;
    }
}