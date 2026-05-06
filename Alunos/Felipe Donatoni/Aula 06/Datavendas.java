import java.util.Scanner;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Datavendas {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Loja loja = new Loja();
        loja.nomeFantasia = "Floricultura Gabrielinha";
        loja.cnpj = "12.143.590/0001-20";
        loja.cidade = "Cascavel";
        loja.bairro = "Centro";
        loja.rua = "Av. Brasil";

        Vendedor vendedor = new Vendedor();
        vendedor.nome = "Rodrigo";
        vendedor.idade = 30;
        vendedor.salarioBase = 2000;
        vendedor.loja = loja;

        loja.vendedores = new Vendedor[]{vendedor};

        Cliente cliente = new Cliente();
        cliente.nome = "Roberta";
        cliente.idade = 25;

        loja.clientes = new Cliente[]{cliente};

        int option = 0;
        int totalquantidade = 0;
        double totalvenda = 0;
        double totaldesconto = 0;
        double ultimoTotal = 0;

        HashMap<LocalDate, Integer> vendaspordia = new HashMap<>();

        while (option != 6) {

            System.out.println("\nMenu");
            System.out.println("1 - Calcular total");
            System.out.println("2 - Calcular troco");
            System.out.println("3 - Registro de vendas");
            System.out.println("4 - Consultar vendas por data");
            System.out.println("5 - Dados da loja");
            System.out.println("6 - Sair");

            option = scanner.nextInt();

            switch (option) {

                case 1:
                    System.out.println("Quantidade:");
                    int quantidade = scanner.nextInt();

                    System.out.println("Preço:");
                    double price = scanner.nextDouble();

                    double total = quantidade * price;
                    double desconto = 0;

                    if (quantidade > 10) {
                        desconto = total * 0.05;
                        total -= desconto;
                        System.out.println("Desconto: " + desconto);
                    }

                    System.out.println("Total: " + total);

                    totalquantidade += quantidade;
                    totalvenda += total;
                    totaldesconto += desconto;
                    ultimoTotal = total;

                    LocalDate hoje = LocalDate.now();
                    vendaspordia.put(hoje,
                            vendaspordia.getOrDefault(hoje, 0) + quantidade);
                    break;

                case 2:
                    System.out.println("Valor recebido:");
                    double recebido = scanner.nextDouble();

                    double troco = recebido - ultimoTotal;
                    System.out.println("Troco: " + troco);
                    break;

                case 3:
                    System.out.println("Quantidade vendida: " + totalquantidade);
                    System.out.println("Total vendido: " + totalvenda);
                    System.out.println("Descontos: " + totaldesconto);
                    break;

                case 4:
                    System.out.println("Data (dd/MM/yyyy):");
                    String texto = scanner.next();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data = LocalDate.parse(texto, formatter);

                    int totalDia = vendaspordia.getOrDefault(data, 0);
                    System.out.println("Vendido no dia: " + totalDia);
                    break;

                case 5:
                    loja.apresentarSe();
                    System.out.println("Clientes: " + loja.contarClientes());
                    System.out.println("Vendedores: " + loja.contarVendedores());

                    vendedor.apresentarSe();
                    System.out.println("Média salárial: " + vendedor.calcularMedia());
                    System.out.println("Bônus: " + vendedor.calcularBonus());

                    cliente.apresentarSe();
                    break;

                case 6:
                    System.out.println("Encerrado");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }

        scanner.close();
    }
}