import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Calculadoraplantas {

    static class Vendedor {
        String nome;
        int idade;
        String loja;
        String cidade;
        String bairro;
        String rua;
        double salarioBase;

        double[] salarioRecebido = {1500, 1600, 1700};

        public void apresentarSe() {
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
            System.out.println("Loja: " + loja);
            System.out.println("Cidade: " + cidade);
            System.out.println("Bairro: " + bairro);
            System.out.println("Rua: " + rua);
            System.out.println("Salário Base: R$ " + salarioBase);
            System.out.println("Média Salarial: R$ " + calcularMedia());
            System.out.println("Bônus: R$ " + calcularBonus());
            System.out.println("Salários Recebidos: " + salarioRecebido[0] + ", " + salarioRecebido[1] + ", " + salarioRecebido[2]);
            System.out.println("----------------------------");
        }

        public double calcularMedia() {
            double soma = 0;
            for (double s : salarioRecebido) soma += s;
            return soma / salarioRecebido.length;
        }

        public double calcularBonus() {
            return salarioBase * 0.2;
        }
    }

    static class Cliente {
        String nome;
        int idade;
        String cidade;
        String bairro;
        String rua;

        public void apresentarSe() {
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
            System.out.println("Cidade: " + cidade);
            System.out.println("Bairro: " + bairro);
            System.out.println("Rua: " + rua);
            System.out.println("----------------------------");
        }
    }

    static class Loja {
        String nomeFantasia;
        String razaoSocial;
        String cnpj;
        String cidade;
        String bairro;
        String rua;

        Vendedor[] vendedores = new Vendedor[10];
        Cliente[] clientes = new Cliente[10];

        int totalVendedores = 0;
        int totalClientes = 0;

        public void contarClientes() {
            System.out.println("Total de clientes: " + totalClientes);
        }

        public void contarVendedores() {
            System.out.println("Total de vendedores: " + totalVendedores);
        }

        public void apresentarSe() {
            System.out.println("Loja: " + nomeFantasia);
            System.out.println("Razão Social: " + razaoSocial);
            System.out.println("CNPJ: " + cnpj);
            System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
        }

        public void adicionarCliente(Cliente c) {
            clientes[totalClientes++] = c;
        }

        public void adicionarVendedor(Vendedor v) {
            vendedores[totalVendedores++] = v;
        }
    }

    static int[] quantidadesVendidas = new int[100];
    static double[] valoresVenda = new double[100];
    static double[] descontosAplicados = new double[100];
    static LocalDate[] datasVenda = new LocalDate[100];
    static int totalVendas = 0;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Loja loja = new Loja();
        loja.nomeFantasia = "Plantas da Gabrielinha";
        loja.razaoSocial = "Plantas da Gabrielinha LTDA";
        loja.cnpj = "12.345.678/0001-99";
        loja.cidade = "Cascavel";
        loja.bairro = "Centro";
        loja.rua = "Rua das Flores";

        int opcao = 0;

        while (opcao != 11) {

            System.out.println("\n=== SISTEMA COMPLETO ===");
            System.out.println("[1] Calcular Preço Total");
            System.out.println("[2] Venda com Desconto + Registro");
            System.out.println("[3] Calcular Troco");
            System.out.println("[4] Ver Registro de Vendas");
            System.out.println("[5] Buscar Vendas por Data");
            System.out.println("[6] Cadastrar Cliente");
            System.out.println("[7] Cadastrar Vendedor");
            System.out.println("[8] Dados da Loja");
            System.out.println("[9] Ver Clientes");
            System.out.println("[10] Ver Vendedores");
            System.out.println("[11] Sair");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Quantidade: ");
                    int q = scanner.nextInt();

                    System.out.print("Preço unitário: ");
                    double p = scanner.nextDouble();

                    System.out.println("Total: R$ " + (q * p));
                    break;

                case 2:
                    System.out.print("Quantidade: ");
                    int qtd = scanner.nextInt();

                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();

                    double total = qtd * preco;
                    double desconto = 0;

                    if (qtd > 10) {
                        desconto = total * 0.05;
                        total -= desconto;
                        System.out.println("Desconto aplicado!");
                    }

                    System.out.println("Total: R$ " + total);

                    LocalDate dataVenda = null;
                    scanner.nextLine();

                    while (dataVenda == null) {
                        System.out.print("Data (dd/MM/yyyy): ");
                        String dataStr = scanner.nextLine();
                        try {
                            dataVenda = LocalDate.parse(dataStr, formatter);
                        } catch (DateTimeParseException e) {
                            System.out.println("Data inválida!");
                        }
                    }

                    quantidadesVendidas[totalVendas] = qtd;
                    valoresVenda[totalVendas] = total;
                    descontosAplicados[totalVendas] = desconto;
                    datasVenda[totalVendas] = dataVenda;
                    totalVendas++;

                    System.out.println("Venda registrada!");
                    break;

                case 3:
                    System.out.print("Valor recebido: ");
                    double recebido = scanner.nextDouble();

                    System.out.print("Valor da compra: ");
                    double compra = scanner.nextDouble();

                    double troco = recebido - compra;

                    if (troco < 0)
                        System.out.println("Faltam R$ " + Math.abs(troco));
                    else
                        System.out.println("Troco: R$ " + troco);
                    break;

                case 4:
                    if (totalVendas == 0) {
                        System.out.println("Nenhuma venda.");
                        break;
                    }

                    for (int i = 0; i < totalVendas; i++) {
                        System.out.println(
                                datasVenda[i].format(formatter) +
                                " | Qtd: " + quantidadesVendidas[i] +
                                " | R$ " + valoresVenda[i] +
                                " | Desc: " + descontosAplicados[i]);
                    }
                    break;

                case 5:
                    System.out.print("Mês: ");
                    int mes = scanner.nextInt();

                    System.out.print("Dia: ");
                    int dia = scanner.nextInt();

                    double totalDia = 0;
                    int count = 0;

                    for (int i = 0; i < totalVendas; i++) {
                        if (datasVenda[i].getMonthValue() == mes &&
                            datasVenda[i].getDayOfMonth() == dia) {

                            count++;
                            totalDia += valoresVenda[i];

                            System.out.println("Venda: R$ " + valoresVenda[i]);
                        }
                    }

                    System.out.println("Total vendas: " + count);
                    System.out.println("Total arrecadado: R$ " + totalDia);
                    break;

                case 6:
                    Cliente c = new Cliente();

                    scanner.nextLine();
                    System.out.print("Nome: ");
                    c.nome = scanner.nextLine();

                    System.out.print("Idade: ");
                    c.idade = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("Cidade: ");
                    c.cidade = scanner.nextLine();

                    System.out.print("Bairro: ");
                    c.bairro = scanner.nextLine();

                    System.out.print("Rua: ");
                    c.rua = scanner.nextLine();

                    loja.adicionarCliente(c);
                    System.out.println("Cliente cadastrado!");
                    break;

                case 7:
                    Vendedor v = new Vendedor();

                    scanner.nextLine();
                    System.out.print("Nome: ");
                    v.nome = scanner.nextLine();

                    System.out.print("Idade: ");
                    v.idade = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("Cidade: ");
                    v.cidade = scanner.nextLine();

                    System.out.print("Bairro: ");
                    v.bairro = scanner.nextLine();

                    System.out.print("Rua: ");
                    v.rua = scanner.nextLine();

                    System.out.print("Salário Base: ");
                    v.salarioBase = scanner.nextDouble();

                    v.loja = loja.nomeFantasia;

                    loja.adicionarVendedor(v);

                    System.out.println("Vendedor cadastrado!");
                    System.out.println("Média salarial: " + v.calcularMedia());
                    System.out.println("Bônus: " + v.calcularBonus());
                    break;

                case 8:
                    loja.apresentarSe();
                    loja.contarClientes();
                    loja.contarVendedores();
                    break;

                case 9:
                    if (loja.totalClientes == 0) {
                        System.out.println("Nenhum cliente cadastrado.");
                        break;
                    }
                    System.out.println("\n=== CLIENTES ===");
                    for (int i = 0; i < loja.totalClientes; i++) {
                        loja.clientes[i].apresentarSe();
                    }
                    break;

                case 10:
                    if (loja.totalVendedores == 0) {
                        System.out.println("Nenhum vendedor cadastrado.");
                        break;
                    }
                    System.out.println("\n=== VENDEDORES ===");
                    for (int i = 0; i < loja.totalVendedores; i++) {
                        loja.vendedores[i].apresentarSe();
                    }
                    break;

                case 11:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}