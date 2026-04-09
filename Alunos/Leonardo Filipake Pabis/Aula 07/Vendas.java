
import java.util.Scanner;

public class Vendas {
    static Scanner scan = new Scanner(System.in);
    private int quantidade = 0;
    private double preco = 0;
    private double valorDescontoRecebido = 0;

    public Vendas(){

    }
    
    public Vendas(int quantidade, double preco, double valorDescontoRecebido){
        setQuantidade(quantidade);
        setPreco(preco);
        setvalorDescontoRecebido(valorDescontoRecebido);
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        if (quantidade <= 0){
            System.out.println("Digite uma quantidade válida:");
            int q = scan.nextInt();
            scan.nextLine();
            setQuantidade(q);
        }
        this.quantidade = quantidade;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        if (preco <= 0){
            System.out.println("Digite um preço válido:");
            double p = scan.nextDouble();
            scan.nextLine();
            setPreco(p);
        }
        this.preco = preco;
    }
    public double getvalorDescontoRecebido() {
        return valorDescontoRecebido;
    }
    public void setvalorDescontoRecebido(double valorDescontoRecebido) {
        this.valorDescontoRecebido = valorDescontoRecebido;
    } 



    
}