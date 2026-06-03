import java.util.Scanner;
public class Main{
    public static float totalCompra = 0;
    public static int quantidadeTotal=0;
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
    }
    public static void menu(){
        int escolha = 0;
        while (escolha !=3 ){
            System.out.println("[1]-Calcular preco total");
            System.out.println("[2]-Calcular troco");
            System.out.println("[3]-Sair");
            escolha = scan.nextInt();
            scan.nextLine();
            validarEscolha(escolha);
        }
    }
    public static void validarEscolha(int escolha){
        switch (escolha) {
            case 1:
                totalCompra += calcularPreco(pedirQuantidade(),pedirValor());
                break;
            
            case 2:
                calcularTroco();
                break;
            default:
                if (escolha!=3){
                    System.out.println("digite uma opcao valida");
                }
                break;
        }
    }
    public static void calcularTroco(){
        float troco = 0;
        if (totalCompra <= 0){
            System.out.println("parece que voce ainda nao comprou nada, volte aqui depois de comprar algo :)");
            totalCompra=0;
            return;
        }
        System.out.printf("voce comprou %d plantas \n",quantidadeTotal);
        System.out.println("o total da compra ficou: R$"+totalCompra);
        System.out.println("digite o valor que deseja entregar ao caixa: ");
        float valorDado = scan.nextFloat();
        scan.nextLine();
        troco = valorDado-totalCompra;
        if (troco < 0){
            System.out.println("voce entregou um valor menor doque o valor total da compra. Compra cancelada!");
        }
        else{
            System.out.printf("Compra finalizada. Seu troco: R$ %.2f \n",troco);
            totalCompra = 0;
            quantidadeTotal = 0;
        }
    }
    public static float calcularPreco(int quantidade, float valor){
        if (verificarValor(quantidade*valor) == 0){
            quantidadeTotal-=quantidade;
        }
        return verificarValor(quantidade*valor);
    }
    public static float verificarValor(float valor){
        if (valor < 0){
            return 0;
        }
        else{
            return valor;
        }
    }
    public static int pedirQuantidade(){
        System.out.println("digite a quantidade de plantas: ");
        int quantidade = scan.nextInt();
        scan.nextLine();
        quantidadeTotal+=quantidade;
        return quantidade;
    }
    public static float pedirValor(){
        System.out.println("digite o valor da planta: ");
        float valor = scan.nextFloat();
        scan.nextLine();
        return valor;
    }
}