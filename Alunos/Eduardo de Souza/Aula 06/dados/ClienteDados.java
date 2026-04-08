package aulas.dados;

public class ClienteDados {
	private String nome;
	private int idade;
	private String cidade;
	private String bairro;
	private String rua;
	
	public ClienteDados(String nome, int idade, String cidade, String bairro, String rua) {
		setNome(nome);
		setIdade(idade);
		setCidade(cidade);
		setBairro(bairro);
		setRua(rua);
	}

	
	public String getNome() {return nome;}
	public void setNome(String nome) {
		if(nome != null && !nome.isBlank())this.nome = nome;
	}
	
	public int getIdade() {return idade;}
	public void setIdade(int idade) {
		if(idade > 0)this.idade = idade;
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
		if(rua != null && rua.isBlank())this.rua = rua;
	}
	
	public void listarCliente() {
		System.out.printf("Nome: %s | Idade: %d \n", getNome(), getIdade());
	}
}
