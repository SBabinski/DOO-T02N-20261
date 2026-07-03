package fag;

import java.util.List;

public class Serie {
	 int id;
	 String nome;
	 String idioma;
	 List<String> generos;
	 double nota;
	 String status;	
	 String estreia;
	 String termino;
	 Emissora emissora;
	 
	 public Serie(int id, String nome, String idioma, List<String> generos, double nota, String status, String estreia, String termino, Emissora emissora) {
		 this.id = id;
		 this.nome = nome;
		 this.idioma = idioma;
		 this.generos = generos;
		 this.nota = nota;
		 this.status = status;
		 this.estreia = estreia;
		 this.termino = termino;
		 this.emissora = emissora;
		 
	 }
	 
	 public int getId() {
		 return id;
	 }
	 
	 public void setId(int id) {
		 this.id = id;
	 }
	 
	 public String getNome() {
		 return nome;
	 }
	 
	 public void setNome(String nome) {
		 this.nome = nome;
	 }
	 
	 public String getIdioma() {
		 return idioma;
	 }
	 
	 public void setIdioma(String idioma) {
		 this.idioma = idioma;
	 }
	 
	 public List<String> getGeneros() {
		 return generos;
	 }
	 
	 public void setGeneros(List<String> generos) {
		 this.generos = generos;
	 }
	 
	 public double getNota() {
		 return nota;
	 }
	 
	 public void setNota(double nota) {
		 this.nota = nota;
	 }
	 
	 public String getStatus() {
		 return status;
	 }
	 
	 public void setStatus(String status) {
		 this.status = status;
	 }
	 
	 public String getEstreia() {
		 return estreia;
	 }
	 
	 public void setEstreia(String estreia) {
		 this.estreia = estreia;
	 }
	 
	 public String getTermino() {
		 return termino;
	 }
	 
	 public void setTermino(String termino) {
		 this.termino = termino;
	 }
	 
	 public Emissora getEmissora() {
		 return emissora;
	 }
	 
	 public void setEmissora(Emissora emissora) {
		 this.emissora = emissora;
	 }
	 
}
