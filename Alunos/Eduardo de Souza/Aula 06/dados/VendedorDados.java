package aulas.dados;

import java.util.ArrayList;
import java.util.List;

public class VendedorDados {
	private String nome;
	private int idade;
	private LojaDados loja;
	private String cidade;
	private String bairro;
	private String rua;
	private double salarioBase;
	private List<Double> salarioRecebido = new ArrayList<>();

	public VendedorDados(String nome, int idade, LojaDados loja, String cidade, String bairro, String rua, double salarioBase,List<Double> salarioRecebido) {
		setNome(nome);
		setIdade(idade);
		setLoja(loja);
		setCidade(cidade);
		setBairro(bairro);
		setRua(rua);
		setSalarioBase(salarioBase);
		setSalarioRecebido(salarioRecebido);
	}
	
	public String getNome() {return nome;}
	public void setNome(String nome) {
		if(nome != null && !nome.isBlank())this.nome = nome;
	}
	
	public int getIdade() {return idade;}
	public void setIdade(int idade) {
		if(idade > 0)this.idade = idade;
	}
	
	public LojaDados getLoja() {return loja;}
	public void setLoja(LojaDados loja) {
		this.loja = loja;
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
	
	public double getSalarioBase() {return salarioBase;}
	public void setSalarioBase(double salarioBase) {
		if(salarioBase > 0)this.salarioBase = salarioBase;
	}
	
	public List<Double> getSalarioRecebido() {return new ArrayList<>(salarioRecebido);}
	public void setSalarioRecebido(List<Double> salarioRecebido) {
		if(salarioRecebido != null)this.salarioRecebido = new ArrayList<>(salarioRecebido);
	}
	
	public void listaVendedor() {
		String auxLoja = (getLoja() == null) ? "Sem vinculo" : getLoja().getNomeFantasia();
		System.out.printf("Nome: %s | Idade: %d | Loja: %s \n", getNome(), getIdade(), auxLoja);
	}
}
