import java.util.ArrayList;

public class Loja {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;

    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Gerente> gerentes = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj,
            Endereco endereco) {

        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public void adicionarVendedor(Vendedor v) {
        vendedores.add(v);
    }

    public void adicionarCliente(Cliente c) {
        clientes.add(c);
    }

    public void contarClientes() {
        System.out.println("Total de clientes: " + clientes.size());
    }

    public void contarVendedores() {
        System.out.println("Total de vendedores: " + vendedores.size());
    }

    public void adicionarGerente(Cliente g) {
        gerentes.add(g);
    }

    public void contarGerentes() {
        System.out.println("Total de gerentes: " + gerentes.size());
    }

    public void apresentarse() {
        System.out.println("Loja: " + this.nomeFantasia +
                " | CNPJ: " + this.cnpj);

        endereco.apresentarLogradouro();
    }
}