package aula6;

import java.util.ArrayList;

public class Loja {

    private String nomefantasia;
    private String razsoc;
    private int cnpj;
    private String cidade;
    private String bairro;
    private String rua;

    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public Loja(String nomefantasia, String razsoc, int cnpj, String cidade, String bairro, String rua){

        this.nomefantasia = nomefantasia;
        this.razsoc = razsoc;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;

        Vendedor vendedor1 = new Vendedor("Vendedor 1", 30, "loja1",
        "Cidade Vendedor 1", "Bairro Vendedor 1", "Rua Vendedor 1", 1000.);
        Vendedor vendedor2 = new Vendedor("Vendedor 2", 35, "loja1",
        "Cidade Vendedor 2", "Bairro Vendedor 2", "Rua Vendedor 2", 1500.);
        vendedores.add(vendedor1);
        vendedores.add(vendedor2);

        Cliente cliente1 = new Cliente("Cliente 1", 20, "Cidade Cliente 1", "Bairro Cliente 1",
        "Rua Cliente 1");
        Cliente cliente2 = new Cliente("Cliente 2", 25, "Cidade Cliente 2", "Bairro Cliente 2",
        "Rua Cliente 2");
        clientes.add(cliente1);
        clientes.add(cliente2);
    }

    public int contarVendedores(){

        int cont = 0;

        for (Vendedor vended : vendedores){

            if (!vended.equals(null))

                cont++;
        }

        return cont;
    }

    public int contarClientes(){

        int cont = 0;

        for (Cliente client : clientes){

            if (!client.equals(null))

                cont++;
        }

        return cont;
    }

    public void apresentarVendedores(){

        for (Vendedor vended : vendedores){

            if (!vended.equals(null))

                vended.apresentarse();
        }
    }
    
    public void apresentarClientes(){

        for (Cliente client : clientes){

            if (!client.equals(null))

                client.apresentarse();
        }
    }

    public void apresentarMediasSalarios(){

        for (Vendedor vended : vendedores){

            if (!vended.equals(null))

                 System.out.println("\nMédia dos salários do vendedor " + vended.getNome() + ": " + vended.calcularMedia());
        }
    }
    
    public void apresentarBonus(){

        for (Vendedor vended : vendedores){
                            
            if (!vended.equals(null))
                
                vended.calcularBonus();
            }
    }

    public void cadastrarVendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salariobase){

        Vendedor vendedor = new Vendedor(nome, idade, loja, cidade, bairro, rua, salariobase);

        vendedores.add(vendedor);
    }

    public void cadastrarCliente(String nome, int idade, String cidade, String bairro, String rua){

        Cliente cliente = new Cliente(nome, idade, cidade, bairro, rua);

        clientes.add(cliente);
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getRazsoc() {
        return razsoc;
    }

    public void setRazsoc(String razsoc) {
        this.razsoc = razsoc;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
}
