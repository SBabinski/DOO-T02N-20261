import java.util.ArrayList;

public class Loja {

    ArrayList<Vendedor> vendedores = new ArrayList<>();
    ArrayList<Gerente> gerentes = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();

    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    Endereco endereco;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        if (cnpj.length() == 14) {
            this.cnpj = cnpj;
        } else {
            System.out.println("CNPJ inválido. Verifique e tente novamente.");
        }
        this.endereco = endereco;
    }

    public void contarClientes() {
        System.out.println("Total de clientes cadastrados: " + clientes.size());
    }

    public void contarVendedores() {
        System.out.println("Total de vendedores cadastrados: " + vendedores.size());
    }

    public void contarGerentes() {
        System.out.println("Total de gerentes cadastrados: " + gerentes.size());
    }

    public void apresentarse() {
        System.out.println("----DADOS DA LOJA----");
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }
}
