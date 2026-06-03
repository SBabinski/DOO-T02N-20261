public class Vendedor {
    public String nome; 
    public int idade; 
    public String Loja;
    public String cidade;
    public String bairro; 
    public String rua; 
    public double salarioBase;
    public double[] salarioRecebido = new double[3];

    public Vendedor(String nome, int idade, String Loja, String cidade, String bairro, String rua, double salarioBase, double[] salarioRecebido){
        this.nome = nome;
        this.idade = idade;
        this.Loja = Loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salarioRecebido = salarioRecebido;
    }

    public void apresentarse(){
        System.out.println("nome: " +nome+ " | idade: " +idade+ " | Loja: " +Loja);
    }
    public void calcularMedia(){
        System.out.println("Média dos ultimos 3 meses de salario: "+(salarioRecebido[0] + salarioRecebido[1] + salarioRecebido[2]) / 3);
    } 
    public void calcularBonus(){
        System.out.println("salario bonus: "+ salarioBase * 0.2);
    }
}
