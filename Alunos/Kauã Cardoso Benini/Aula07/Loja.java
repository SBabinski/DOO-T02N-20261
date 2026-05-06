import java.util.ArrayList;

public class Loja {

    String nomeFantasia;
    String cnpj;
    Endereco endereco;

    ArrayList<Vendedor> vendedores = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();

    public Loja(String nomeFantasia, String cnpj, Endereco endereco){
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public Loja(String myPlant, String myPlantLtda, String s, String medianeira, String centro, String ruaDasFlores) {
    }

    public void apresentarSe(){
        System.out.println("Loja: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }

    public void contarClientes(){
        System.out.println("Clientes: " + clientes.size());
    }

    public void contarVendedores(){
        System.out.println("Vendedores: " + vendedores.size());
    }
}