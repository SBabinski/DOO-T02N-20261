import java.util.ArrayList;

public class Vendedor {

String nome;
int idade;
String loja;
String cidade;
String bairro;
String rua;
double salarioBase;

ArrayList <Double> salarioRecebido = new ArrayList<>();

public Vendedor ( String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {

    this.nome = nome;
    this.idade = idade;
    this.loja = loja;
    this.cidade = cidade;
    this.bairro = bairro;
    this.rua = rua;
    this.salarioBase = salarioBase;
    
}

public void apresentarse(){
 
    System.out.println("nome: " + this.nome);
    System.out.println("idade: " + this.idade);
    System.out.println("loja: " + this.loja);
    
}

public void adicionarSalario(double valor){
    salarioRecebido.add(valor);
}

public double calcularMedia(){
    double soma = 0;
    for (double somasalario : salarioRecebido) {
        soma += somasalario;
    }
    return soma/salarioRecebido.size();
}

public double calcularBonus(){
    return this.salarioBase * 0.2;
}

}