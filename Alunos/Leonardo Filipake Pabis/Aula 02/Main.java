import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static int opcao = 1000;
    public static void main(String[] args){
            Menu();

    }
    
    public static void Menu(){
        System.out.println("********");
        System.out.println("* MENU *");
        System.out.println("********");
        System.out.println("1 - Cálculo de preço total");
        System.out.println("2 - Cálculo de troco");
        System.out.println("3 - Sair");
        
        do{
            System.out.println("Digite uma opção válida");
            opcao = scan.nextInt();
            scan.nextLine();
            if (opcao == 1){
                CalculoPrecoTotal();
            }else if (opcao == 2){
                CalculoDeTroco();
            }else if (opcao == 3){
                Sair();
            }
        }while (opcao != 3);

    }
    public static void CalculoPrecoTotal(){
        System.out.println("Digite a quantidade da planta vendida:");
        int quantidade = scan.nextInt();
        scan.nextLine();
        System.out.println("Digite o preço unitário da planta:");
        double preco = scan.nextDouble();
        scan.nextLine();
        System.out.printf("Preço Total: %.2f\n", quantidade*preco);
        VoltarMenu();
    }
    public static void CalculoDeTroco(){
        System.out.println("Valor recebido pelo cliente:");
        double valor = scan.nextDouble();
        scan.nextLine();
        System.out.println("Valor total da compra:");
        double total = scan.nextDouble();
        scan.nextLine();
        if (valor-total < 0){
            System.out.println("Valor insuficiênte, tente novamente");
            CalculoDeTroco();
        }else{
            System.out.printf("Troco a ser devolvido: %.2f\n", valor-total);
            VoltarMenu();
        }
    }

    public static void VoltarMenu(){
        System.out.println("Pressione enter para voltar ao menu");
        scan.nextLine();
        Menu();
    }

    public static void Sair(){
        System.out.println("Finalizando o systema");
    }
}
