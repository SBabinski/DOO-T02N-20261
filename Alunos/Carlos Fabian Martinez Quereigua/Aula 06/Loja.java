package fag.objetos;

import java.util.ArrayList;

public class Loja {

	private String nomeFantasia;
	private String razaoSocial;
	private int cnpj; 
	private String cidade;
	private String bairro;
	private String rua;
	ArrayList<Vendedor> vendedores = new ArrayList<>();
	ArrayList<Cliente> clientes = new ArrayList<>();
	
	public Loja() {
		
	}

	public Loja(String nomeFantasia, String razaoSocial, int cnpj, String cidade, String bairro, String rua,
			ArrayList<Vendedor> vendedores, ArrayList<Cliente> clientes) {
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.vendedores = vendedores;
		this.clientes = clientes;
	}
	
	public void apresentarse() {
		
		System.out.printf("Nome: %s\n", nomeFantasia);
		System.out.printf("CNPJ: %d\n", cnpj);
		System.out.printf("Cidade: %s \nBairro: %s \nRua %s\n", cidade, bairro, rua);
		System.out.println("---------------------------------------------------");
	}
	
	public int contarVendedores() {
		
		return vendedores.size();
		
	}
	public int contarClientes() {
		
		return clientes.size();
		
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
	public int getCpnj() {
		return cnpj;
	}
	public void setCpnj(int cpnj) {
		this.cnpj = cpnj;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public ArrayList<Vendedor> getVendedores() {
		return vendedores;
	}
	public void setVendedores(Vendedor vendedor) {
		this.vendedores.add(vendedor);
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
	
	
	
	}
