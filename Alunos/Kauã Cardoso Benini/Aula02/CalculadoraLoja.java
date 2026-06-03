import java.util.Scanner;

public class CalculadoraLoja {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int opcao;

        do{
           
            System.out.println("\n=== Loja da Dona Gabrielinha ===");
            System.out.println("[1] Calcular Preço Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scan.nextInt();

            switch(opcao){
                case 1:
                    total(scan);
                    break;
                
                case 2:
                    troco(scan);
                    break;
                case 3:
                    System.out.println("Encerrando programa...");
                    break;
                
                default:
                    System.out.println("Opcao invalida!, tente novamente");
                    break;

            }
        }while(opcao!=3);

        scan.close();
    }

    public static void total(Scanner scan){
        
        System.out.println("Digite a quantidade:");
        int quantidade = scan.nextInt();

        System.out.println("Digite o valor:");
        double preco = scan.nextDouble();

        double total = preco*quantidade;

        System.out.println("Total: R$ " + total);
    }
    
    public static void troco(Scanner scan){
        System.out.println("Digite o valor pago: ");
        double pago = scan.nextDouble();

        System.out.println("Digite o valor da compra: ");
        double compra = scan.nextDouble();

        double troco = pago-compra;

        if(troco<0){
            System.out.println("Valor insuficiente, faltam: R$ "+(-troco));
        }
        else{
            System.out.println("Troco: R$ "+troco);
        }
    }
}
