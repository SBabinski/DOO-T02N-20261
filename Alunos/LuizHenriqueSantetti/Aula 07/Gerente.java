public class Gerente extends Pessoa {

    String loja;
    double salarioBase;
    double[] salarioRecebido = {3000,3200,3100};

    public Gerente(String nome, int idade, Endereco endereco, String loja, double salarioBase){
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
    }

    public void apresentarse(){
        System.out.println("gerente: " + nome + " idade: " + idade + " loja: " + loja);
    }

    public double calcularMedia(){
        double soma = 0;
        for(int i=0;i<salarioRecebido.length;i++){
            soma = soma + salarioRecebido[i];
        }
        return soma / salarioRecebido.length;
    }

    public double calcularBonus(){
        return salarioBase * 0.35;
    }
}