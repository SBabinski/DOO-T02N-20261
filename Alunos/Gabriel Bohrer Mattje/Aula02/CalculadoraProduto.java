import java.util.Scanner;

public class CalculadoraProduto {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        mostrarMenu();
    }

    public static void mostrarMenu() {
        int op = -1;
        do {
            System.out.println("----Menu Calculadora----");
            System.out.println("1- Calcular Valor Total");
            System.out.println("2- Calcular Troco");
            System.out.println("0- Sair");
            op = scan.nextInt();
            scan.nextLine();
            validarEscolha(op);
        } while (op != 0);
        System.out.println("Sistema Encerrado!");
    }
    public static void validarEscolha(int op){
        switch (op) {
            case 1:
                calcularValorTotal();
                break;

            case 2:
                calcularTroco();
                break;

            case 0:
                break;
            default:
                System.out.println("Opção Inválida! Digite Novamente!");
        }
    }
    public static void calcularValorTotal(){
        System.out.println("Valor do Produto: ");
        double valor = scan.nextDouble();
        System.out.println("Quantidade do Produto: ");
        int quantidade = scan.nextInt();
        scan.nextLine();
        System.out.println("Valor Total: " + (valor * quantidade));
    }
    public static void calcularTroco(){
        System.out.println("Valor da Compra: ");
        double compra = scan.nextDouble();
        System.out.println("Valor Entregue pelo Cliente: ");
        double valorCliente = scan.nextDouble();
        double troco = valorCliente - compra;
        if (troco>0){
            System.out.println("Valor do Troco:"+troco);
        }
        else if (troco == 0) {
            System.out.println("Valor Correto! Sem Necessidade de Troco");

        } else{
            System.out.println("Faltando um Total de " + (troco*-1) + " para completar a compra.");
        }

    }
}
