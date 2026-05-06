package fag.objetos;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {

	private String nome;
	private int idade;
	private Loja loja;
	private String cidade;
	private String bairro;
	private String rua;
	private double salarioBase;
	private ArrayList<Double> salariosRecebidos = new ArrayList<>(List.of(3000.0d, 3500.0d, 4000.0d));
	
	public Vendedor() {
		
	}
	
	public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, double salarioBase,
			double salarioRecebido) {
		setNome(nome);
		setIdade(idade);
		setLoja(loja);
		setCidade(cidade);
		setBairro(bairro);
		setRua(rua);
		setSalarioBase(salarioBase);
		setSalarioRecebido(salarioRecebido);
	}

	public void apresentarse() {
		System.out.printf("Nome: %s/n", nome);
		System.out.printf("Idade: %d/n", idade);
		System.out.printf("Loja: %s/n", loja.getNomeFantasia());
		System.out.println("-------------------------------------------------");
	}
	//calcula media dos salarios
	public double calcularMedia() {
		double soma = 0;
		
		for (double salario : salariosRecebidos) {
			soma += salario;
		}
	
		return soma / salariosRecebidos.size();
	}
	
	public double calcularBonus() {
		return salarioBase * 0.2;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if (!nome.isBlank() && nome != null) {
			this.nome = nome;
		}
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		if(idade >= 0) {
			this.idade = idade;
		}
	}
	
	public Loja getLoja() {
		return loja;
	}
	
	public void setLoja(Loja loja) {
		
		this.loja = loja;
		
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		if (!cidade.isBlank() && cidade != null) {
			this.cidade = cidade;
		}
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		if (!bairro.isBlank() && bairro != null) {
			this.bairro = bairro;
		}
	}
	
	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		if (!rua.isBlank() && rua != null) {
			this.rua = rua;
		}
	}
	
	public double getSalarioBase() {
		return salarioBase;
	}
	
	public void setSalarioBase(double salarioBase) {
		if(salarioBase >= 0) {
			this.salarioBase = salarioBase;
		}
	}
	
	public double getSalarioRecebido() {
		return salariosRecebidos.get(salariosRecebidos.size()-1);
	}
	public void setSalarioRecebido(double salarioRecebido) {
		if(salarioRecebido >= 0) {
			this.salariosRecebidos.add(salarioRecebido) ;
		}
	}
}
