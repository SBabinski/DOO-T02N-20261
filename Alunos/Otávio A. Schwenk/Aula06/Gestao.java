import Objetos.Vendedor;
import Objetos.Loja;
import Objetos.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestao {
    
    public static List<Vendedor> funcionarios = new ArrayList<>();
    public static List<Loja> lojas = new ArrayList<>();
    public static List<Cliente> clientes = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static void mostrarMenuLojas(){
        int escolha = 0;
        do{
            System.out.println("--------------------MENU--------------------");
            System.out.println("[1] - Listar");
            System.out.println("[0] - Voltar");

            escolha = scan.nextInt();
            scan.nextLine();
            if(escolha == 1){
                listarLojas();
            }else if(escolha == 0){
                
            }else{
                System.out.println("ATENCAO: Escolha uma opcao valida!");
                mostrarMenuLojas();
            }
        }while(escolha!=0);
    }

    public static void mostrarMenuFuncionarios(){
        int escolha = 0;
        do{
            System.out.println("--------------------MENU--------------------");
            System.out.println("[1] - Listar");
            System.out.println("[2] - Listar Funcionarios por loja");
            System.out.println("[3] - Calcular Bônus");
            System.out.println("[4] - Calcular Média");
            System.out.println("[0] - Voltar");

            escolha = scan.nextInt();
            scan.nextLine();
            validarEscolhaGestaoFuncionarios(escolha);
        }while(escolha!=0);
    }

    public static void validarEscolhaGestaoFuncionarios(int escolha){
        switch(escolha){
            case 1:
                listarVendedor();
                break;
            case 2:
                listarVendedoresPorLoja();
                break;
            case 3:
                calcularBonus();
                break;
            case 4:
                calcularMedia();
            case 0:
                break;
            default:
                System.out.println("ERRO: Favor escolher uma opcao valida!");
                break;
        }
    }

    public static void listarLojas(){
        for(int i = 0; i < lojas.size(); i++){
            System.out.printf("%d - ", i+1);
            lojas.get(i).apresentarSe();
        }
    }

    public static void listarVendedor(){
        for(int i = 0; i < funcionarios.size(); i++){
            System.out.printf("%d - ", i+1);
            funcionarios.get(i).apresentarSe();
        }
    }

    public static void listarVendedoresPorLoja(){
        int escolha = 0;
        listarLojas();
        do{
            System.out.println("De qual loja deseja listar?");
            escolha = scan.nextInt()-1;
            if(escolha <0 && escolha > lojas.size()){
                System.out.println("ATENCAO: Escolha uma opcao valida!");
            }
        }while(escolha <0 && escolha > lojas.size());
        System.out.printf("Vendedores da loja %s\n", lojas.get(escolha).getNomeFantasia());
        lojas.get(escolha).contarVendedores();
    }

    public static void calcularBonus(){
        int escolha = 0;
        System.out.println("De qual funcionario deseja calcular o bônus?");
        listarVendedor();
        escolha = scan.nextInt()-1;
        funcionarios.get(escolha).calcularBonus();
    }

    public static void calcularMedia(){
        int escolha = 0;
        System.out.println("De qual funcionario deseja calcular a media?");
        listarVendedor();
        escolha = scan.nextInt()-1;
        funcionarios.get(escolha).calcularMedia();
    }

    public static void mostrarMenuClientes(){
        int escolha = 0;
        do{
            System.out.println("--------------------MENU--------------------");
            System.out.println("[1] - Listar");
            System.out.println("[2] - Listar Clientes por loja");
            System.out.println("[0] - Voltar");

            escolha = scan.nextInt();
            scan.nextLine();
            validarEscolhaGestaoClientes(escolha);
        }while(escolha!=0);
    }

    public static void validarEscolhaGestaoClientes(int escolha){
        switch(escolha){
            case 1:
                listarClientes();
                break;
            case 2:
                listarClientesPorLoja();
                break;
            case 0:
                break;
            default:
                System.out.println("ERRO: Favor escolher uma opcao valida!");
                break;
        }
    }

    public static void listarClientes(){
        for(int i = 0; i < clientes.size(); i++){
            System.out.printf("%d - ", i+1);
            clientes.get(i).apresentarSe();
        }
    }

    public static void listarClientesPorLoja(){
        int escolha = 0;
        listarLojas();
        do{
            System.out.println("De qual loja deseja listar?");
            escolha = scan.nextInt()-1;
            if(escolha <0 && escolha > lojas.size()){
                System.out.println("ATENCAO: Escolha uma opcao valida!");
            }
        }while(escolha <0 && escolha > lojas.size());
        System.out.printf("Clientes da loja %s\n", lojas.get(escolha).getNomeFantasia());
        lojas.get(escolha).contarClientes();
    }
}
