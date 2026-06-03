package fag;

public class Cliente {
	String nome;
	String cpf;
	String cnh;
	
	public Cliente(String nome, String cpf, String cnh) {
		if (nome != null && !nome.isBlank()) {
			this.nome = nome;
		}
		if (cpf != null && !cpf.isBlank()) {
			this.cpf = cpf;
		}
		if (cnh != null && !cnh.isBlank()) {
			this.cnh = cnh;
		}
		
	}
}
