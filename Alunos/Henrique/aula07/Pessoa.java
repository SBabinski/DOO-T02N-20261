package fag;

public class Pessoa {
	
	String nome;
	int idade;
	Endereco endereco;
	
	public Pessoa(String nome, int idade, Endereco endereco) {
		if (nome != null && !nome.isBlank()) {
			this.nome = nome;
		} else {
			System.out.println("Nome inválido.");
		}
		if (idade > 0 && idade < 100) {
			this.idade = idade;
		}else {
			System.out.println("Idade inválida.");
		}
		this.endereco = endereco;
	}
	
	public void apresentarse() {
		System.out.println("Nome: "+nome);
		System.out.println("Idade: "+idade);
		endereco.apresentarLogradouro();
	}
	
}
