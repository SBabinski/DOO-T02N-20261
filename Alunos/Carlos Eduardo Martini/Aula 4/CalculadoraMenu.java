import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalculadoraMenu {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Historico> historicoVendas = new ArrayList<>();
    

    public static void main(String[] args) {
        Menu();
        scan.close();
    }

    public static void ValidarResp(int resp) {
        if (resp == 1) {
            PreçoTotal();
        } else if (resp == 2) {
            CalcularTroco();
        } else if (resp == 3) {
            exibirHistorico();
        }
    }

    public static void Menu() {
        int resp = 1000;
        do {
            System.out.println("-----------Menu-----------");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Historico");
            System.out.println("[4] - Sair");
            resp = scan.nextInt();
            scan.nextLine();
            ValidarResp(resp);
        } while (resp != 4);
        System.out.println("Obrigado por utilizar a calculadora!!!");
    }

    /**
     * 
     */
    public static void exibirHistorico() {
        double totalVendasDia = 0;
        System.out.println("-----Historico-de-Vendas-----");
        

        if(historicoVendas.isEmpty()){
            System.out.println("nenhuma venda registrada");
        }else{
            System.out.println("insira o dia da venda:");
            int diaH = scan.nextInt();
            scan.nextLine();
            System.out.println("insira o mes da venda:");
            int mesH = scan.nextInt();
            scan.nextLine();
            for(Historico novaVenda : historicoVendas){
                if(novaVenda.hoje.getDayOfMonth() == diaH && novaVenda.hoje.getMonthValue() == mesH){
                    System.out.printf("Quantidade: %d, Total: %.2f, Desconto: %.2f%n", novaVenda.quantPlant, novaVenda.resultTotal, novaVenda.valorDesc);
                    if(novaVenda.valorDesc == 0){
                        totalVendasDia = totalVendasDia + novaVenda.resultTotal;
                    }else{
                        totalVendasDia = totalVendasDia + novaVenda.valorDesc;
                    } 
                    
                }                
            }
            System.out.println("total das vendas desse dia: " +totalVendasDia);
         }
    }

    public static void PreçoTotal() {
        

        System.out.println("-----Calcular-Preço-Total-----");
        System.out.println("Adicione a quantidade de plantas vendidas");
        int quantPlant = scan.nextInt();
        scan.nextLine();
        System.out.println("Adicione o preco da plantas vendida");
        double precoPlant = scan.nextDouble();
        scan.nextLine();
        double resultTotal = quantPlant * precoPlant;
        double valorDesc;
        LocalDate hoje = LocalDate.now();
        if(quantPlant < 10){
            System.out.printf("O preco total da venda sera %.2f%n", resultTotal);
            valorDesc = 0;
        }else{
            valorDesc = resultTotal * 0.05;
            valorDesc = resultTotal - valorDesc;
            System.out.printf("O original da venda seria %.2f%n", resultTotal);
            System.out.printf("Com desconto fica %.2f%n", valorDesc);
        }

        registrarVenda(quantPlant, resultTotal, valorDesc, hoje);
    }

    public static void CalcularTroco() {

        System.out.println("-----Calcular-Troco-----");
        System.out.println("O preco que o cliente pagou");
        double precoCliente = scan.nextDouble();
        scan.nextLine();
        System.out.println("adicione o total da venda");
        double precoTotal = scan.nextDouble();
        scan.nextLine();

        double troco = precoCliente - precoTotal;
        if (troco > 0) {
            System.out.printf("Voce deve %.2f de troco!%n", troco);
        } else if (troco < 0) {
            System.out.printf("O cliente ainda deve %.2f%n", troco);
        } else if (troco == 0) {
            System.out.println("Ninguem deve nada!!!");
        }
    }

    public static void registrarVenda(int quantPlant, double resultTotal, double valorDesc, LocalDate hoje) {
        Historico novaVenda = new Historico(quantPlant, resultTotal, valorDesc, hoje);
        historicoVendas.add(novaVenda);
    }
}