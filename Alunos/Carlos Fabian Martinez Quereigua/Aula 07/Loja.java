package fag.objetos;

import java.util.ArrayList;

public class Loja {
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private Endereco endereco;
	private ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public Loja() {
		
	}
	
	public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.endereco = endereco;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		if(nomeFantasia != null && !nomeFantasia.isBlank()) {
			this.nomeFantasia = nomeFantasia;
		}
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		if(razaoSocial != null && !razaoSocial.isBlank()) {
			this.razaoSocial = razaoSocial;
		}
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if(cnpj != null && !cnpj.isBlank()) {
			this.cnpj = cnpj;
		}
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
	    if (endereco != null &&
	        endereco.getEstado() != null && !endereco.getEstado().isBlank() &&
	        endereco.getCidade() != null && !endereco.getCidade().isBlank() &&
	        endereco.getBairro() != null && !endereco.getBairro().isBlank() &&
	        endereco.getRua() != null && !endereco.getRua().isBlank() &&
	        endereco.getNumero() != null && !endereco.getNumero().isBlank()) {
	        
	        this.endereco = endereco;
	    }
	}

	public ArrayList<Vendedor> getVendedores() {
		return vendedores;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void adicionaVendedor(Vendedor vendedor) {
		if(vendedor != null) {
			vendedores.add(vendedor);
			System.out.println("Vendedor adicionado");
		}
		else {
			System.out.println("Vendedor invalido!");
		}
	}
	
	public void adicionaCliente(Cliente cliente) {
		if(cliente != null) {
			clientes.add(cliente);
			System.out.println("Cliente adicionado");
		}
		else {
			System.out.println("Cliente invalido!");
		}
	}
	
}
