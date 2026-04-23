package aula6;
import java.util.ArrayList;
import java.util.Scanner;

public class Vendedor {
    
    int idade;
    double salarioBase, salarioRecebido;
    String nome, loja, cidade, rua;

    double teste = 100;

    ArrayList<Double> salariosRecebidos = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public Vendedor(String nome, int idade, String loja, String cidade, String rua, double salarioBase){

        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.rua = rua;
        this.salarioBase = salarioBase;
        salariosRecebidos.add(1000.);
        salariosRecebidos.add(4500.);
        salariosRecebidos.add(2500.);
    }

    public void apresentarse(){

        System.out.println("\nNome: " + this.nome + " - Idade: " + this.idade + " - Loja: " + this.loja);
    }

    public void calcularMedia(){

        int cont = 0;
        double somaSalariosRecebidos = 0;

        for (Double salarioRecebido : salariosRecebidos){

            somaSalariosRecebidos += salarioRecebido;
            cont++;
        }

        System.out.println("\nMédia dos salários do vendedor " + this.nome + ": " + somaSalariosRecebidos / cont);
    }

    public void calcularBonus(){

        System.out.println("\nBônus: " + this.salarioBase * .2);
    }

    public void alterarNome(){

        String nome;

        System.out.println("\nDigite o novo nome: ");
        nome = scan.nextLine();

        this.nome = nome;
    }

    public void alterarIdade(){

        int idade;

        System.out.println("\nDigite a nova idade: ");
        idade = scan.nextInt();

        scan.nextLine();

        this.idade = idade;
    }

     public void alterarLoja(){

        String loja;

        System.out.println("\nDigite o nome da nova loja: ");
        loja = scan.nextLine();

        this.loja = loja;
    }

     public void alterarCidade(){

        String cidade;

        System.out.println("\nDigite o nome da nova cidade: ");
        cidade = scan.nextLine();

        this.cidade = cidade;
    }

    public void alterarRua(){

        String rua;

        System.out.println("\nDigite o nome da nova rua: ");
        rua = scan.nextLine();

        this.rua = rua;
    }

    public void alterarSalarioBase(){

        double salarioBase;

        System.out.println("\nDigite o novo salário base: ");
        salarioBase = scan.nextDouble();

        scan.nextLine();

        this.salarioBase = salarioBase;
    }

    public void adicionarSalarioRecebido(){

        System.out.println("\nDigite o valor do salário recebido: ");
        this.salarioRecebido = scan.nextDouble();

        scan.nextLine();

        salariosRecebidos.add(this.salarioRecebido);
    }
}   
