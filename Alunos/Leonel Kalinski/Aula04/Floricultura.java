package fag;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import objetos.RegistroDeVendas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Floricultura{

	LocalDateTime agora = LocalDateTime.now();
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
                System.out.println("4-Buscar Por Data");
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
		    case 4 -> buscarPorData();
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
        LocalDateTime agora = LocalDateTime.now(); 

        RegistroDeVendas r = new RegistroDeVendas(qtd, preco, valorTotal, agora);
        registro.add(r);
    }
	
	public static void buscarPorData() {
	    System.out.println("Digite a data (dd/MM/yyyy): ");
	    String dataInput = scan.next();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    double total = 0;
	    for (RegistroDeVendas r : registro) {
	        String dataRegistro = r.getDataVenda().format(formatter);
	        if (dataRegistro.equals(dataInput)) {
	            total += r.getValorTotal();
	        }
	    }

	    System.out.println("Total vendido no dia: " + total);
	}
}