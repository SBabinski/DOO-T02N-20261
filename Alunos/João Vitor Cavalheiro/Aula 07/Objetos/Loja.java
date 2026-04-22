import java.util.ArrayList;
import java.util.List;

public class Loja extends Endereco {

    String nomeFantasia;
    String razaoSocial;
    String cnpj;

    public Loja(String bairro, String cidade, String complemento, String estado, int numero, String rua, String nomeFantasia, String razaoSocial, String cnpj) {
        super(bairro, cidade, complemento, estado, numero, rua);
        this.nomeFantasia=nomeFantasia;
        this.razaoSocial=razaoSocial;
        this.cnpj=cnpj;
    }

    List <Gerente> gerentes = new ArrayList<>();
    List <Vendedor> vendedores = new ArrayList<>();
    List <Cliente> clientes = new ArrayList<>();

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    public void AddCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public void AddVendedor(Vendedor vendedor){
        vendedor.setLoja(getNomeFantasia());
        vendedores.add(vendedor);
    }

        public void AddGerente(Gerente gerente){
        gerente.setLoja(getNomeFantasia());
        gerentes.add(gerente);
    }

    @Override
    public void apresentarLogradouro(){

        System.out.println("=======================");
        System.out.println("----------Loja----------");
        System.out.println("Nome da Loja "+getNomeFantasia());
        System.out.println("Razão Social: "+getRazaoSocial());
        System.out.println("CNPJ "+getCnpj());
        System.out.println("=======================");

        System.err.println("Esta loja se localiza no:");
        super.apresentarLogradouro();

    }

}
