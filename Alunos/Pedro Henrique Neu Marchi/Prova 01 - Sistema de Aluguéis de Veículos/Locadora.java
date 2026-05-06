import java.time.LocalDate;
import java.util.Scanner;

public class Locadora {
    Locacao[] locacoes = new Locacao[10];
    int contador = 0;
    Clientes[] clientes = new Clientes[10];
    int contClientes = 0;
    Veiculo[] veiculos = new Veiculo[10];
    int contVeiculos = 0;
    Scanner scanner = new Scanner(System.in);

    public void adicionarCliente(Clientes c) {
        if (contClientes < 10) {
            clientes[contClientes++] = c;
        } else {
            System.out.println("Limite de clientes atingido.");
        }
    }
    public Clientes buscarCliente(String cpf) {
        for (int i = 0; i < contClientes; i++) {
            if (clientes[i].cpf.equals(cpf)) {
                return clientes[i];
            }
        }
        return null;
    }
        public void cadastrarVeiculo(Veiculo v) {
        if (contVeiculos < 10) {
            veiculos[contVeiculos++] = v;
        } else {
            System.out.println("Limite de veículos atingido.");
            }
         }

        public void cadastrarVeiculoInterativo(int tipoVeiculo, Locadora locadora) {
        if (tipoVeiculo == 1) {
            Carro carro = new Carro();

            System.out.print("Placa: ");
            carro.placa = scanner.nextLine();
            System.out.print("Valor de Locação: ");
            carro.valorLocacao = scanner.nextDouble();
            System.out.print("Possui Ar Condicionado (true/false): ");
            carro.arCondicionado = scanner.nextBoolean();
            scanner.nextLine(); // limpar buffer
            locadora.cadastrarVeiculo(carro);

        } else if (tipoVeiculo == 2) {
            Moto moto = new Moto();

            System.out.print("Placa: ");
            moto.placa = scanner.nextLine();
            System.out.print("Valor de Locação: ");
            moto.valorLocacao = scanner.nextDouble();
            System.out.print("Cilindradas: ");
            moto.cilindradas = scanner.nextInt();
            scanner.nextLine(); // limpar buffer
            locadora.cadastrarVeiculo(moto);

        } else {
            System.out.println("Tipo de veículo inválido.");
        }
    }

    public Veiculo buscarVeiculo(String placa) {
        for (int i = 0; i < contVeiculos; i++) {
            if (veiculos[i].placa.equals(placa)) {
                return veiculos[i];
            }
        }
        return null;
    }

    public void adicionarLocacao(Locacao locacao) {
        if (contador < 10) {
            locacoes[contador++] = locacao;
        } else {
            System.out.println("Limite de locações atingido.");
        }
    }

   public static void registrarLocacao(Scanner scanner, Locadora locadora) {
    Locacao locacao = new Locacao();

    System.out.print("CPF do Cliente: ");
    String cpfCliente = scanner.nextLine();
    locacao.clientes = locadora.buscarCliente(cpfCliente);
        if (locacao.clientes == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        System.out.print("Placa do Veículo: ");
        String placaVeiculo = scanner.nextLine();
        locacao.veiculo = locadora.buscarVeiculo(placaVeiculo);
        if (locacao.veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }
    System.out.print("Data de Locação (YYYY-MM-DD): ");
    locacao.dataLocacao = LocalDate.parse(scanner.nextLine());
    System.out.print("Dias de Locação: ");
    locacao.diasLocacao = scanner.nextInt();
    scanner.nextLine();
    locacao.valorTotal = locacao.veiculo.valorLocacao * locacao.diasLocacao;
    locacao.devolvido = false;
    locadora.adicionarLocacao(locacao);
    System.out.println("Locação registrada com sucesso!");
    }

    public void devolverLocacao(String cpf) {
    boolean encontrou = false;

    for (int i = 0; i < contador; i++) {
        if (locacoes[i].clientes.cpf.equals(cpf) && !locacoes[i].devolvido) {
            locacoes[i].devolvido = true;
            System.out.println("Devolução realizada com sucesso!");
            encontrou = true;
            break;
        }
    }
    if (!encontrou) {
        System.out.println("Nenhuma locação pendente encontrada para esse CPF.");
    }
}

    public void listarPendentes() {
        System.out.println("\n=== LOCAÇÕES ATIVAS ===");
        for (int i = 0; i < contador; i++) {
            if (!locacoes[i].devolvido) {
                locacoes[i].exibirLocacao();
            }
        }
    }
}

