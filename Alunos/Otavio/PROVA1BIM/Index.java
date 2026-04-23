import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Index {
    public static void main(String[] args) {
        List<Locacao> listaLocacao = new ArrayList<Locacao>();
        List<Veiculo> listaVeiculo = new ArrayList<Veiculo>();
        List<Cliente> listaCliente = new ArrayList<Cliente>();

        boolean rodando = true;
        Scanner scanner = new Scanner(System.in);
        
        listaVeiculo.add(new Carro("123mfsd", 123.5, true));
        listaVeiculo.add(new Moto("teste987", 321, 2));

        listaCliente.add(new Cliente("roberto", "1234567890", "23erewr43"));
        listaCliente.add(new Cliente("jeferson", "0987654321", "392ruiw"));

        listaLocacao.add(new Locacao(listaCliente.get(1), listaVeiculo.get(1), LocalDate.of(2021, 2, 2), LocalDate.of(2022, 2, 2), false));
        listaLocacao.add(new Locacao(listaCliente.get(1), listaVeiculo.get(0), LocalDate.of(2021, 5, 2), LocalDate.of(2024, 2, 2), true));

        while (rodando) { 
            System.out.println("O que você quer fazer hoje?");
            System.out.println("1 - Cadastrar nova locação");
            System.out.println("2 - Realizar devolução");
            System.out.println("3 - Listar locações pendentes");
            System.out.println("4 - Cadastrar Cliente");
            System.out.println("5 - Cadastrar Carro");
            System.out.println("6 - Cadastrar Moto");
            System.out.println("7 - Sair");

            String resposta = scanner.nextLine();

            switch (resposta) {
                case "1":
                    System.out.println("Escolha um dos clientes abaixo: ");
                    for (int i = 0; i < listaCliente.size(); i++) {
                        System.out.println(i + " - " + listaCliente.get(i).getNome());
                    }

                    Cliente clienteSelecionado = listaCliente.get(scanner.nextInt());

                    System.out.println("Escolha um dos veiculos abaixo: ");
                    for (int i = 0; i < listaVeiculo.size(); i++) {
                        System.out.println(i + " - " + listaVeiculo.get(i).mostrarInformacoes());
                    }

                    Veiculo veiculoSelecionado = listaVeiculo.get(scanner.nextInt());

                    System.out.println("Insira o ano, mês e dia que foi retirado o veiculo");
                    LocalDate dataRetirada = LocalDate.of(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());

                    System.out.println("Insira o ano, mês e dia que foi devolvido o veiculo");
                    LocalDate dataRetornado = LocalDate.of(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());

                    System.out.println("Veiculo foi retornado? (true = entregue false = não entregue)");
                    listaLocacao.add(new Locacao(clienteSelecionado, veiculoSelecionado, dataRetirada, dataRetornado, scanner.nextBoolean()));

                    break;
                case "2":
                    System.out.println("Escolha o veiculo que quer fazer devolução: ");
                    for (int i = 0; i < listaLocacao.size(); i++) {
                        System.out.println(i + " - ");
                        listaLocacao.get(i).mostrarDados();
                    }
                    listaLocacao.get(scanner.nextInt()).setSituacaoDevolucao(true);
                    
                    break;
                case "3":
                    for (int i = 0; i < listaLocacao.size(); i++) {
                        if (!listaLocacao.get(i).getSituacaoDevolucao()) {
                            System.out.println(i + " = ");
                            listaLocacao.get(i).mostrarDados();
                        }
                        System.out.println();
                    }
                    break;
                case "4":
                    System.out.println("Insira o nome, CPF e CNH");
                    listaCliente.add(new Cliente(scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
                    break;
                case "5":
                    System.out.println("Insira a placa, valor da diária e se tem ar condicionado ou não");
                    listaVeiculo.add(new Carro(scanner.nextLine(), scanner.nextDouble(), scanner.nextBoolean()));
                    break;
                case "6":
                    System.out.println("Insira a placa, valor da diária e a quantidade de cilindradas");
                    listaVeiculo.add(new Moto(scanner.nextLine(), scanner.nextDouble(), scanner.nextInt()));
                    break;
                case "7":
                    for (int i = 0; i < listaLocacao.size(); i++) {
                        if (!listaLocacao.get(i).getSituacaoDevolucao()) {
                            System.out.println(i + " = ");
                            listaLocacao.get(i).mostrarDados();
                        }
                        System.out.println();
                    }
                    System.out.println("Obrigado por usar o sistema");
                    rodando = false;
                    break;
                default:
                    System.out.println("Insira um valor válido");
                    break;
            }
        }
    }
}
