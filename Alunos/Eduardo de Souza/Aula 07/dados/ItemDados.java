package aulas.dados;

public class ItemDados {
	private int id;
	private String nome;
	private String tipo;
	private double valor;
	
	public ItemDados(int id, String nome, String tipo, double valor) {
		setId(id);
		setNome(nome);
		setTipo(tipo);
		setValor(valor);
	}
	
	public int getId() {return id;}
	public void setId(int id) {
		if(id > 0)this.id = id;
	}
	
	public String getNome() {return nome;}
	public void setNome(String nome) {
		if(nome != null && !nome.isBlank())this.nome = nome;
	
	}
	public String getTipo() {return tipo;}
	public void setTipo(String tipo) {
		if(tipo != null && !tipo.isBlank())this.tipo = tipo;
	}
	
	public double getValor() {return valor;}
	public void setValor(double valor) {
		if(valor > 0.0)this.valor = valor;
	}
	
	public void listaItens() {
		System.out.printf("ID: %d | Nome: %s | Tipo: %s | Valor: %.2f \n", getId(), getNome(), getTipo(), getValor());
	}
}
