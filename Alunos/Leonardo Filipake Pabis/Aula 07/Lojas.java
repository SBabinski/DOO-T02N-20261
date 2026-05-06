import java.util.ArrayList;

public class Lojas {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    public Endereco endereco;
    public ArrayList<Vendedor> vendedores = new ArrayList<>();
    public ArrayList<Clientes> clientes = new ArrayList<>();
    private Gerente gerente;

    public Lojas() {

    }

    public Lojas(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        setNomeFantasia(nomeFantasia);
        setRazaoSocial(razaoSocial);
        setCnpj(cnpj);
        this.endereco = new Endereco(cidade, bairro, rua);
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public final void setNomeFantasia(String nomeFantasia) {
        if ((nomeFantasia != null) && (!nomeFantasia.isBlank())){
            this.nomeFantasia = nomeFantasia;
        }
        else {
            System.out.println("Digite um nome válido:");
            String novoNomeFantasia = Main.scan.nextLine();
            setNomeFantasia(novoNomeFantasia);
        }
    }

    
   public String getRazaoSocial() {
        return razaoSocial;
    }

    public final void setRazaoSocial(String razaoSocial) {
        if ((razaoSocial != null) && (!razaoSocial.isBlank())){
            this.razaoSocial = razaoSocial;
        }
        else {
            System.out.println("Digite um nome válido:");
            String novoRazaoSocial = Main.scan.nextLine();
            setRazaoSocial(novoRazaoSocial);
        }
    }

    public String getCnpj() {
        return cnpj;
    }

    public final void setCnpj(String cnpj) {
        if ((cnpj != null) && (!cnpj.isBlank()) && (cnpj.length() == 14)){
            this.cnpj = cnpj;
        }
        else {
            System.out.println("Digite um cnpj válido:");
            String novaCnpj = Main.scan.nextLine();
            setCnpj(novaCnpj);
        }
    }

    public Endereco getEndereco(){
        return endereco;
    }
    

    public void contarClientes() {
        int quantidadeClientes = clientes.size();
        System.out.println("A loja "+this.nomeFantasia+" tem "+quantidadeClientes+" clientes");
    }

    public void contarVendedores() {
        int quantidadeVendedores = vendedores.size();
        System.out.println("A loja "+this.nomeFantasia+" tem "+quantidadeVendedores+" vendedores");
    }

    public void apresentarse() {
        System.out.printf("\nLoja: "+ this.nomeFantasia + " | CNPJ: " + this.cnpj + " | "+ this.endereco);
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }


}
