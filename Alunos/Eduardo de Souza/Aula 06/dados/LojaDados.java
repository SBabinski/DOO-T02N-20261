package aulas.dados;

import java.util.ArrayList;
import java.util.List;

public class LojaDados {
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String cidade;
	private String bairro;
	private String rua;
	private List<VendedorDados> vendedores = new ArrayList<>();
	private List<ClienteDados> clientes = new ArrayList<>();
	
	public LojaDados(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua, List<VendedorDados> vendedores,List<ClienteDados> clientes) {
		setNomeFantasia(nomeFantasia);
		setRazaoSocial(razaoSocial);
		setCnpj(cnpj);
		setCidade(cidade);
		setBairro(bairro);
		setRua(rua);
		setVendedores(vendedores);
		setClientes(clientes);
	}
	
	public String getNomeFantasia() {return nomeFantasia;}
	public void setNomeFantasia(String nomeFantasia) {
		if(nomeFantasia != null && !nomeFantasia.isBlank())this.nomeFantasia = nomeFantasia;
	}
	
	public String getRazaoSocial() {return razaoSocial;}
	public void setRazaoSocial(String razaoSocial) {
		if(razaoSocial != null && !razaoSocial.isBlank())this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {return cnpj;}
	public void setCnpj(String cnpj) {
		if(cnpj != null && !cnpj.isBlank())this.cnpj = cnpj;
	}
	
	public String getCidade() {return cidade;}
	public void setCidade(String cidade) {
		if(cidade != null && !cidade.isBlank())this.cidade = cidade;
	}
	
	public String getBairro() {return bairro;}
	public void setBairro(String bairro) {
		if(bairro != null && !bairro.isBlank())this.bairro = bairro;
	}
	
	public String getRua() {return rua;}
	public void setRua(String rua) {
		if(rua != null && !rua.isBlank())this.rua = rua;
	}
	public List<VendedorDados> getVendedores() {return vendedores;}
	public void setVendedores(List<VendedorDados> vendedores) {
		if(vendedores != null)this.vendedores = new ArrayList<>(vendedores);
	}
	
	public List<ClienteDados> getClientes() {return clientes;}
	public void setClientes(List<ClienteDados> clientes) {
		if(clientes != null)this.clientes = new ArrayList<>(clientes);
	}
	
	public void adicionarVendedor (VendedorDados vendedor) {
		if(vendedor != null) vendedores.add(vendedor);
	}
	
	public void adicionarCliente (ClienteDados cliente) {
		if(cliente != null) clientes.add(cliente);
	}
	
	public void listarLoja() {
		System.out.printf("Nome: %s | CNPJ: %s | Endereço: %s / %s / %s \n", getNomeFantasia(), getCnpj(), getCidade(), getBairro(), getRua());
	}
}
