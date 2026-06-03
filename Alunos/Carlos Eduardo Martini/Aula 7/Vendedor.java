public class Vendedor extends Funcionarios {

    public Vendedor(String nome, int idade, String Loja, Endereco endereco, double salarioBase, double[] salarioRecebido){
        super(nome, idade, Loja, endereco, salarioBase, salarioRecebido);
    }

    public void apresentarse(){
        System.out.println("Vendedor: " +nome+ " | idade: " +idade+ " | Loja: " +Loja);
    }
    public void calcularMedia(){
        System.out.println("Média dos ultimos 3 meses de salario: "+(salarioRecebido[0] + salarioRecebido[1] + salarioRecebido[2]) / 3);
    } 
    public void calcularBonus(){
        System.out.println("salario bonus: "+ salarioBase * 0.2);
    }
}
