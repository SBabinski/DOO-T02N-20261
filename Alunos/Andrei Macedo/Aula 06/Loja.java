import java.util.ArrayList;
import java.util.List;

public class Loja {

    private String nome;
    private String Razao_social;
    private String cnpj;
    private String endereco;
    private final List<Cliente> clientes = new ArrayList<>(); 
    private final List<Vendedor> vendedores = new ArrayList<>();

    public Loja(String nome, String razao_social, String cnpj, String endereco) {
        this.nome = nome;
        Razao_social = razao_social;
        this.cnpj = cnpj;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
        System.out.println(" === LISTA DE CLIENTES === ");
        for (Cliente c : clientes) {
            System.out.println(c);
        }

        System.out.println("\n");

        System.out.println(" === LISTA DE VENDEDORES === ");
        for (Vendedor v : vendedores) {
            System.out.println(v);
            
        }

        System.err.println("\n");
    }
}
