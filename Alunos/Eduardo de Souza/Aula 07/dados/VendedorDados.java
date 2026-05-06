package aulas.dados;

import java.util.ArrayList;
import java.util.List;

import aulas.dados.superclasse.PessoaDados;

public class VendedorDados extends PessoaDados{
	private LojaDados loja;
	private double salarioBase;
	private List<Double> salarioRecebido = new ArrayList<>();

	public VendedorDados(String nome, int idade, LojaDados loja, EnderecoDados endereco, double salarioBase,List<Double> salarioRecebido) {
		setNome(nome);
		setIdade(idade);
		setLoja(loja);
		setEndereco(endereco);
		setSalarioBase(salarioBase);
		setSalarioRecebido(salarioRecebido);
	}
	
	public LojaDados getLoja() {return loja;}
	public void setLoja(LojaDados loja) {
		this.loja = loja;
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
