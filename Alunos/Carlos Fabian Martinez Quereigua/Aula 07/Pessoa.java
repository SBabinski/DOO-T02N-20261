package fag.objetos;

public class Pessoa {

	protected String nome;
	protected int idade;
	protected Endereco endereco;
	
	public Pessoa() {
		
	}

	public Pessoa(String nome, int idade, Endereco endereco) {
	        setNome(nome);
	        setIdade(idade);
	        setEndereco(endereco);
	}
	 
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome != null && !nome.isBlank()) {
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
	    if (endereco != null &&
	        endereco.getEstado() != null && !endereco.getEstado().isBlank() &&
	        endereco.getCidade() != null && !endereco.getCidade().isBlank() &&
	        endereco.getBairro() != null && !endereco.getBairro().isBlank() &&
	        endereco.getRua() != null && !endereco.getRua().isBlank() &&
	        endereco.getNumero() != null && !endereco.getNumero().isBlank()) {
	        
	        this.endereco = endereco;
	    }
	}
	
	
}
