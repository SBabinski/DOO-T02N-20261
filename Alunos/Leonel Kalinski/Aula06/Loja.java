package objetos;

import java.util.ArrayList;

import fag.Floricultura;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
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
        if ((cnpj != null) && (!cnpj.isBlank()) && (cnpj.length() == 14)){
            this.cnpj = cnpj;
        }
        else {
            System.out.println("Digite um cnpj válido:");
            String novaCnpj = Floricultura.scan.nextLine();
            setCnpj(novaCnpj);
        }
    }
    

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if ((cidade != null) && (!cidade.isBlank())){
            this.cidade = cidade;
        }
        else {
            System.out.println("Digite uma cidade válida:");
            String novoCidade = Floricultura.scan.nextLine();
            setCidade(novoCidade);
        }
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if ((bairro != null) && (!bairro.isBlank())){
            this.bairro = bairro;
        }
        else {
            System.out.println("Digite um bairro válido:");
            String novoBairro = Floricultura.scan.nextLine();
            setBairro(novoBairro);
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        if ((rua != null) && (!rua.isBlank())){
            this.rua = rua;
        }
        else {
            System.out.println("Digite uma rua válida:");
            String novoRua = Floricultura.scan.nextLine();
            setRua(novoRua);
        }
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
        String endereço = "Cidade: " + this.cidade + " | Bairro: " + this.bairro + " | Rua: " + this.rua;
        System.out.println("Nome: "+ this.nomeFantasia + " | CNPJ: " + this.cnpj + " | "+ endereço);
    }
}
