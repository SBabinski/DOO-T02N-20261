import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
    static Scanner scan = new Scanner(System.in);
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    public ArrayList<Cliente>clientesLoja = new ArrayList<>();
    private ArrayList<Vendedor>vendedores = new ArrayList<>();

    
    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        setNomeFantasia(nomeFantasia);
        setRazaoSocial(razaoSocial);
        setCnpj(cnpj);
        setCidade(cidade);
        setBairro(bairro);
        setRua(rua);
    }
    public void apresentarse(){
        String endereco = "cidade: "+cidade+", rua: "+rua+", bairro: "+bairro;
        System.out.println("Loja:");
        System.out.println("Nome: "+ nomeFantasia +", CNPJ: "+cnpj+", endereco: "+endereco);
        System.out.println("Clientes:");
        for(int i=0; i<clientesLoja.size();i++){
            clientesLoja.get(i).apresentarse();
        }
        System.out.println("Vendedores:");
        for (int i = 0; i < vendedores.size(); i++) {
            vendedores.get(i).apresentarse();
        }
        System.out.println();
    }
    public int contarClientes(){
        return clientesLoja.size();
    }
    public void adicionarCliente(Cliente cliente){
        clientesLoja.add(cliente);
    }
    public int contarVendedores(){
        return vendedores.size();
    }
    public void adicionarVendedor(Vendedor vendedor){
        vendedores.add(vendedor);
    }
    public String getNomeFantasia() {
        return nomeFantasia;
    }
    public void setNomeFantasia(String nomeFantasia) {
        if(nomeFantasia!=null && !nomeFantasia.isBlank()){
            this.nomeFantasia = nomeFantasia;
        }
        else{
            System.out.println("nome fantasia invalido, digite outro:");
            nomeFantasia = scan.nextLine();
            setNomeFantasia(nomeFantasia);
        }
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        if(razaoSocial!=null && !razaoSocial.isBlank()){
            this.razaoSocial = razaoSocial;
        }
        else{
            System.out.println("razao social invalida, digite novamente:");
            razaoSocial = scan.nextLine();
            setRazaoSocial(razaoSocial);
        }
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        if(cnpj!=null && !cnpj.isBlank()){
            this.cnpj = cnpj;
        }
        else{
            System.err.println("CNPJ invalido, digite novamente:");
            cnpj = scan.nextLine();
            setCnpj(cnpj);
        }
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        if(cidade!=null && !cidade.isBlank()){
            this.cidade = cidade;
        }
        else{
            System.out.println("cidade invalida, digite novamente:");
            cidade = scan.nextLine();
            setCidade(cidade);
        }
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        if(bairro!=null && !bairro.isBlank()){
            this.bairro = bairro;
        }
        else{
            System.out.println("bairro invalido, digite novamente:");
            bairro = scan.nextLine();
            setBairro(bairro);
        }
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        if(rua!=null && !rua.isBlank()){
            this.rua = rua;
        }
        else{
            System.out.println("rua invalida, digite novamente:");
            rua = scan.nextLine();
            setRua(rua);
        }
    }

}
