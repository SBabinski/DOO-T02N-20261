package fag.objetos;

public class Cliente extends Pessoa {

	public Cliente(){
		
	}
	
	public Cliente(String nome, int idade, Endereco endereco) {	
		super(nome, idade, endereco);
	}

	public void apresentarse() {
		System.out.printf("Nome: %s\n", nome );
		System.out.printf("Idade: %d\n", idade);
		endereco.mostrarEndereco();
		System.out.println("------------------------------------------");
	}

}
