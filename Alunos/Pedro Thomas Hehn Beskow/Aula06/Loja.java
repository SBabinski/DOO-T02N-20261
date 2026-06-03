import java.util.ArrayList;

public class Loja {

    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;

    // quem trabalha e quem compra na loja
    ArrayList<Vendedor> vendedores = new ArrayList<>();
    ArrayList<Cliente>  clientes   = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj,
                String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial  = razaoSocial;
        this.cnpj         = cnpj;
        this.cidade       = cidade;
        this.bairro       = bairro;
        this.rua          = rua;
    }

    public void adicionarVendedor(Vendedor v) { vendedores.add(v); }
    public void adicionarCliente(Cliente c)   { clientes.add(c);   }

    public void contarVendedores() {
        System.out.println("Total de vendedores: " + vendedores.size());
    }

    public void contarClientes() {
        System.out.println("Total de clientes  : " + clientes.size());
    }

    // printa nome fantasia, cnpj e endereço
    public void apresentarse() {
        System.out.println("\nLoja");
        System.out.println("Nome Fantasia : " + nomeFantasia);
        System.out.println("CNPJ          : " + cnpj);
        System.out.println("Endereço      : " + rua + ", " + bairro + " - " + cidade);
    }
}
