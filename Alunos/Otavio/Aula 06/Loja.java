public class Loja {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private Vendedor[] vendedores;
    private Cliente[] clientes;
    private int totalVendedores;
    private int totalClientes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj,
                String cidade, String bairro, String rua, int capacidadeVendedores, int capacidadeClientes) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores = new Vendedor[capacidadeVendedores];
        this.clientes = new Cliente[capacidadeClientes];
        this.totalVendedores = 0;
        this.totalClientes = 0;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void adicionarVendedor(Vendedor vendedor) {
        if (totalVendedores < vendedores.length) {
            vendedores[totalVendedores] = vendedor;
            totalVendedores++;
        } else {
            System.out.println("Capacidade maxima de vendedores atingida.");
        }
    }

    public void adicionarCliente(Cliente cliente) {
        if (totalClientes < clientes.length) {
            clientes[totalClientes] = cliente;
            totalClientes++;
        } else {
            System.out.println("Capacidade maxima de clientes atingida.");
        }
    }

    public int contarClientes() {
        return totalClientes;
    }

    public int contarVendedores() {
        return totalVendedores;
    }

    public void Apresentar() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereco: " + rua + ", " + bairro + ", " + cidade);
    }
}
