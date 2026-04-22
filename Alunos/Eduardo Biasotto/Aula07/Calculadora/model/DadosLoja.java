package Calculadora.model;

import java.util.ArrayList;

public class DadosLoja {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private DadosEndereco endereco;

    private ArrayList<DadosGerente> gerentes = new ArrayList<>();
    private ArrayList<DadosVendedor> vendedores = new ArrayList<>();
    private ArrayList<DadosCliente> clientes = new ArrayList<>();
    private ArrayList<DadosItem> itens = new ArrayList<>();

    public DadosLoja(String nomeFantasia, String razaoSocial, String cnpj, DadosEndereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public void apresentarse() {
        System.out.println("\n======= DADOS DA LOJA - " + nomeFantasia + " =======");
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();

        System.out.println("\nTotal de clientes: " + contarClientes());

        System.out.println("\n===== GERENTES =====");        

        System.out.println("\nTotal de gerentes: " + contarGerentes());

        if (gerentes.isEmpty()) {
            System.out.println("\nNenhum gerente cadastrado nesta loja.");
        } else {
            System.out.println("\nGerentes:");
            for (DadosGerente g : gerentes) {
                System.out.println("- " + g.getNome());
            }
        }

        System.out.println("\n===== FUNCIONARIOS =====");

        System.out.println("\nTotal de vendedores: " + contarVendedores());

        if (vendedores.isEmpty()) {
            System.out.println("\nNenhum vendedor cadastrado nesta loja.");
        } else {
            System.out.println("\nVendedores:");
            for (DadosVendedor v : vendedores) {
                System.out.println("- " + v.getNome());
            }
        }

        System.out.println("\n===== PRODUTOS =====");

        System.out.println("\nTotal de produtos: " + contarItens());

        if (itens.isEmpty()) {
            System.out.println("\nNenhum produto cadastrado nesta loja.");
        } else {
            System.out.println("\nProdutos:");
            for (DadosItem i : itens) {
                System.out.println("- " + i.getNome() + " | R$ " + i.getValor());
            }
        }


    }

    public int contarClientes() {
        return clientes.size();
    }

    public int contarGerentes(){
        return gerentes.size();
    }
    
    public int contarVendedores() {
        return vendedores.size();
    }

    public int contarItens() {
        return itens.size();
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public ArrayList<DadosGerente> getGerentes() {
        return gerentes;
    }

    public ArrayList<DadosVendedor> getVendedores() {
        return vendedores;
    }

    public ArrayList<DadosCliente> getClientes() {
        return clientes;
    }

    public ArrayList<DadosItem> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return nomeFantasia;
    }
}