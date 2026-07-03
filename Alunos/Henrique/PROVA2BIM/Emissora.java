package fag;

public class Emissora {
	String nome;
	String pais;
	
	public Emissora(String nome, String pais) {
		this.nome = nome;
		this.pais = pais;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
}
