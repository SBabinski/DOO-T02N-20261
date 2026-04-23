package fag.objetos;

public class Cliente {
	private String nome;
	private String cPF;
	private String cNH;
	
	public Cliente() {
		
	}
	
	public Cliente(String nome, String CPF, String CNH) {
		setNome(nome);
		setCPF(CPF);
		setCNH(CNH);		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome != null && !nome.isBlank()) {
			this.nome = nome;
		}
	}

	public String getCPF() {
		return cPF;
	}

	public void setCPF(String cPF) {
		if(cPF != null && !cPF.isBlank()) {
			this.cPF = cPF;
		}
	}

	public String getCNH() {
		return cNH;
	}

	public void setCNH(String cNH) {
		if(cNH != null && !cNH.isBlank()) {
			this.cNH = cNH;
		}
	}
	
	
}
