package aulas.dados;

import aulas.dados.superclasse.PessoaDados;

public class ClienteDados extends PessoaDados{
	
	public ClienteDados(String nome, int idade, EnderecoDados endereco) {
		setNome(nome);
		setIdade(idade);
		setEndereco(endereco);
	}
	
	public void listarCliente() {
		System.out.printf("Nome: %s | Idade: %d \n", getNome(), getIdade());
	}
}
