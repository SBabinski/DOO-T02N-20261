package fag;
import java.util.List;
import java.util.ArrayList;

public class Usuario {
	String nome;
	List<Serie> favoritos;
	List<Serie> assistidos;
	List<Serie> assistir;
	
	public Usuario(String nome) {
		this.nome = nome;
		this.favoritos = new ArrayList<>();
		this.assistidos = new ArrayList<>();
		this.assistir = new ArrayList<>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Serie> getFavoritos(){
		return favoritos;
	}
	
	public void setFavoritos(List<Serie> favoritos) {
		this.favoritos = favoritos;
	}
	
	public List<Serie> getAssistidos(){
		return assistidos;
	}
	
	public void setAssistidos(List<Serie> assistidos) {
		this.assistidos = assistidos;
	}
	
	public List<Serie> getAssistir(){
		return assistir;
	}
	
	public void setAssistir(List<Serie> assistir) {
		this.assistir = assistir;
	}
}
