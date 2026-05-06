import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int ultimaQuantidade = 0;
        double ultimoTotal = 0;
        double ultimoDesconto = 0;

        LocalDate[] datas = new LocalDate[100];
        int[] vendas = new int[100];
        int contador = 0;

        int opcao = 0;

        Loja loja = new Loja();
        loja.nomeFantasia = "My Plant";
        loja.razaoSocial = "My Plant LTDA";
        loja.cnpj = "123456789";
        loja.cidade = "Cascavel";
        loja.bairro = "Centro";
        loja.rua = "Rua A";

        Vendedor v1 = new Vendedor();
        v1.nome = "Joao";
        v1.idade = 25;
        v1.loja = loja.nomeFantasia;
        v1.cidade = "Cascavel";
        v1.bairro = "Centro";
        v1.rua = "Rua A";
        v1.salarioBase = 2000;
        v1.salarioRecebido = new double[]{2000, 2100, 2200};

        Cliente c1 = new Cliente();
        c1.nome = "Maria";
        c1.idade = 30;
        c1.cidade = "Cascavel";
        c1.bairro = "Centro";
        c1.rua = "Rua B";

        loja.vendedores = new Vendedor[]{v1};
        loja.clientes = new Cliente[]{c1};

        while (opcao != 3) {

            System.out.println("\n | MENU - MY PLANT |");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            System.out.println("[4] - Registrar Venda");
            System.out.println("[5] - Consultar Vendas");
            System.out.println("[6] - Mostrar Dados da Loja");

            opcao = sc.nextInt();

            if (opcao == 1) {

                System.out.println("Quantidade da planta:");
                int quantidade = sc.nextInt();

                System.out.println("Preço da planta:");
                double preco = sc.nextDouble();
            
                double totalSemDesconto = quantidade * preco;

                double desconto = 0;
                double totalComDesconto = totalSemDesconto;

                if (quantidade > 10) {
                    desconto = totalSemDesconto * 0.05;
                    totalComDesconto = totalSemDesconto - desconto;
                }

                ultimaQuantidade = quantidade;
                ultimoTotal = totalComDesconto;
                ultimoDesconto = desconto;

                System.out.println("Quantidade Vendida: " + ultimaQuantidade);
                System.out.println("Valor total da Compra: " + totalSemDesconto);
                System.out.println("Desconto Aplicado: " + ultimoDesconto);
                System.out.println("Total com Desconto: " + ultimoTotal);
            }

            if (opcao == 2) {

                if (ultimoTotal == 0) {
                    System.out.println("Nenhuma venda registrada ainda.");
                } else {

                    System.out.println("Valor recebido:");
                    double recebido = sc.nextDouble();

                    double compra = ultimoTotal;
                    System.out.println("Valor da compra: " + compra);

                    double troco = recebido - compra;

                    System.out.println("Troco: " + troco);
                    System.out.println("\nÚltima venda:");
                    System.out.println("Quantidade: " + ultimaQuantidade);
                    System.out.println("Total: " + ultimoTotal);
                    System.out.println("Desconto: " + ultimoDesconto);
                }
            }

            if (opcao == 4) {

                System.out.println("Digite a data (dd/MM/yyyy):");
                String dataTexto = sc.next();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(dataTexto, formatter);

                datas[contador] = data;
                vendas[contador] = 1;
                contador++;

                System.out.println("Venda registrada.");
            }

            if (opcao == 5) {

                System.out.println("Digite a data (dd/MM/yyyy):");
                String dataTexto = sc.next();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataBusca = LocalDate.parse(dataTexto, formatter);

                int totalVendas = 0;

                for (int i = 0; i < contador; i++) {
                    if (datas[i].equals(dataBusca)) {
                        totalVendas = totalVendas + vendas[i];
                    }
                }

                System.out.println("Total de vendas: " + totalVendas);
            }

            if (opcao == 6) {
                loja.apresentarse();
                v1.apresentarse();
                c1.apresentarse();
                System.out.println("Media salarios: " + v1.calcularMedia());
                System.out.println("Bonus: " + v1.calcularBonus());
                System.out.println("Total clientes: " + loja.contarClientes());
                System.out.println("Total vendedores: " + loja.contarVendedores());
            }

            if (opcao == 3) {
                System.out.println("Obrigado pela visita, volte sempre!");
            }
        }

        sc.close();
    }
}