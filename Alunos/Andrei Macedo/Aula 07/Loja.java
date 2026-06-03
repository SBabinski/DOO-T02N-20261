import java.util.ArrayList;
import java.util.List;

public class Loja {

    private String nome;
    private String Razao_social;
    private String cnpj;
    private Endereco endereco;
    private final List<Cliente> clientes = new ArrayList<>(); 
    private final List<Vendedor> vendedores = new ArrayList<>();

    public Loja(String nome, String razao_social, String cnpj, Endereco endereco) {
        this.nome = nome;
        Razao_social = razao_social;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public Loja() {
        //TODO Auto-generated constructor stub
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazao_social() {
        return Razao_social;
    }

    public void setRazao_social(String razao_social) {
        Razao_social = razao_social;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void salvar_vendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
        System.out.println(" === Vendedor salvo com sucesso. === ");
    }

    public void salvar_cliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println(" === Cliente salvo com sucesso. === ");
    }

    public void apresentarse() {
        System.out.println("Existem " + clientes.size() + " clientes cadastrados.");
        System.out.println("Existem " + vendedores.size() + " funcionarios cadastrados.");
        System.out.println(" === LISTA DE CLIENTES === ");
        int xCliente = 0;
        for (Cliente c : clientes) {
            xCliente++;
            System.out.println(xCliente);
            c.apresentarse();
            
        }

        System.out.println("\n");

        int xVendedores = 0;
        System.out.println(" === LISTA DE VENDEDORES === ");
        for (Vendedor v : vendedores) {
            xVendedores++;
            System.out.println(xVendedores);
            v.apresentarse();
            
        }

        System.out.println("\n");
    }

    public Cliente buscarCliente(int indice) {
        int indiceReal = indice - 1;

        if (indiceReal >= 0 && indiceReal < clientes.size()) {
            return clientes.get(indiceReal);
        } else {
        System.out.println("Erro: Índice inválido!");
            return null;
        }
    }

    public Vendedor buscarVendedor(int indice) {
        int indiceReal = indice - 1;

        if (indiceReal >= 0 && indiceReal < clientes.size()) {
            return vendedores.get(indiceReal);
        } else {
        System.out.println("Erro: Índice inválido!");
            return null;
        }
    }

}
