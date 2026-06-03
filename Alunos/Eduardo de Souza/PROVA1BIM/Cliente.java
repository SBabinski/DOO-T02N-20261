package prova;

public class Cliente {
	private String nome;
	private String cpf;
	private String cnh;
	
	public Cliente(String nome, String cpf, String cnh) {
		setNome(nome);
		setCpf(cpf);
		setCnh(cnh);
	}
	
	public String getNome() {return nome;}
	public void setNome(String nome) {
		if(nome != null && !nome.isBlank())this.nome = nome;
	}
	public String getCpf() {return cpf;}
	public void setCpf(String cpf) {
		if(cpf != null && !cpf.isBlank())this.cpf = cpf;
	}
	public String getCnh() {return cnh;}
	public void setCnh(String cnh) {
		if(cnh != null && !cnh.isBlank())this.cnh = cnh;
	}
	
	public void listaCliente() {
		System.out.printf("Cliente | Nome: %s / CPF: %s / CNH: %s \n", getNome(), getCpf(), getCnh());
	}
}
