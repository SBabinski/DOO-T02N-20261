package fag;

public class Cliente {
    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;

public Cliente(String nome, int idade, String cidade, String bairro, String rua){
    this.nome = nome;
    if (idade > 0 && idade < 100) {
        this.idade = idade;
    }else {
        System.out.println("Idade inválida. Por favor tente novamente.");
    }
    this.cidade = cidade;
    this.bairro = bairro;
    this.rua = rua;
}

public void apresentarse() {
	System.out.println("----APRESENTAÇÃO DO CLIENTE----");
	System.out.println("Nome: "+nome);
	System.out.println("Idade: "+idade);
	
}
}
