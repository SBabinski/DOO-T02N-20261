package objetos;

import java.util.ArrayList;

import fag.Floricultura;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Enderecos enderecos;
    public ArrayList<Vendedor> vendedores = new ArrayList<>();
    public ArrayList<Cliente> clientes = new ArrayList<>();

    public Loja() {

    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        if ((nomeFantasia != null) && (!nomeFantasia.isBlank())){
            this.nomeFantasia = nomeFantasia;
        }
        else {
            System.out.println("Digite um nome válido:");
            String novoNomeFantasia = Floricultura.scan.nextLine();
            setNomeFantasia(novoNomeFantasia);
        }
    }

    
   public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        if ((razaoSocial != null) && (!razaoSocial.isBlank())){
            this.razaoSocial = razaoSocial;
        }
        else {
            System.out.println("Digite um nome válido:");
            String novoRazaoSocial = Floricultura.scan.nextLine();
            setRazaoSocial(novoRazaoSocial);
        }
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
    }
    
    public void setEnderecos(Enderecos enderecos) {
    	this.enderecos = enderecos; 
    	}
   
    public void contarClientes() {
        int quantidadeClientes = clientes.size();
        System.out.println("A loja "+this.nomeFantasia+" tem "+quantidadeClientes+" clientes");
    }

    public void contarVendedores() {
        int quantidadeVendedores = vendedores.size();
        System.out.println("A loja "+this.nomeFantasia+" tem "+quantidadeVendedores+" vendedores");
    }

    public void apresentarse() {
        System.out.println("Nome: " + this.nomeFantasia + " | CNPJ: " + this.cnpj);

        if (enderecos != null) {
            enderecos.apresentarLogradouro();
        } else {
            System.out.println("Endereço não cadastrado");
        }
    }
}
