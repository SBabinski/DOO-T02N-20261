package objetos;

public class Clientes {
	private String nome;
	private int cpf;
	private int cnh;
	
	public Clientes() {}

	public Clientes(String nome, int cpf, int cnh) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.cnh = cnh;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public int getCnh() {
		return cnh;
	}

	public void setCnh(int cnh) {
		this.cnh = cnh;
	}
}
