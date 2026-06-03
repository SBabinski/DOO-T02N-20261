import java.util.Scanner;

public class Principal {
    private static Scanner leia = new Scanner(System.in);

    public static void main(String[] args) {
        exibir_menu();
        leia.close();
    }

    public static void exibir_menu(){
        int op = 0;
        do{
            System.out.println(" === Digite a opção desejada: === ");
            System.out.println("[1] Para realizar venda.");
            System.out.println("[2] Mostrar histórico.");
            System.out.println("[3] Filtrar data.");
            System.out.println("[4] Sair.");
            op = leia.nextInt();
            validar_escolha(op);
        }while (op != 4);
    }

    public static void validar_escolha(int op){
        switch (op) {
            case 1:
                Metodo.realizar_venda(leia);
                break;
            
            case 2:
                Metodo.mostrar_historico();
                break;

            case 3:
                Metodo.filtrar_data(leia);
                break;

            case 4:
                System.out.println("Obrigado por utilizar o sistema!");
                break;
        
            default:
                System.out.println("Digite uma opção válida!");
                break;
        }
    }
}