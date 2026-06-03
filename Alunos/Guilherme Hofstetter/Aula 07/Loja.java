import java.util.ArrayList;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;
    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Gerente> gerentes = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }

    public int contarClientes() { return clientes.size(); }
    public int contarVendedores() { return vendedores.size(); }
    public int contarGerentes() { return gerentes.size(); }

    public void adicionarVendedor(Vendedor v) { vendedores.add(v); }
    public void adicionarCliente(Cliente c) { clientes.add(c); }
    public void adicionarGerente(Gerente g) { gerentes.add(g); }

    public String getNomeFantasia() { return nomeFantasia; }
    public Endereco getEndereco() { return endereco; }
    public ArrayList<Vendedor> getVendedores() { return vendedores; }
    public ArrayList<Cliente> getClientes() { return clientes; }
    public ArrayList<Gerente> getGerentes() { return gerentes; }
}
