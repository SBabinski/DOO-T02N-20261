package Objetos;

import java.util.ArrayList;

public class Loja {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Cliente> clientes;
    private Endereco endereco;

    public Loja() {
        funcionarios = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        setNomeFantasia(nomeFantasia);
        setRazaoSocial(razaoSocial);
        setCnpj(cnpj);

        setEndereco(new Endereco(cidade, bairro, rua));
        funcionarios = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void adicionarFuncionario(Funcionario f) {
        if(!this.funcionarios.contains(f)) {
            this.funcionarios.add(f);
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

    public void contarFuncionarios() {
        System.out.println("Funcionarios: ");
        for(int i = 0; i < funcionarios.size();i++){
            System.out.printf(" %d - %s (%s)\n",
            i+1,
            funcionarios.get(i).getNome(), funcionarios.get(i).getTipo());
        } 
        System.out.printf("Total: %d\n", funcionarios.size());
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

    public void setEndereco(Endereco endereco) {
        if (endereco != null) {
            this.endereco = endereco;
        }
    }

    public void apresentarSe() {
        System.out.printf("Loja: %s, CNPJ: %s, Endereço: %s, %s, %s\n",
        nomeFantasia, cnpj, endereco.getCidade(), endereco.getBairro(), endereco.getRua());
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