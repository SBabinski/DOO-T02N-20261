import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class LojaGabi{

    static Scanner scanner = new Scanner(System.in);
    static ArrayList <Venda> vendas = new ArrayList<>();

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu(){
        int opcao;

        do { 
            System.out.printf("\n====Loja da Dona Gabrielinha====\n");
            System.out.println("[1] Calcular Preço Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Ver Registro de Vendas");
            System.out.println("[4] Buscar vendas por data");
            System.out.println("[0] Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            validarOpcao(opcao);

        } while (opcao!= 0);
    }

    public static void validarOpcao(int opcao){
        
        switch (opcao){
            case 1 -> calcularTotal();
            case 2 -> calcularTroco();
            case 3 -> listarVendas();
            case 4 -> buscarPorData();
            case 0 -> System.out.println("Encerrando...");
            default -> System.out.println("Opção inválida!");
        }
    }

    public static void calcularTotal(){

        System.out.println("Quantidade de planta:");
        int quantidade = scanner.nextInt();

        System.out.println("Preço da planta:");
        double preco = scanner.nextDouble();

        double subtotal = quantidade * preco;
        double desconto = 0;

        if (quantidade > 10){
            desconto = subtotal * 0.05;
        }

        double total = subtotal - desconto;

        LocalDate dataAtual = LocalDate.now();

        Venda venda = new Venda(quantidade, preco, desconto, total, dataAtual);
        vendas.add(venda);

        System.out.println("Total: R$ " + total);
        System.out.println("Desconto: R$ " + desconto);
    }

    public static void calcularTroco() {

        System.out.print("Valor recebido: ");
        double recebido = scanner.nextDouble();

        System.out.print("Valor da compra: ");
        double total = scanner.nextDouble();

        double troco = recebido - total;

        System.out.println("Troco: R$ " + troco);
    }

    public static void listarVendas(){
        System.out.printf("\n====Registro de Vendas====\n");
        for (int i = 0; i < vendas.size(); i++) {
            System.out.printf("%d | ", i + 1);
            vendas.get(i).mostrarVendas();
        }
    }

    public static void buscarPorData(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Digite a data (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();

        LocalDate dataBusca = LocalDate.parse(dataStr, formatter);

        int totalVendas = 0;

        for (Venda v : vendas){
            if (v.getData().equals(dataBusca)){
                totalVendas++;
            }
        }

        System.out.println("Total de vendas nesta data: " + totalVendas);
    }

    static class Venda{

        private int quantidade;
        private double preco;
        private double desconto;
        private double total;
        private LocalDate data;

        public Venda(int quantidade, double preco, double desconto, double total, LocalDate data){
            
            this.quantidade = quantidade;
            this.preco = preco;
            this.desconto = desconto;
            this.total = total;
            this.data = data;
        }

        public int getQuantidade(){
            return quantidade;
        }

        public double getTotal(){
            return total;
        }

        public double getDesconto(){
            return desconto;
        }

        public double getPreco(){
            return preco;
        }

        public LocalDate getData(){
            return data;
        }

        public void mostrarVendas(){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = data.format(formatter);

            System.out.println("Quantidade: " + quantidade + " | Preço: R$ " + preco + 
            " | Desconto: R$ " + desconto + " | Total: R$ " + total + " | Data: " + dataFormatada);
        }
    }
}
