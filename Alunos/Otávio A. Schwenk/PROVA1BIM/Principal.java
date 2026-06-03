import java.util.Scanner;

public class Principal {

    public static Scanner scan = new Scanner(System.in);
 
    public static void main (String[] args){
        System.out.println("=========== Bem-Vindo ===========");
        System.out.println("===== Locadora de Veículos =====");

        chamarMenu();
    }

    public static void chamarMenu(){
        int escolha = -1;
        do{
            System.out.println("---------- MENU ----------");
            System.out.println("[1] - Cadastrar Cliente");
            System.out.println("[2] - Cadastrar Veículo");
            System.out.println("[3] - Realizar Locação");
            System.out.println("[4] - Realizar uma Devolução");
            System.out.println("[5] - Listar Locações");
            System.out.println("[0] - SAIR");

            escolha = scan.nextInt();
            validarEscolha(escolha);
        }while(escolha != 0);
    }

    public static void validarEscolha(int escolha){
        switch(escolha){
            case 1:
                Metodos.cadastrarCliente();
                break;
            case 2:
                mostrarMenuVeiculos();
                break;
            case 3:
                Metodos.realizarLocação();
                break;
            case 4:
                Metodos.realizarDevolucao();
                break;
            case 5:
                Metodos.listarLocacoesSemDevolucao();
                break;
            case 6:
                Metodos.testar();
                break;
            case 0:
                System.out.println("Até a próxima!");
                break;
            default:
                System.out.println("ERRO: Escolha uma opcao valida!");
                break;
        }
    }

    public static void mostrarMenuVeiculos(){
        int escolha = -1;
        do{
            System.out.println("---------- MENU ----------");
            System.out.println("[1] - Cadastrar Carro");
            System.out.println("[2] - Cadastrar Moto");
            System.out.println("[0] - Voltar");

            escolha = scan.nextInt();

            switch(escolha){
                case 1:
                    Metodos.cadastrarCarro();
                    break;
                case 2:
                    Metodos.cadastrarMoto();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("ERRO: Escolha uma opcao valida!");
                    break;
            }
        }while(escolha != 0);
    }
}
