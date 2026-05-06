import java.util.ArrayList;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
    }

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    public void adicionarVendedor(Vendedor v) { vendedores.add(v); }
    public void adicionarCliente(Cliente c) { clientes.add(c); }

    public String getNomeFantasia() { return nomeFantasia; }
    public ArrayList<Vendedor> getVendedores() { return vendedores; }
    public ArrayList<Cliente> getClientes() { return clientes; }
}
