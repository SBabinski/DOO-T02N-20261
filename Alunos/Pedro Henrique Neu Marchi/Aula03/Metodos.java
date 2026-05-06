import java.util.Scanner;
import java.util.ArrayList;

public class Metodos {
   static Scanner leitor = new Scanner(System.in);
   static ArrayList<Vendas> vendas = new ArrayList<>();

    public static void calcularPrecoComDesconto(){
        System.out.println("Quantidade comprada: ");
        int quantidade = leitor.nextInt();
        leitor.nextLine();
        System.out.println("Valor Unitario: ");
        double valor = leitor.nextDouble();
        leitor.nextLine();
        
        double total = quantidade * valor;
        double desconto = 0;
        double valorFinal = 0;
        if (quantidade > 10){
            desconto = total * 0.05;
            valorFinal =total-desconto;
        }

        System.out.println("Preco total: " + total);
        System.out.println("Desconto: " + desconto);
        System.out.println("Valor total com desconto: " + valorFinal);

        vendas.add(new Vendas(quantidade, total, desconto, valorFinal));
    }

    public static void calcularTroco(){
        System.out.println("Valor recebido: ");
        double recebido = leitor.nextDouble();
        leitor.nextLine();
        System.out.println("Valor da compra: ");
        double compra = leitor.nextDouble();
        leitor.nextLine();
        
        double troco = recebido - compra;

        System.out.println("Troco: " + troco);
    }

    public static void mostrarVendas(){
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }
        for (Vendas venda : vendas) {
            venda.mostrarVenda();
            System.out.println("-------------------");
        }
    }
}
