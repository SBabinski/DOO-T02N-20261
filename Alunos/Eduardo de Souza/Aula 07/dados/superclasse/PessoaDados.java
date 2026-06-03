package aulas.dados.superclasse;

import aulas.dados.*;

public class PessoaDados {
	
	private String nome;
	private int idade;
	private EnderecoDados endereco;
	
	public String getNome() {return nome;}
	public void setNome(String nome) {
		if(nome != null && !nome.isBlank())this.nome = nome;
	}
	
	public int getIdade() {return idade;}
	public void setIdade(int idade) {
		if(idade > 0)this.idade = idade;
	}
	
	public EnderecoDados getEndereco() {return endereco;}
	public void setEndereco(EnderecoDados endereco) {
		if(endereco != null)this.endereco = endereco;
	}
}
