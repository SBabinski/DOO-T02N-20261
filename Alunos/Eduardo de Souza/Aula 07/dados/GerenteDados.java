package aulas.dados;

import java.util.ArrayList;
import java.util.List;

import aulas.dados.superclasse.PessoaDados;

public class GerenteDados extends PessoaDados {
	private LojaDados loja;
	private double salarioBase;
	private List<Double> salarioRecebido = new ArrayList<>();

	public GerenteDados(String nome, int idade, LojaDados loja, EnderecoDados endereco,double salarioBase, List<Double> salarioRecebido) {
		setNome(nome);
		setIdade(idade);
		setLoja(loja);
		setEndereco(endereco);
		setSalarioBase(salarioBase);
		setSalarioRecebido(salarioRecebido);
	}
	
	public LojaDados getLoja() {return loja;}
	public void setLoja(LojaDados loja) {
		if(loja != null)this.loja = loja;
	}
	
	public double getSalarioBase() {return salarioBase;}
	public void setSalarioBase(double salarioBase) {
		if(salarioBase > 0)this.salarioBase = salarioBase;
	}
	
	public List<Double> getSalarioRecebido() {return salarioRecebido;}
	public void setSalarioRecebido(List<Double> salarioRecebido) {
		if(salarioRecebido != null)this.salarioRecebido = salarioRecebido;
	}
	
	public void listaGerente() {
		String auxLoja = (getLoja() == null) ? "Sem Vinculo" : getLoja().getNomeFantasia();
		System.out.printf("Nome: %s | Idade: %d | Loja: %s \n", getNome(), getIdade(), auxLoja);
	}
}
