import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class SistemaVendas {

    static ArrayList<Venda> vendas = new ArrayList<>();
    static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void total(Scanner scan){

        System.out.println("Digite a quantidade:");
        int quantidade = scan.nextInt();

        System.out.println("Digite o valor:");
        double preco = scan.nextDouble();

        scan.nextLine();

        System.out.println("Digite a data(dd/MM/yyyy): ");
        String dataTexto = scan.nextLine();

        LocalDate data = LocalDate.parse(dataTexto, formato);

        double total = preco * quantidade;
        double desconto = 0;

        if(quantidade > 10){
            desconto = total * 0.05;
            total = total - desconto;
        }

        System.out.println("Total: R$ " + total);

        if(desconto > 0){
            System.out.println("Desconto aplicado: R$ " + desconto);
        }

        vendas.add(new Venda(quantidade, preco, desconto, total, data));
    }

    public static void troco(Scanner scan){

        System.out.println("Digite o valor pago: ");
        double pago = scan.nextDouble();

        System.out.println("Digite o valor da compra: ");
        double compra = scan.nextDouble();

        double troco = pago - compra;

        if(troco < 0){
            System.out.println("Valor insuficiente, faltam: R$ " + (-troco));
        }
        else{
            System.out.println("Troco: R$ " + troco);
        }
    }

    public static void mostrarVendas(){

        if(vendas.isEmpty()){
            System.out.println("Nenhuma venda registrada!");
            return;
        }

        for(int i = 0; i < vendas.size(); i++){
            Venda v = vendas.get(i);

            System.out.println("\nVenda " + (i + 1));
            System.out.println("Quantidade: " + v.quantidade);
            System.out.println("Preço unitário: R$ " + v.preco);
            System.out.println("Total final: R$ " + v.total);
            System.out.println("Desconto: R$ " + v.desconto);
            System.out.println("Data: " + v.data.format(formato));
        }
    }

    public static void buscarDia(Scanner scan){

        scan.nextLine();

        System.out.println("Digite a data desejada: ");
        String dataTexto = scan.nextLine();

        LocalDate dataBusca = LocalDate.parse(dataTexto, formato);

        int totalVendas = 0;

        for(Venda v : vendas){
            if(v.data.equals(dataBusca)){
                totalVendas++;
            }
        }

        System.out.println("Total de vendas do dia: " + totalVendas);
    }

    public static void buscarMes(Scanner scan){

        System.out.println("Digite o mes: ");
        int mes = scan.nextInt();

        System.out.println("Digite o ano: ");
        int ano = scan.nextInt();

        int totalVendas = 0;

        for(Venda v : vendas){
            if(v.data.getMonthValue() == mes && v.data.getYear() == ano){
                totalVendas++;
            }
        }

        System.out.println("Total de vendas no mes: " + totalVendas);
    }
}