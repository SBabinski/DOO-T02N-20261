package fag;

public class Item {
	
	int id;
	String nome;
	String tipo;
	double valor;
	
	public Item(int id, String nome, String tipo, double valor) {
		this.id = id;
		if (nome != null && !nome.isBlank()) {
			this.nome = nome;
		} else {
			System.out.println("Nome inválido, tente novamente.");
		}
		if (tipo != null && !tipo.isBlank()) {
			this.tipo = tipo;
		} else {
			System.out.println("Tipo inválido, tente novamente.");
		}
		if (valor > 0 && valor < 10000) {
			this.valor = valor;
		}else {
			System.out.println("Valor inválido");
		}
	}	
	
	public void gerarDescricao() {
		System.out.println("----APRESENTAÇÃO DO ITEM----");
		System.out.println("ID do item: "+id);
		System.out.println("Nome do item: "+ nome);
		System.out.println("Tipo do item: "+tipo);
		System.out.println("Valor do item: "+valor);
		
	}
	
}
