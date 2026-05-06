import java.time.LocalDate;
import java.util.Scanner;

public class Locadora {

    Scanner scan = new Scanner(System.in);
    private Banco banco;

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataEmprestimo;
    private int dias;
    private boolean devolvido;

    public Locadora(Cliente cliente, Veiculo veiculo, LocalDate dataEmprestimo, int dias) {

        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataEmprestimo = dataEmprestimo;
        this.dias = dias;
        this.devolvido = false;

    }

    public Locadora(Banco banco) {
        this.banco = banco;

    }

    public void cadastrarLocacao(Scanner scan) {

        int locacaoAtivo = 0;

        for (Locadora l : banco.getLocacao()) {

            if (!l.isDevolvido()) {
                locacaoAtivo++;
            }
        }

        if (locacaoAtivo > 10) {
            System.out.println("Não é possível registrar mais locações");
            System.out.println("Já existem 10 locações ativas");
            return;
        }

        if (banco.getClientes().isEmpty()) {

            System.out.println("Nenhum cliente cadastrado");
            return;

        }

        System.out.println("Selecione um cliente: ");

        for(int i = 0; i < banco.getClientes().size(); i++) {
            System.out.println("[" + (i + 1) + "] - " + banco.getClientes().get(i).getNome());
        }

        int escolhaCliente = scan.nextInt();
        Cliente cliente = banco.getClientes().get(escolhaCliente - 1);

        System.out.println("Qual veiculo o cliente deseja: ");
        System.out.println("[1] - Carro");
        System.out.println("[2] - Moto");
        int tipo = scan.nextInt();

        Veiculo veiculoEscolhido = null;

        switch (tipo) {

            case 1:

                if (banco.getCarros().isEmpty()) {
                    System.out.println("Nenhum carro cadastrado");
                    return;
                }

                System.out.println("Escolha qual carro será levado: ");

                for (int i = 0; i < banco.getCarros().size(); i++) {
                    System.out.println("[" + (i + 1) + "] - " + banco.getCarros().get(i).getPlaca());
                }

                int escolhaCarro = scan.nextInt();
                veiculoEscolhido = banco.getCarros().get(escolhaCarro - 1);
                break;

            case 2:

                if (banco.getMotos().isEmpty()) {
                    System.out.println("Nenhuma moto cadastrada");
                    return;
                }

                System.out.println("Escolha qual moto será levado: ");

                for (int i = 0; i < banco.getMotos().size(); i++) {
                    System.out.println("[" + (i + 1) + "] - " + banco.getMotos().get(i).getPlaca());
                }

                int escolhaMoto = scan.nextInt();
                veiculoEscolhido = banco.getMotos().get(escolhaMoto - 1);
                break;

            default:
                System.out.println("Selecione uma opcao correta!");
                return;
        }

        System.out.println("Digite a data da locação:");
        System.out.print("Dia: ");
        int dia = scan.nextInt();

        System.out.print("Mês: ");
        int mes = scan.nextInt();

        System.out.print("Ano: ");
        int ano = scan.nextInt();

        LocalDate dataLocacao = LocalDate.of(ano, mes, dia);

        System.out.println("Quantos dias o cliente ficará com o veículo: ");
        int dias = scan.nextInt();

        Locadora locacao = new Locadora(cliente, veiculoEscolhido, dataLocacao, dias);
        banco.getLocacao().add(locacao);

        System.out.println("Locação registrado com sucesso");
        System.out.println();
        locacao.mostrarLocacao();

    }

    public void mostrarLocacao() {

            System.out.println("🔖");
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Item: " + veiculo.getPlaca());
            System.out.println("Data do empréstimo: " + dataEmprestimo);
            System.out.println("Prazo: " + dias + " dias");
            System.out.println("Data prevista: " + dataEmprestimo.plusDays(dias));
            System.out.println("Valor final: " + dias * veiculo.getValorDiaria() + "R$");
            System.out.println("Situação: " + (devolvido ? "Devolvido" : "Em andamento"));
            System.out.println("➖➖➖➖➖➖➖➖➖➖➖");

    }


    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void devolver() {
        devolvido = true;
    }
}