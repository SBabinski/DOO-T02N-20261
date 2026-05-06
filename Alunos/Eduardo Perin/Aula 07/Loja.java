import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
    static Scanner scan = new Scanner(System.in);
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;
    private ArrayList<Funcionario>funcionarios = new ArrayList<>();
    private ArrayList<Cliente>clientesLoja = new ArrayList<>();

    
    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        setNomeFantasia(nomeFantasia);
        setRazaoSocial(razaoSocial);
        setCnpj(cnpj);
        this.endereco = endereco;
    }
    public Loja(){

    }
    public void criar(){
        Endereco endereco = new Endereco();
        System.out.println("digite o nome fantasia:");
        setNomeFantasia(scan.nextLine());
        System.out.println("digite a razao social");
        setRazaoSocial(scan.nextLine());
        System.out.println("digite o CNPJ");
        setCnpj(scan.nextLine());
        endereco.criar();
        this.endereco = endereco;
    }
    public void apresentarse(){
        System.out.println("LOJA:");
        System.out.println("NOME: "+ nomeFantasia +", CNPJ: "+cnpj);
        endereco.apresentarLogradouro();
        System.out.println("CLIENTES:");
        for(int i=0; i<clientesLoja.size();i++){
            clientesLoja.get(i).apresentarse();
        }
        System.out.println("VENDEDORES:");
        for (int i = 0; i < funcionarios.size(); i++) {
            funcionarios.get(i).apresentarse();
        }
        System.out.println();
    }
    public int contarClientes(){
        return clientesLoja.size();
    }
    public void adicionarCliente(Cliente cliente){
        clientesLoja.add(cliente);
    }
    public int contarFuncionarios(){
        return funcionarios.size();
    }
    public void adicionarFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);
        funcionario.adicionarFuncionarioNaLoja(this);
    }
    public ArrayList<Vendedor> getVendedores() {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            if (f instanceof Vendedor) {
                vendedores.add((Vendedor) f);
            }
        }
        return vendedores;
    }

    public ArrayList<Gerente> getGerentes() {
        ArrayList<Gerente> gerentes = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            if (f instanceof Gerente) {
                gerentes.add((Gerente) f);
            }
        }
        return gerentes;
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
}
