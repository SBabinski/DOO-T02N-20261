public class Cliente extends Pessoa {
    
    double totalCompras; 

    public Cliente (String nome, int idade, Endereco endereco) {
        super (nome, idade, endereco);
        this.totalCompras = 0;
    }
    
    public void apresentarSe() {
        System.out.println("Cliente");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Total compras: R$ " + totalCompras);
    }

    public void adicionarCompra(double valor) {
        totalCompras = totalCompras + valor;
    }
}