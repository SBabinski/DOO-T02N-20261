package fag;

public class Endereco {
	String estado;
	String cidade;
	String bairro;
	int numero;
	String complemento;
	
	public Endereco(String estado, String cidade, String bairro, int numero, String complemento) {
		if (estado != null && !estado.isBlank()) {
			this.estado = estado;
		}else {
			System.out.println("Estado inválido, tente novamente");
		}
		if (cidade != null && !cidade.isBlank()) {
			this.cidade = cidade;
		}else {
			System.out.println("Cidade inválida, tente novamente");
		}
		if (bairro != null && !bairro.isBlank()) {
			this.bairro = bairro;
		}else {
			System.out.println("Bairro inválido, tente novamente");
		}
		if (numero > 0 && numero <+ 10000) {
			this.numero = numero;
		}
		if (complemento != null && !complemento.isBlank()) {
			this.complemento = complemento;
		}else {
			System.out.println("Complemento inválido, tente novamente");
		}
	}
	
	public void apresentarLogradouro() {
		System.out.println("----ENDEREÇOS----");
		System.out.println("Estado: "+estado);
		System.out.println("Cidade: "+cidade);
		System.out.println(("Bairro: "+bairro));
		System.out.println("Número da casa: "+numero);
		System.out.println("Complemento: "+complemento);
		
	}
	
}
