public class Loja {
    public String nomeFantasia; 
    public String razãoSocial; 
    public String cnpj; 
    public String cidade; 
    public String bairro; 
    public String rua; 
    public Vendedor[] vendedores; 
    public Cliente[] clientes;

    public Loja(String nomeFantasia, String razãoSocial, String cnpj, String cidade, String bairro, String rua, Vendedor[] vendedores, Cliente[] clientes){
        this.nomeFantasia = nomeFantasia;
        this.razãoSocial = razãoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores =vendedores;
        this.clientes = clientes;
    }

    public void contarClientes(){
        System.out.println("quantidade de clientes: " +this.clientes.length);
    }
    public void contarVendedores(){
        System.out.println("quantidade de vendedores: " +this.vendedores.length);
    }
    public void apresentarse(){
        System.out.println("Loja "+nomeFantasia+ " cnpj: " +cnpj+ " localizada na rua " +rua+" no bairro "+bairro);
    }
}
