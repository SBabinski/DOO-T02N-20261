import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class CalculadoraGabrielinha {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int escolha;
        int totalPlantasVendidas = 0;
        
        double totalVendas = 0;
        double totalDescontos = 0;
       
        HashMap<String, Integer> vendasPorData = new HashMap<>();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            
            System.out.print("ESCOLHA ALGUMA OPCAO"); 
            System.out.println("1 - TOTAL");
            System.out.println("2 - TROCO");
            System.out.println("3 - REGISTRO DE VENDAS");
            System.out.println("4 - FECHAR");
            System.out.println("5 - SALVAR VENDA POR DATA");
            System.out.println("6 - VER VENDA POR DATA");
            
            escolha = entrada.nextInt();  
            entrada.nextLine(); 

        if (escolha == 1) {
             System.out.print("QUANTIDADE =");   
                int qtd = entrada.nextInt();
             System.out.print("PRECO =");
               
             double preco = entrada.nextDouble();  
             double total = qtd * preco;
             double desconto = 0;

        if (qtd > 10) {
                    desconto = total * 0.05;
                    total -= desconto;
             System.out.println("DESCONTO DE 5% ADICIONADO");
                }

                totalPlantasVendidas += qtd;
                totalVendas += total;
                totalDescontos += desconto;

                System.out.println("TOTAL = R$" + total);

            } else if (escolha == 2) {
             System.out.print("TOTAL PAGO =");
                double pago = entrada.nextDouble();
             System.out.print("VALOR COMPRA =");
                double compra = entrada.nextDouble();
                double troco = pago - compra;
 
             System.out.println("TROCO = R$ " + troco);
            
            } else if (escolha == 3) {
             System.out.println("------------- REGISTRO DE VENDAS -------------");
             System.out.println("PLANTAS VENDIDAS: " + totalPlantasVendidas);
             System.out.println("TOTAL VENDAS: R$ " + totalVendas);
             System.out.println("DESCONTOS APLICADOS: R$ " + totalDescontos);
             System.out.println("----------------------------------------------");

            } else if (escolha == 4) {
             System.out.println("FECHAR");
                break;

            } else if (escolha == 5) {
             System.out.print("DIGITE A DATA (dd/MM/yyyy): ");
             String data = entrada.nextLine();

             System.out.print("QUANTIDADE: ");
             int qtd = entrada.nextInt();

         try {
                LocalDate dataFormatada = LocalDate.parse(data, formato);
                String chave = dataFormatada.format(formato);

             if (vendasPorData.containsKey(chave)) {
                    vendasPorData.put(chave, vendasPorData.get(chave) + qtd);
                } else {
                    vendasPorData.put(chave, qtd);
                }

             System.out.println("SALVO!");
             } catch (Exception e) {
             System.out.println("DATA ERRADA");
   
             }

            } else if (escolha == 6) {
             System.out.print("DIGITE A DATA (dd/MM/yyyy): ");
             String data = entrada.nextLine();

         try {
                LocalDate dataFormatada = LocalDate.parse(data, formato);
                String chave = dataFormatada.format(formato);

             if (vendasPorData.containsKey(chave)) {
                    System.out.println("VENDAS: " + vendasPorData.get(chave));
                } else {
             System.out.println("NENHUMA VENDA NESSE DIA");
                }

             } catch (Exception e) {
             System.out.println("DATA ERRADA"); 
             }
             

            } else {
             System.out.println("INVALIDO");
             System.out.println();
            }
        }

        entrada.close();
    }
}