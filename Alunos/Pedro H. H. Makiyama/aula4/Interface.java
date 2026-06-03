package aula4;

import java.util.Scanner;

public class Interface {

    Scanner scan = new Scanner(System.in);
    Calculadora calculadora = new Calculadora();
    Registro registro = new Registro();

    public void progInterface(){
        
        int opcao;
        double total, troco;
        String diaS;

        while (true){

            System.out.println("[1] - Calcular Preço Total\n" +
                               "[2] - Calcular Troco\n" +
                               "[3] - Registro de Vendas\n" +
                               "[4] - Sair\n");

            opcao = scan.nextInt();

            scan.nextLine();

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

                    scan.nextLine();

                    if (opcao == 1){

                        registro.registrarVenda();
                        break;
                    }
                    
                    if (opcao == 2){

                        System.out.println("[1] - Listar Todas as Vendas\n" +
                                           "[2] - Listar Vendas de um Dia\n" +
                                           "[3] - Listar Vendas de um Mês\n" +
                                           "[4] - Voltar\n");    

                        opcao = scan.nextInt();
                        
                        scan.nextLine();
                        
                        if (opcao == 1){

                            registro.listarVendas();
                            break;
                        }

                        if (opcao == 2){

                            System.out.println("Digite a data do dia: ");
                            diaS = scan.nextLine();

                            registro.listarVendasDia(diaS);

                            break;
                        }            
                            
                        if (opcao == 3){

                            System.out.println("Digite o mês: ");
                            int mes = scan.nextInt();
                            
                            System.out.println("\nDigite o ano: ");
                            int ano = scan.nextInt();
                            
                            scan.nextLine();

                            registro.listarVendasMes(mes, ano);

                            break;
                        }

                        if (opcao == 4){

                            System.out.println("Voltando...\n");
                            break;
                        }
                            
                        if (opcao < 1 && opcao > 4){
              
                            System.out.println("Opção Inválida!\n");
                            break;
                        }
                    }

                    if (opcao == 3){

                        System.out.println("Voltando...\n");
                        break;
                    }

                    if (opcao < 1 || opcao > 3){

                        System.out.println("Opção Inválida!\n");
                        break;
                    }
                    
                case 4:

                    System.out.println("Saindo...");

                    return;
                
                default:

                    System.out.println("Opção Inválida!");
            }
        }
    }
}