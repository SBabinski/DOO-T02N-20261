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

    public Loja(String nomeFantasia, String razaoSocial, String cnpj,
            String cidade, String bairro, String rua) {

        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
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

    public void apresentarse() {
        System.out.println("Loja: " + this.nomeFantasia +
                " | CNPJ: " + this.cnpj +
                " | Endereço: " + this.cidade + ", " + this.bairro + ", " + this.rua);
    }
}