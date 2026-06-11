package aula7;

import java.util.ArrayList;

public class Loja {

    private String nomefantasia;
    private String razsoc;
    private int cnpj;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;
    private Endereco endereco;
    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<Gerente> gerentes = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public Loja(String nomefantasia, String razsoc, int cnpj, String cidade, String bairro, String rua, String estado,
                int numero, String complemento){

        this.nomefantasia = nomefantasia;
        this.razsoc = razsoc;
        this.cnpj = cnpj;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;

        endereco = new Endereco(estado, cidade, bairro, rua, numero, complemento);

        Vendedor vendedor1 = new Vendedor("Vendedor 1", 30, "loja1", "Estado Vendedor 1",
        "Cidade Vendedor 1", "Bairro Vendedor 1", "Rua Vendedor 1", 430, "Casa Vendedor 1", 1000.);
        Vendedor vendedor2 = new Vendedor("Vendedor 2", 35, "loja1", "Estado Vendedor 2", 
        "Cidade Vendedor 2", "Bairro Vendedor 2", "Rua Vendedor 2", 430, "Casa Vendedor 2", 1500.);
        vendedores.add(vendedor1);
        vendedores.add(vendedor2);

        Cliente cliente1 = new Cliente("Cliente 1", 20, "Estado Cliente 1", "Cidade Cliente 1",
        "Bairro Cliente 1", "Rua Cliente 1", 430, "Casa Cliente 1");
        Cliente cliente2 = new Cliente("Cliente 2", 25, "Cidade Cliente 2", "Estado Cliente 2",
        "Bairro Cliente 2", "Rua Cliente 2", 430, "Casa Cliente 2");
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

    public int contarGerentes(){

        int cont = 0;

        for (Gerente gerent : gerentes){

            if (!gerent.equals(null))

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
    
    public void apresentarGerentes(){

        for (Gerente gerent : gerentes){

            if (!gerent.equals(null))

                gerent.apresentarse();
        }
    }

    public void apresentarClientes(){

        for (Cliente client : clientes){

            if (!client.equals(null))

                client.apresentarse();
        }
    }

    public void apresentarMediasSalariosVendedores(){

        for (Vendedor vended : vendedores){

            if (!vended.equals(null))

                 System.out.println("\nMédia dos salários do vendedor " + vended.getNome() + ": " + vended.calcularMedia());
        }
    }
    
    public void apresentarBonusVendedores(){

        for (Vendedor vended : vendedores){
                            
            if (!vended.equals(null))
                
                System.out.println("\nBônus do vendedor " + vended.getNome() + ": " + vended.calcularBonus());
            }
    }

    public void apresentarMediasSalariosGerentes(){

        for (Gerente gerent : gerentes){

            if (!gerent.equals(null))

                System.out.println("\nMédia dos salários do gerente " + gerent.getNome() + ": " + gerent.calcularMedia());
        }
    }
    
    public void apresentarBonusGerentes(){

        for (Gerente gerent : gerentes){

            if (!gerent.equals(null))

                System.out.println("\nBônus do gerente " + gerent.getNome() + ": " + gerent.calcularBonus());
        }
    }

    public void cadastrarVendedor(String nome, int idade, String loja, String estado, String cidade, String bairro,
                                  String rua, int numero, String complemento, double salariobase){

        Vendedor vendedor = new Vendedor(nome, idade, loja, estado, cidade, bairro, rua, numero, complemento, salariobase);

        vendedores.add(vendedor);
    }

    public void cadastrarCliente(String nome, int idade, String estado, String cidade, String bairro, String rua, int numero,
                                 String complemento){

        Cliente cliente = new Cliente(nome, idade, estado, cidade, bairro, rua, numero, complemento);

        clientes.add(cliente);
    }

    public void cadastrarGerente(String nome, int idade, String loja, String estado, String cidade, String bairro, String rua, int numero, String complemento){

        Gerente gerente = new Gerente(nome, idade, loja, estado, cidade, bairro, rua, numero, complemento, numero);

        gerentes.add(gerente);
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
        endereco.setCidade(cidade);
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
        endereco.setBairro(bairro);
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
        endereco.setRua(rua);
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        endereco.setEstado(estado);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        endereco.setNumero(numero);
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
        endereco.setComplemento(complemento);
    }

    public ArrayList<Gerente> getGerentes() {
        return gerentes;
    }
}
