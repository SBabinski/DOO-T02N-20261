import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import objetos.RegistroDeVendas;

public class Floricultura{

	static List<RegistroDeVendas> registro = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
     
    }
    public static void menu(){
        int opcao;
            do {
                System.out.println("------- MENU ------");
                System.out.println("1-Preco Total");
                System.out.println("2-Troco");
                System.out.println("3-Registro De Vendas");
                System.out.println("0-Sair");
                opcao = scan.nextInt();
                validarEscolha(opcao);
          
            } while (opcao != 0);
    }
    public static void validarEscolha(int opcao) {
		switch (opcao) {
		    case 1 -> precoTotal();
		    case 2 -> troco();
		    case 3 -> registro();
		    case 0 -> System.out.println("saindo...");
	
		}	

	}
    public static void registro() {
    	   if (registro.size() == 0) {
               System.out.println("nenhum produto vendido");
               return;
           }
    	 for (int i = 0; i < registro.size(); i++) {
             System.out.println((i + 1) + "-" + registro.get(i));
         }
    	 
    }
    public static void troco() {
		System.out.println("Quanto voce pagou?");
		double pago = scan.nextDouble();
		System.out.println("qual era o preco total?");
		double preco = scan.nextDouble();
		System.out.println("O seu troco e:"+(pago-preco));
		
	}
	public static void precoTotal() {
        System.out.println("quantas plantas voce comprou");
        double qtd = scan.nextDouble();
        System.out.println("qual o valor da planta");
        double preco = scan.nextDouble();
        if (qtd>=10) {preco = preco*0.5;}
        double valorTotal = preco * qtd;
        System.out.println("O valor Total a ser pago e:" + valorTotal);
        RegistroDeVendas r = new RegistroDeVendas(qtd, preco, valorTotal);
        registro.add(r);
    }
}