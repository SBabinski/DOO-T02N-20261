package aula3;

import java.util.Scanner;

public class Interface {

    Scanner scan = new Scanner(System.in);
    Calculadora calculadora = new Calculadora();
    Registro registro = new Registro();

    public void progInterface(){
        
        int opcao;
        double total, troco;

        while (true){

            System.out.println("[1] - Calcular Preço Total\n" +
                               "[2] - Calcular Troco\n" +
                               "[3] - Registro de Vendas\n" +
                               "[4] - Sai\n");

            opcao = scan.nextInt();

            switch (opcao){

                case 1:

                    total = calculadora.Total();

                    System.out.println("Total: " + total);

                    break;

                case 2:

                    troco = calculadora.Troco();

                    System.out.println("Troco: " + troco);

                    break;

                case 3:

                    System.out.println("[1] - Registrar Venda\n"+
                                       "[2] - Consultrar Histórico de Vendas\n" +
                                       "[3] - Voltar\n");

                    opcao = scan.nextInt();

                    if (opcao == 1)

                        registro.registrarVenda();
                    
                    if (opcao == 2)

                        registro.listarVendas();

                    if (opcao == 3)

                        break;
                    
                    if (opcao < 1 || opcao > 3)

                        System.out.println("Opção Inválida!\n");
                    
                    break;
                    
                case 4:

                    System.out.println("Saindo...");

                    return;
                
                default:

                    System.out.println("Opção Inválida!");
            }
        }
    }
}