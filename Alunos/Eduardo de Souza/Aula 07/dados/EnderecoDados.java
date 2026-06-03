package aulas.dados;

public class EnderecoDados {
	private String estado;
	private String cidade;
	private String bairro;
	private String rua;
	private String complemento;
	
	public EnderecoDados(String estado,String cidade, String bairro, String rua, String complemento) {
		setEstado(estado);
		setCidade(cidade);
		setBairro(bairro);
		setRua(rua);
		setComplemento(complemento);
	}
	
	public String getEstado() {return estado;}
	public void setEstado(String estado) {
		if(estado != null && !estado.isBlank())this.estado = estado;
	}

	public String getComplemento() {return complemento;}
	public void setComplemento(String complemento) {
		if(complemento != null && !complemento.isBlank())this.complemento = complemento;
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
		if(rua != null && !rua.isBlank())this.rua = rua;
	}

}
