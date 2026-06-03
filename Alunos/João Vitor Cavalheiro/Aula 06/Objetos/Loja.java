import java.util.ArrayList;
import java.util.List;

public class Loja {
    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidadeL;
    String bairroL;
    String ruaL;

    List <Vendedor> vendedoresL = new ArrayList<>();
    List <Cliente> clientesL = new ArrayList<>();

    public Loja(){
        
    }

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidadeL, String bairroL, String ruaL) {
        this.nomeFantasia=nomeFantasia;
        this.razaoSocial=razaoSocial;
        this.cnpj=cnpj;
        this.cidadeL=cidadeL;
        this.bairroL=bairroL;
        this.ruaL=ruaL;
    }

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

    public String getCidadeL() {
        return cidadeL;
    }

    public void setCidadeL(String cidadeL) {
        this.cidadeL = cidadeL;
    }

    public String getBairroL() {
        return bairroL;
    }

    public void setBairroL(String bairroL) {
        this.bairroL = bairroL;
    }

    public String getRuaL() {
        return ruaL;
    }

    public void AddCliente(Cliente cliente){
        clientesL.add(cliente);
    }

    public void AddVendedor(Vendedor vendedor){
        vendedor.setLoja(getNomeFantasia());
        vendedoresL.add(vendedor);
    }

    public void setRuaL(String ruaL) {
        this.ruaL = ruaL;
    }
    public void apresentarLoja(){
        
        System.out.println("=======================");
        System.out.println("----------Loja----------");
        System.out.println("Nome da Loja: "+getNomeFantasia());
        System.out.println("CNPJ: "+getCnpj());
        System.out.println("Endereço: Estamos localizados na cidade "+getCidadeL()+
        " no bairro "+getBairroL()+
        " na rua "+getRuaL());
        System.out.println("Esta loja possui "+vendedoresL.size() +" vendedores e "+clientesL.size()+" clientes");
        System.out.println("=======================");

    }
}
