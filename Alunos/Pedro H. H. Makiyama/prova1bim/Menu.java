package prova1bim;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    Registro registro = new Registro();

    public void mostrarMenu(){

        String cliente, placa;
        int numeroLocacoes = 10, cpf, cnh;
        int opcao;
        boolean temArCond;

        registro.registrarCarro("abc123", true);
        registro.registrarMoto("abc321", 10);
        registro.registrarCliente("pedro1", 123, 321);
        registro.registrarCliente("pedro2", 1234, 4321);
        registro.registrarLocacao("pedro1", "abc123", 3, 1, "01/01/2000", 30);
        registro.registrarLocacao("pedro2", "abc321", 4, 2, "02/01/2000", 30);

        while(true){
            
            System.out.println("1 - Listar locações sem devolução\n 2 - Cadastrar cliente\n 3 - Cadastrar veículo\n 4 - Cadastrar locação\n 5 - Realizar devolução \n6 - Sair");
            

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){

                case 1: 

                    registro.listarSemDevolucao();
                    break;
                
                case 2: 

                    System.out.println("\nNome: ");
                    cliente = scanner.nextLine();

                    System.out.println("\nCPF: ");
                    cpf = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\nCNH: ");
                    cnh = scanner.nextInt();
                    scanner.nextLine();

                    registro.registrarCliente(cliente , cpf, cnh);

                    break;
                
                case 3: 

                    System.out.println("\nPlaca: ");
                    placa = scanner.nextLine();

                    System.out.println("\nTem ar condicionado: ");
                    temArCond = scanner.nextBoolean();

                    registro.registrarCarro(placa, temArCond);

                    break;
                    
                case 5: 

                    System.out.println("\nCliente: ");
                    cliente = scanner.nextLine();

                    System.out.println("\nPlaca: ");
                    placa = scanner.nextLine();

                    registro.realizarDevolucao(cliente, placa);

                    break;

                case 6:

                    return;

                default:

                    System.out.println("\nOpcao invalida");
                    break;
            }
        }
    }
}
