public class Clientes extends Pessoa {

    public Clientes() {

    }

    public Clientes(String nome, int idade, String cidade, String bairro, String rua) {
        super(nome, idade, cidade, bairro, rua);
    }

    public void apresentarse() {
        System.out.println("Cliente: "+ this.getNome() + " | Idade: "+ this.getIdade());
    }
     
}
