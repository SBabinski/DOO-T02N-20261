public class Cliente extends Pessoa {

    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        super(nome, idade, cidade, bairro, rua);
    }

    public void apresentarCliente(){
        System.out.println("=======================");
        System.out.println("----------Cliente----------");
        System.out.println("Nome: "+getNome());
        System.out.println("Idade: "+getIdade());
        System.out.println("=======================");
    }

}

