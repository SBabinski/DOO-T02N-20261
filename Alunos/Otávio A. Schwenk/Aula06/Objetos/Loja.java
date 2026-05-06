package Objetos;

import java.util.ArrayList;

public class Loja {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Cliente> clientes;

    public Loja() {
        vendedores = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        setNomeFantasia(nomeFantasia);
        setRazaoSocial(razaoSocial);
        setCnpj(cnpj);
        setCidade(cidade);
        setBairro(bairro);
        setRua(rua);
        vendedores = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void adicionarVendedor(Vendedor v) {
        if(!this.vendedores.contains(v)) {
            this.vendedores.add(v);
        }
    }

    public void adicionarCliente(Cliente c) {
        if(!this.clientes.contains(c)) {
            this.clientes.add(c);
        }
    }

    public void contarClientes() {
        System.out.println("Clientes: ");
        for(int i = 0; i <clientes.size();i++){
            System.out.printf(" %d - %s\n", i+1, clientes.get(i).getNome());
        } clientes.size();
        System.out.printf("Total: %d\n", clientes.size());
    }

    public void contarVendedores() {
        System.out.println("Vendedores: ");
        for(int i = 0; i <vendedores.size();i++){
            System.out.printf(" %d - %s\n", i+1, vendedores.get(i).getNome());
        } 
        System.out.printf("Total: %d\n", vendedores.size());
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setNomeFantasia(String nomeFantasia) {
        if(nomeFantasia != null && !nomeFantasia.isBlank()) {
            this.nomeFantasia = nomeFantasia;
        }
    }

    public void setRazaoSocial(String razaoSocial) {
        if(razaoSocial != null && !razaoSocial.isBlank()) {
            this.razaoSocial = razaoSocial;
        }
    }

    public void setCnpj(String cnpj) {
        if(cnpj != null && !cnpj.isBlank()) {
            this.cnpj = cnpj;
        }
    }

    public void setCidade(String cidade) {
        if(cidade != null && !cidade.isBlank()) {
            this.cidade = cidade;
        }
    }

    public void setBairro(String bairro) {
        if(bairro != null && !bairro.isBlank()) {
            this.bairro = bairro;
        }
    }

    public void setRua(String rua) {
        if(rua != null && !rua.isBlank()) {
            this.rua = rua;
        }
    }

    public void apresentarSe() {
        System.out.printf("Loja: %s, CNPJ: %s, Endereço: %s, %s, %s\n",
        nomeFantasia, cnpj, cidade, bairro, rua);
    }

    @Override
    public String toString() {
        return "Loja [nomeFantasia=" + nomeFantasia + 
        ", razaoSocial=" + razaoSocial + 
        ", cnpj=" + cnpj + 
        ", cidade=" + cidade + 
        ", bairro=" + bairro + 
        ", rua=" + rua + 
        "]";
    }
}