package fag;
import java.util.ArrayList;
import java.util.List;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;
    
    private List<Vendedor> vendedores = new ArrayList<>();
    private List<Gerente> gerentes = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public void adicionarVendedor(Vendedor vendedor) { 
    	vendedores.add(vendedor); 
    }
    
    public void adicionarGerente(Gerente gerente) { 
    	gerentes.add(gerente); 
    }
    
    public void adicionarCliente(Cliente cliente) { 
    	clientes.add(cliente); 
    }

    public void apresentarse() {
        System.out.println("Loja: " + nomeFantasia + " (CNPJ: " + cnpj + ")");
        if(endereco != null) endereco.apresentarLogradouro();
    }

    public void contarVendedores() { 
    	System.out.println("Total de Vendedores: " + vendedores.size()); 
    }
    
    public void contarClientes() { 
    	System.out.println("Total de Clientes: " + clientes.size());
    }

    public String getNomeFantasia() { 
    	return nomeFantasia; 
    }
    
    public List<Vendedor> getVendedores() { 
    	return vendedores; 
    }
}