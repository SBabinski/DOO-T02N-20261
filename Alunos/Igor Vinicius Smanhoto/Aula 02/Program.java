package fag;

import java.util.ArrayList;
import java.util.Scanner;

import fag.objects.Floricultura;

public class Program {
public static  ArrayList<Floricultura> produto = new ArrayList<>();
 	

public static void main(String[] args) {
	 Scanner sc = new Scanner(System.in);
     popularFloricultura();
     imprimirMenu(sc);
     sc.close();
	}

public static void popularFloricultura()
{
	produto.add(new Floricultura("Orquidea", 16.0));
	produto.add(new Floricultura("Rosa do Deserto", 33.0));
	produto.add(new Floricultura("Rosa do Oceano", 12.0));
	produto.add(new Floricultura("Rosas Amarelas", 6.0));
	produto.add(new Floricultura("Rosas", 8.0));
}

public static void listarPlantas() {
    System.out.println("\n===== PLANTAS DISPONÍVEIS =====");
    for (int i = 0; i < produto.size(); i++) {
        System.out.printf("[%d] - %s | Preço: R$ %.2f%n",
                i + 1,
                produto.get(i).getNome(),
                produto.get(i).getPreco());
    }
}

public static void imprimirMenu(Scanner sc)
{
	int opcao = 0;
	   do {
           listarPlantas();

           System.out.println("\nEscolha o número da planta que deseja comprar:");
           int escolha = sc.nextInt();

           if (escolha < 1 || escolha > produto.size()) {
               System.out.println("Opção de planta inválida.");
               continue;
           }

           Floricultura plantaEscolhida = produto.get(escolha - 1);

           System.out.println("Digite a quantidade:");
           int quantidade = sc.nextInt();

           double precoTotal = plantaEscolhida.getPreco() * quantidade;

           System.out.println("\nAbaixo as opções:");
           System.out.println("[1] - Calcular preço total");
           System.out.println("[2] - Calcular troco");
           System.out.println("[3] - Sair");

           System.out.print("Digite a opção: ");
           opcao = sc.nextInt();

           switch (opcao) {
               case 1:
                   System.out.printf("Planta escolhida: %s%n", plantaEscolhida.getNome());
                   System.out.printf("Preço total a ser pago: R$ %.2f%n", precoTotal);
                   break;

               case 2:
                   System.out.print("Digite o valor do pagamento: ");
                   double pagamento = sc.nextDouble();

                   if (pagamento < precoTotal) {
                       System.out.printf("Pagamento insuficiente. Faltam R$ %.2f%n", (precoTotal - pagamento));
                   } else {
                       double troco = pagamento - precoTotal;
                       System.out.printf("Troco: R$ %.2f%n", troco);
                   }
                   break;

               case 3:
                   System.out.println("Encerrando o programa...");
                   break;

               default:
                   System.out.println("Opção inválida.");
           }

       } while (opcao != 3);
   }
	
}





