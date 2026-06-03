package Aula06;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CalculadoraProduto {

    static Scanner scan = new Scanner(System.in);

    // Formatador de data
    static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Loja do sistema
    static Loja loja = new Loja(
            "My Plant",
            "My Plant LTDA",
            "00.000.000/0001-00",
            "Cascavel",
            "Centro",
            "Rua Principal"
    );

    public static void main(String[] args){
        mostrarMenu();
    }

    public static void mostrarMenu() {

        int op = -1;

        do {
            System.out.println("\n----Menu Calculadora----");
            System.out.println("1- Calcular Valor Total");
            System.out.println("2- Calcular Troco");
            System.out.println("3- Registro de Vendas");
            System.out.println("4- Buscar Vendas por Data");
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

            case 3:
                mostrarRegistroVendas();
                break;

            case 4:
                buscarVendasPorData();
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

        double valorTotal = quantidade * valor;
        double desconto = 0;

        if(quantidade > 10){
            desconto = valorTotal * 0.05;
            valorTotal -= desconto;
        }

        System.out.println("Valor Total: " + valorTotal);
        System.out.println("Desconto Total: " + desconto);

        System.out.println("Digite a data da venda (dd/MM/yyyy): ");
        String dataTexto = scan.nextLine();

        LocalDate data = LocalDate.parse(dataTexto, formatter);

        // cria venda
        Venda venda = new Venda(
                quantidade,
                valor,
                desconto,
                valorTotal,
                data
        );

        // adiciona venda na loja
        loja.adicionarVendas(venda);

        System.out.println("Venda registrada com sucesso!");
    }


    public static void calcularTroco(){

        System.out.println("Valor da Compra: ");
        double compra = scan.nextDouble();

        System.out.println("Valor Entregue pelo Cliente: ");
        double valorCliente = scan.nextDouble();

        double troco = valorCliente - compra;

        if (troco > 0){
            System.out.println("Valor do Troco: " + troco);
        }
        else if (troco == 0){
            System.out.println("Valor Correto! Sem Necessidade de Troco");
        }
        else{
            System.out.println("Faltando "
                    + (troco * -1)
                    + " para completar a compra.");
        }
    }

    public static void mostrarRegistroVendas(){

        System.out.println("\n--- Registro de Vendas ---");

        loja.mostrarVendas();
    }

    public static void buscarVendasPorData(){

        System.out.println("Digite a data para busca (dd/MM/yyyy): ");
        String dataBuscaTexto = scan.nextLine();

        LocalDate dataBusca =
                LocalDate.parse(dataBuscaTexto, formatter);

        loja.buscarVendasPorData(dataBusca);
    }
}