package objetos;

import fag.Floricultura;

public class Cliente {

    private String nome;
    private int idade;
    private Enderecos enderecos;

    public Cliente() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if ((nome != null) && (!nome.isBlank())){
            this.nome = nome;
        }
        else {
            System.out.println("Digite um nome válido:");
            String novoNome = Floricultura.scan.nextLine();
            setNome(novoNome);
        }
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade > 0){
            this.idade = idade;
        }
        else {
            System.out.println("Digite uma idade válida:");
            int novaIdade = Floricultura.scan.nextInt();
            Floricultura.scan.nextLine();
            setIdade(novaIdade);
        }
    }

    public void setEndereco(Enderecos enderecos) {
    	this.enderecos = enderecos; 
    	}

    public void apresentarse() {
        System.out.println("Cliente: "+ this.nome + " | Idade: "+ this.idade);
    	enderecos.apresentarLogradouro();
    }
    
}
