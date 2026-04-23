package aula6;

import java.util.Scanner;
import java.util.ArrayList;

public class Interface {

    Scanner scan = new Scanner(System.in);
    Calculadora calculadora = new Calculadora();
    Registro registro = new Registro();

    ArrayList<Vendedor> vendedores = new ArrayList<>();

    public void progInterface(){
        
        int opcao, idade;
        double total, troco, salarioBase;
        String diaS, nome, cidade, rua, loja;

        while (true){

            System.out.println("\n");

            System.out.println("[1] - Calcular Preço Total\n" +
                               "[2] - Calcular Troco\n" +
                               "[3] - Registro de Vendas\n" +
                               "[4] - Vendedores\n" +
                               "[5] - Sair\n");

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

                    System.out.println("[1] - Cadastrar Vendedor\n" +
                                       "[2] - Mostrar Vendedores\n" +
                                       "[3] - Calcular Médias de Salários\n" +
                                       "[4] - Calcular Bônus\n" +
                                       "[5] - Alterar Dados\n" +
                                       "[6] - Voltar\n");
                    
                    opcao = scan.nextInt();

                    if (opcao == 1){

                        System.out.println("\nDigite o nome: ");
                        nome = scan.nextLine();

                        System.out.println("\nDigite a idade: ");
                        idade = scan.nextInt();

                        scan.nextLine();

                        System.out.println("\nDigite o nome da loja: ");
                        loja = scan.nextLine();

                        System.out.println("\nDigite o nome da cidade: ");
                        cidade = scan.nextLine();

                        System.out.println("\nDigite o nome da rua: ");
                        rua = scan.nextLine();

                        System.out.println("\nDigite o salário base: ");
                        salarioBase = scan.nextDouble();

                        scan.nextLine();
                        
                        Vendedor vendedor = new Vendedor(nome, idade, loja, cidade, rua, salarioBase);

                        vendedores.add(vendedor);

                        break;
                    }

                    if (opcao == 2){

                        for (Vendedor vend : vendedores){

                            vend.apresentarse();
                            break;
                        }
                    }

                    if (opcao == 3){

                        for (Vendedor vend : vendedores){

                            vend.calcularMedia();
                            break;
                        }
                    }

                    if (opcao == 4){

                        for (Vendedor vend : vendedores){

                            vend.calcularBonus();
                            break;
                        }
                    }

                    if (opcao == 5){
                        
                        System.out.println("\nDigite o nome do vendedor a ter os dados alterados: ");
                        

                        System.out.println("[1] - Alterar Nome\n" +
                                           "[2] - Alterar Idade\n" +
                                           "[3] - Alterar Loja\n" +
                                           "[4] - Alterar Cidade\n" +
                                           "[5] - Alterar Rua\n" +
                                           "[6] - Alterar Salário Base\n" +
                                           "[7] - Voltar");
                    }

                    if (opcao == 6){

                        System.out.println("\nVoltando...");
                        break;
                    }

                    if (opcao < 1 || opcao > 6){

                        System.out.println("\nOpção Inválida!");
                        break;
                    }

                case 5:

                    System.out.println("Saindo...");

                    return;
                
                default:

                    System.out.println("Opção Inválida!");
            }
        }
    }
}