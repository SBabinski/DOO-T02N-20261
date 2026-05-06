import java.util.Scanner;
import java.util.ArrayList;
public class Calculadora{
    public static ArrayList<Venda>vendas = new ArrayList<>();
    public static float totalCompra = 0;
    public static int quantidadeTotal=0;
    public static int quantidadeVendida=0;
    public static float valorTotalVendido=0;

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
    }
    public static void menu(){
        int escolha = 0;
        while (escolha !=4 ){
            System.out.println("[1]-Calcular preco total");
            System.out.println("[2]-Finalizar compra");
            System.out.println("[3]-Mostrar vendas");
            System.out.println("[4]-Sair");
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
                finalizarCompra();
                break;
            case 3:
                mostrarVendas();
                break;
            default:
                if (escolha!=4){
                    System.out.println("digite uma opcao valida");
                }
                break;
        }
    }
    public static void finalizarCompra(){
        Venda venda = new Venda();
        float desconto = 0;
        float backup = 0;
        float troco = 0;
        
        if (totalCompra <= 0){
            System.out.println("parece que voce ainda nao comprou nada, volte aqui depois de comprar algo :)");
            totalCompra=0;
            return;
        }
        System.out.printf("voce comprou %d plantas \n",quantidadeTotal);
        if (quantidadeTotal > 10){
            System.out.println("sua compra foi elegivel para um desconto de 5%");
            backup = totalCompra;
            totalCompra-=(totalCompra*0.05);
            desconto = backup-totalCompra;
            System.out.printf("o total da compra ficou: R$%.2f voce economizou R$%.2f \n",totalCompra,desconto);
        }else{
            System.out.println("o total da compra ficou: R$"+totalCompra);
        }
        troco = calcularTroco(receberValor(), totalCompra);
        if (troco < 0){
            System.out.println("voce entregou um valor menor doque o valor total da compra. Compra cancelada!");
            if (quantidadeTotal > 10){
                totalCompra = backup;
            }
        }
        else{
            System.out.printf("Compra finalizada. Seu troco: R$ %.2f \n",troco);
            venda.setQuantidade(quantidadeTotal);
            venda.setDesconto(desconto);
            venda.setValor(totalCompra);
            vendas.add(venda);
            desconto=0;
            totalCompra = 0;
            quantidadeTotal = 0;
            backup=0;
        }
    }
    public static float receberValor(){
        System.out.println("digite o valor que deseja entregar ao caixa: ");
        float valorDado = scan.nextFloat();
        scan.nextLine();
        return valorDado;
    }
    public static float calcularTroco(float valorDado,float valorCompra){
        return valorCompra-valorDado;
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
    public static void mostrarVendas(){
        for(int i=0;i<vendas.size();i++){
            System.out.printf("%d - ",i+1);
            System.out.println(vendas.get(i));
        }
    }
}