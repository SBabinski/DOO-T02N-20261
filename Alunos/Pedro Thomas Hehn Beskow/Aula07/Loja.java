import java.util.ArrayList;

public class Loja {

    String   nomeFantasia;
    String   razaoSocial;
    String   cnpj;
    Endereco endereco;

    // quem trabalha e quem compra na loja
    ArrayList<Vendedor> vendedores = new ArrayList<>();
    ArrayList<Gerente>  gerentes   = new ArrayList<>();
    ArrayList<Cliente>  clientes   = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial,
                String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial  = razaoSocial;
        this.cnpj         = cnpj;
        this.endereco     = endereco;
    }

    public void adicionarVendedor(Vendedor v) { vendedores.add(v); }
    public void adicionarGerente(Gerente g)   { gerentes.add(g);   }
    public void adicionarCliente(Cliente c)   { clientes.add(c);   }

    public void contarVendedores() {
        System.out.println("Total de vendedores : " + vendedores.size());
    }

    public void contarGerentes() {
        System.out.println("Total de gerentes   : " + gerentes.size());
    }

    public void contarClientes() {
        System.out.println("Total de clientes   : " + clientes.size());
    }

    // printa nome fantasia, CNPJ e endereço da loja
    public void apresentarse() {
        System.out.println("\nLoja");
        System.out.println("Nome Fantasia : " + nomeFantasia);
        System.out.println("Razão Social  : " + razaoSocial);
        System.out.println("CNPJ          : " + cnpj);
        System.out.print("Endereço      :");
        endereco.apresentarLogradouro();
    }
}
