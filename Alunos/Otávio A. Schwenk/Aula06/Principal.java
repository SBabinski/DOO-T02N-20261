import java.util.Scanner;

public class Principal {

    static Scanner scan = new Scanner(System.in);

    public static void main (String [] args){
        Populadores.popularObjetos();
        chamarBoasVindas();
        chamarMenuPrincipal();
    }
    
    public static void chamarBoasVindas(){
        System.out.println("-----------------------MY PLANT-----------------------");
        System.out.println("Seja Bem-Vindo a calculadora da loja de plantas!");
        System.out.println("Feito para gerenciar da Loja da Dona Gabrielinha");
        System.out.println("Basta escolher o item desejado do menu!");
        System.out.println("Controle de vendas e pessoas");
    }

    public static void chamarMenuPrincipal(){
        int escolha = 0;
        do{
            System.out.println("--------------------MENU--------------------");
            System.out.println("[1] - Gerenciar Vendas");
            System.out.println("[2] - Gerenciar Loja");
            System.out.println("[0] - Sair");

            escolha = scan.nextInt();
            scan.nextLine();
            validarEscolhaPrincipal(escolha);
        }while(escolha!=0);
    }

    public static void validarEscolhaPrincipal(int escolha){
        switch(escolha){
            case 1:
                chamarMenuVendas();
                break;
            case 2:
                chamarMenuGestao();
                break;
            case 0:
                System.out.println("Ate a proxima!");
                System.out.println("---------------------FIM---------------------");
                break;
            default:
                System.out.println("ERRO: Favor escolher uma opcao valida!");
                break;
        }
    }

    public static void chamarMenuVendas(){
        int escolha = 0;
        do{
            System.out.println("--------------------MENU--------------------");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco ");
            System.out.println("[3] - Registro de Vendas");
            System.out.println("[0] - Voltar");

            escolha = scan.nextInt();
            scan.nextLine();
            validarEscolhaVendas(escolha);
        }while(escolha!=0);
    }

    public static void validarEscolhaVendas(int escolha){
        switch(escolha){
            case 1:
                Vendas.calcularPrecoTotal();
                break;
            case 2:
                Vendas.calcularTroco();
                break;
            case 3:
                Vendas.mostrarMenuRegisto();
                break;
            case 0:
                break;
            default:
                System.out.println("ERRO: Favor escolher uma opcao valida!");
                break;
        }
    }

    public static void chamarMenuGestao(){
        int escolha = 0;
        do{
            System.out.println("--------------------MENU--------------------");
            System.out.println("[1] - Lojas");
            System.out.println("[2] - Funcionarios");
            System.out.println("[3] - Clientes");
            System.out.println("[0] - Voltar");

            escolha = scan.nextInt();
            scan.nextLine();
            validarEscolhaGestao(escolha);
        }while(escolha!=0);
    }

    public static void validarEscolhaGestao(int escolha){
        switch(escolha){
            case 1:
                Gestao.mostrarMenuLojas();
                break;
            case 2:
                Gestao.mostrarMenuFuncionarios();
                break;
            case 3:
                Gestao.mostrarMenuClientes();
                break;
            case 0:
                break;
            default:
                System.out.println("ERRO: Favor escolher uma opcao valida!");
                break;
        }
    }
}