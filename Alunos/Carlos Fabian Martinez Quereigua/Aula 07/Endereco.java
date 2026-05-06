package fag.objetos;

public class Endereco {
	
	protected String estado;
	protected String cidade;
	protected String bairro;
	protected String rua;
	protected String numero;
	
	public Endereco(){
		
	}

	public Endereco(String estado, String cidade, String bairro, String rua, String numero) {
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		if(estado != null && !estado.isBlank()) {
			this.estado = estado;
		}
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		if(cidade != null && !cidade.isBlank()) {
			this.cidade = cidade;
		}
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		if(bairro != null && !bairro.isBlank()) {
			this.bairro = bairro;
		}
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		if(rua != null && !rua.isBlank()) {
			this.rua = rua;
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if(numero != null && !numero.isBlank()) {
			this.numero = numero;
		}
	}
	
	public void mostrarEndereco(){
		System.out.printf("Estado: %s\n",estado);
		System.out.printf("Cidade: %s\n",cidade);
		System.out.printf("Bairro: %s\n",bairro);
		System.out.printf("Rua: %s\n",rua);
		System.out.printf("Número: %s\n",numero);
	}
}
