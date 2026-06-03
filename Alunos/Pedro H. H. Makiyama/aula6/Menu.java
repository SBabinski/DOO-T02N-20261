package aula6;

import java.util.Scanner;

public class Menu {

    int opcao;
    int idade;
    double total;
    double troco;
    double salariobase;
    String diaS;
    String nome;
    String cidade;
    String bairro;
    String rua;
    String loja;

    Scanner scan = new Scanner(System.in);
    Calculadora calculadora = new Calculadora();
    private Registro registro = new Registro();
    private Loja loja1 = new Loja("nomefant1", "razsoc1", 123, "cidade1", "bairro1", "rua1");    

    public void progMenu(){
        
        while (true){

            System.out.println("\n");

            System.out.println("[1] - Calcular Preço Total\n" +
                               "[2] - Calcular Troco\n" +
                               "[3] - Registro de Vendas\n" +
                               "[4] - Vendedores\n" +
                               "[5] - Clientes\n" +
                               "[6] - Sair\n");

            opcao = scan.nextInt();

            scan.nextLine();

            switch (opcao){

                case 1:

                    total = calculadora.Total();

                    System.out.println("Total: " + (double) total);

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

                    System.out.println("[1] - Contar Vendedores\n" +
                                       "[2] - Cadastrar Vendedor\n" +
                                       "[3] - Mostrar Vendedores\n" +
                                       "[4] - Calcular Médias de Salários\n" +
                                       "[5] - Calcular Bônus\n" +
                                       "[6] - Alterar Dados\n" +
                                       "[7] - Voltar\n");
                    
                    opcao = scan.nextInt();

                    scan.nextLine();
                    
                    if (opcao == 1){

                        System.out.println("\nQuantidade de vendedores: " + loja1.contarVendedores());

                        break;
                    }
                    
                    if (opcao == 2){

                        System.out.println("\nDigite o nome: ");
                        nome = scan.nextLine();

                        System.out.println("\nDigite a idade: ");
                        idade = scan.nextInt();

                        scan.nextLine();

                        System.out.println("\nDigite o nome da loja: ");
                        loja = scan.nextLine();

                        System.out.println("\nDigite o nome da cidade: ");
                        cidade = scan.nextLine();

                        System.out.println("\nDigite o nome do bairro: ");
                        bairro = scan.nextLine();

                        System.out.println("\nDigite o nome da rua: ");
                        rua = scan.nextLine();

                        System.out.println("\nDigite o salário base: ");
                        salariobase = scan.nextDouble();

                        scan.nextLine();
                        
                        loja1.cadastrarVendedor(nome, idade, loja, cidade, bairro, rua, salariobase);

                        break;
                    }

                    if (opcao == 3){

                        loja1.apresentarVendedores();

                        break;
                    }

                    if (opcao == 4){

                        loja1.apresentarMediasSalarios();

                        break;
                    }

                    if (opcao == 5){

                        loja1.apresentarBonus();
                        
                        break;
                    }

                    if (opcao == 6){
                        
                        System.out.println("\nDigite o nome do vendedor a ter os dados alterados: ");
                        nome = scan.nextLine();

                        for (Vendedor vended : loja1.getVendedores()){

                            if (vended.getNome().equals(nome)){
                                
                                System.out.println("[1] - Alterar Nome\n" +
                                                   "[2] - Alterar Idade\n" +
                                                   "[3] - Alterar Loja\n" +
                                                   "[4] - Alterar Cidade\n" +
                                                   "[5] - Alterar Bairro\n" +
                                                   "[6] - Alterar Rua\n" +
                                                   "[7] - Alterar Salário Base\n" +
                                                   "[8] - Voltar\n");
                                
                                opcao = scan.nextInt();

                                scan.nextLine();

                                if (opcao == 1){

                                    System.out.println("\nDigite o novo nome: ");
                                    nome = scan.nextLine();

                                    vended.setNome(nome);

                                    break;
                                }

                                if (opcao == 2){

                                    System.out.println("\nDigite a nova idade: ");
                                    idade = scan.nextInt();

                                    scan.nextLine();

                                    vended.setIdade(idade);

                                    break;
                                }

                                if (opcao == 3){

                                    System.out.println("\nDigite a nova loja do vendedor: ");
                                    loja = scan.nextLine();

                                    vended.setLoja(loja);

                                    break;
                                }

                                if (opcao == 4){

                                    System.out.println("\nDigite a nova cidade: ");
                                    cidade = scan.nextLine();

                                    vended.setCidade(cidade);

                                    break;
                                }

                                if (opcao == 5){

                                    System.out.println("\nDigite o novo bairro: ");
                                    bairro = scan.nextLine();

                                    vended.setBairro(bairro);

                                    break;
                                }

                                if (opcao == 6){

                                    System.out.println("\nDigite a nova rua: ");
                                    rua = scan.nextLine();

                                    vended.setRua(rua);

                                    break;
                                }

                                if (opcao == 7){

                                    System.out.println("\nDigite o novo salário base: ");
                                    salariobase = scan.nextDouble();

                                    scan.nextLine();

                                    vended.setSalarioBase(salariobase);

                                    break;
                                }

                                if (opcao == 8){

                                    System.out.println("\nVoltando...");

                                    break;
                                    
                                }

                                if (opcao < 1 && opcao > 8){

                                    System.out.println("\nOpção inválida!");

                                    break;
                                }
                            }
                        }

                        break;
                    }

                    if (opcao == 7){

                        System.out.println("\nVoltando...");
                        break;
                    }

                    if (opcao < 1 || opcao > 7){

                        System.out.println("\nOpção Inválida!");
                        break;
                    }

                case 5:

                    System.out.println("[1] - Contar Clientes\n" +
                                       "[2] - Cadastrar Cliente\n" +
                                       "[3] - Mostrar Clientes\n" +
                                       "[4] - Alterar Dados\n" +
                                       "[5] - Voltar\n");
                    
                    opcao = scan.nextInt();
                    
                    scan.nextLine();

                    if (opcao == 1){

                        System.out.println("\nQuantidade de clientes: " + loja1.contarClientes());

                        break;
                    }
                    
                    if (opcao == 2){

                        System.out.println("\nDigite o nome do cliente: ");
                        nome = scan.nextLine();

                        System.out.println("\nDigite a idade do cliente: ");
                        idade = scan.nextInt();
                        
                        scan.nextLine();

                        System.out.println("\nDigite a cidade do cliente: ");
                        cidade = scan.nextLine();

                        System.out.println("\nDigite o bairro do cliente: ");
                        bairro = scan.nextLine();

                        System.out.println("\nDigite a rua do cliente: ");
                        rua = scan.nextLine();

                        loja1.cadastrarCliente(nome, idade, cidade, bairro, rua);

                        break;
                    }

                    if (opcao == 3){

                        loja1.apresentarClientes();

                        break;
                    }

                    if (opcao == 4){

                        System.out.println("\nDigite o nome do cliente a ter os dados alterados: ");
                        nome = scan.nextLine();

                        for (Cliente client : loja1.getClientes()){
                            
                            if (client.getNome().equals(nome)){

                                System.out.println("[1] - Alterar nome\n" +
                                                   "[2] - Alterar idade\n" +
                                                   "[3] - Alterar cidade\n" +
                                                   "[4] - Alterar bairro\n" +
                                                   "[5] - Alterar rua\n" +
                                                   "[6] - Voltar\n");
                                
                                opcao = scan.nextInt();

                                scan.nextLine();

                                if (opcao == 1){

                                    System.out.println("\nDigite o novo nome: ");
                                    nome = scan.nextLine();

                                    client.setNome(nome);

                                    break;
                                }

                                if (opcao == 2){

                                    System.out.println("\nDigite a nova idade: ");
                                    idade = scan.nextInt();

                                    scan.nextLine();

                                    client.setIdade(idade);

                                    break;
                                }

                                if (opcao == 3){

                                    System.out.println("\nDigite a nova cidade: ");
                                    cidade = scan.nextLine();

                                    client.setCidade(cidade);

                                    break;
                                }

                                if (opcao == 4){

                                    System.out.println("\nDigite o novo bairro: ");
                                    bairro = scan.nextLine();

                                    client.setBairro(bairro);

                                    break;
                                }

                                if (opcao == 5){

                                    System.out.println("\nDigite a nova rua: ");
                                    rua = scan.nextLine();

                                    client.setRua(rua);

                                    break;
                                }

                                if (opcao == 6){

                                    System.out.println("\nVoltando...");

                                    break;
                                }

                                if (opcao < 1 || opcao > 6){

                                    System.out.println("\nOpção inválida!");

                                    break;
                                }
                            }
                        }

                        break;
                    }
                    
                    if (opcao == 5){

                        System.out.println("\nVoltando...");

                        break;
                    }
                    
                    if (opcao < 1 || opcao > 5){

                        System.out.println("\nOpção inválida!");

                        break;
                    }

                case 6:

                    System.out.println("Saindo...");

                    return;
                
                default:

                    System.out.println("Opção Inválida!");
            }
        }
    }
}