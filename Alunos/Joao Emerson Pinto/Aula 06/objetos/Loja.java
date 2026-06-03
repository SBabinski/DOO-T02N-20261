package objetos;

public class Loja {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private Vendedor[] vendedores;
    private Cliente[] clientes;

    public Loja(
        String nomeFantasia,
        String razaoSocial,
        String cnpj,
        String cidade,
        String bairro,
        String rua,
        Vendedor[] vendedores,
        Cliente[] clientes
    ) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores = vendedores != null ? vendedores : new Vendedor[0];
        this.clientes = clientes != null ? clientes : new Cliente[0];
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

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public Vendedor[] getVendedores() {
        return vendedores;
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public int contarClientes() {
        return clientes.length;
    }

    public int contarVendedores() {
        return vendedores.length;
    }

    public void adicionarCliente(Cliente cliente) {
        Cliente[] novosClientes = new Cliente[clientes.length + 1];

        for (int i = 0; i < clientes.length; i++) {
            novosClientes[i] = clientes[i];
        }

        novosClientes[clientes.length] = cliente;
        clientes = novosClientes;
    }

    public void adicionarVendedor(Vendedor vendedor) {
        Vendedor[] novosVendedores = new Vendedor[vendedores.length + 1];

        for (int i = 0; i < vendedores.length; i++) {
            novosVendedores[i] = vendedores[i];
        }

        novosVendedores[vendedores.length] = vendedor;
        vendedores = novosVendedores;
    }

    public void apresentarse() {
        System.out.println("Loja: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereco: " + rua + ", " + bairro + ", " + cidade);
    }
}
