package fag.objects;

public class Floricultura {
	
	private String nome;
	private int quantidade;
	private double preco;
	
	public Floricultura(String nome, double preco) {
	
		setNome(nome);
		setPreco(preco);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	@Override
	public String toString() {
		return "Floricultura [nome=" + nome +  "preco=" + preco + "]";
	}
	
	
	
	

}
