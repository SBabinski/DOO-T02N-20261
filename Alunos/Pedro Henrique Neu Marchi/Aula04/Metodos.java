import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Metodos {
   static Scanner leitor = new Scanner(System.in);
   static ArrayList<Vendas> vendas = new ArrayList<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void calcularPrecoComDesconto(){
        System.out.println("Quantidade comprada: ");
        int quantidade = leitor.nextInt();
        leitor.nextLine();
        System.out.println("Valor Unitario: ");
        double valor = leitor.nextDouble();
        leitor.nextLine();
        LocalDate data = lerDataValida();
    
        double total = quantidade * valor;
        double desconto = 0;
        double valorFinal = 0;

            if (quantidade > 10){
            desconto = total * 0.05;
            valorFinal =total-desconto;
                }   else {
                valorFinal = total;
                }

        System.out.printf("Preco total: %.2f\n", total);
        System.out.printf("Desconto: %.2f\n", desconto);
        System.out.printf("Valor total com desconto: %.2f\n", valorFinal);

        vendas.add(new Vendas(quantidade, total, desconto, valorFinal, data));
    }
    public static void buscarPorDia() {

        leitor.nextLine();

        System.out.print("Digite a data (dd/MM/yyyy): ");
        LocalDate dataBusca = lerDataValida();

        int totalVendas = 0;

        for (Vendas v : vendas) {
            if (v.data.equals(dataBusca)) {
                totalVendas++;
            }
        }

        System.out.println("Total de vendas no dia: " + totalVendas);
    }

    public static void buscarPorMes() {

        System.out.print("Digite o mês (1-12): ");
        int mes = leitor.nextInt();

        System.out.print("Digite o ano: ");
        int ano = leitor.nextInt();

        int totalVendas = 0;

        for (Vendas v : vendas) {
            if (v.data.getMonthValue() == mes && v.data.getYear() == ano) {
                totalVendas++;
            }
        }

        System.out.println("Total de vendas no mês: " + totalVendas);
    }

    public static void calcularTroco(){
        System.out.println("Valor recebido: ");
        double recebido = leitor.nextDouble();
        leitor.nextLine();
        System.out.println("Valor da compra: ");
        double compra = leitor.nextDouble();
        leitor.nextLine();
        
        double troco = recebido - compra;

        System.out.println("Troco: " + troco);
    }

    public static LocalDate lerDataValida() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = null;

        while (data == null) {
            System.out.print("Digite a data (dd/MM/yyyy): ");
            String entrada = leitor.nextLine();

            try {
                data = LocalDate.parse(entrada, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Tente novamente.");
            }
        }
        return data;
    }
}
