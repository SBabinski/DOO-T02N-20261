package aulas.dados;

import java.util.ArrayList;
import java.util.List;

public class LojaDados {
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private EnderecoDados endereco;
	private List<VendedorDados> vendedores = new ArrayList<>();
	private List<ClienteDados> clientes = new ArrayList<>();
	
	public LojaDados(String nomeFantasia, String razaoSocial, String cnpj, EnderecoDados endereco, List<VendedorDados> vendedores,List<ClienteDados> clientes) {
		setNomeFantasia(nomeFantasia);
		setRazaoSocial(razaoSocial);
		setCnpj(cnpj);
		setEndereco(endereco);
		setVendedores(vendedores);
		setClientes(clientes);
	}
	
	public EnderecoDados getEndereco() {return endereco;}
	public void setEndereco(EnderecoDados endereco) {
		if(endereco != null)this.endereco = endereco;
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
		System.out.printf("Nome: %s | CNPJ: %s | Endereço: %s / %s / %s \n", getNomeFantasia(), getCnpj(), endereco.getCidade(), endereco.getBairro(), endereco.getRua());
	}
}
