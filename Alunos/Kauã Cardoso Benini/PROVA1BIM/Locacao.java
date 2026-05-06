import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Locacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate data;
    private boolean devolvido;
    private LocalDate dataDevolucao;

    public Locacao() {
    }

    public static void cadastroLocacao(Scanner scan) {

        for(int i = 0; i < Lista.clientes.size(); i++ ){
            Cliente cliente = Lista.clientes.get(i);

            System.out.println("[" + i + "] Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("CNH " + cliente.getCnh());
            System.out.println("------------------------");
        }

        System.out.println("Escolha o numero do cliente");
        int indiceCliente = scan.nextInt();

        if(indiceCliente < 0 || indiceCliente >= Lista.clientes.size()){
            System.out.println("Cliente invalido");
            return;
        }

        Cliente clienteSelecionado = Lista.clientes.get(indiceCliente);

        for (int i = 0; i < Lista.veiculos.size(); i++ ) {
            Veiculo veiculo = Lista.veiculos.get(i);

            System.out.println("[" + i + "] placa: " + veiculo.getPlaca());
            System.out.println("preco: " + veiculo.getPreco());

            if(veiculo instanceof Carro){
                Carro carro = (Carro) veiculo;
                System.out.println("Possui arcondicionado? " + carro.getAr());
            }
            else if(veiculo instanceof Moto){
                Moto moto = (Moto) veiculo;
                System.out.println("Possui " + moto.getClindradas() + " cilindradas");
            }

            System.out.println("----------------------");
        }

        System.out.println("Escolha o numero do veiculo");
        int indiceVeiculo = scan.nextInt();

        if (indiceVeiculo < 0 || indiceVeiculo >= Lista.veiculos.size()){
            System.out.println("Veiculo invalido");
            return;
        }

        Veiculo veiculoSelecionado = Lista.veiculos.get(indiceVeiculo);

        System.out.println("Digite a data da locacao (yyy/MM/dd): ");
        String dataLocacao = scan.next();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate dataLocacaoSelecionado = LocalDate.parse(dataLocacao, formatter);

        Locacao novaLocacao = new Locacao();
        novaLocacao.setCliente(clienteSelecionado);
        novaLocacao.setVeiculo(veiculoSelecionado);
        novaLocacao.setData(dataLocacaoSelecionado);
        novaLocacao.setDevolvido(false);

        if(Lista.locacao.size() < 11){
            Lista.locacao.add(novaLocacao);
            System.out.println("Locacao realizada com sucesso");
        } else {
            System.out.println("Limite de locacoes atingido");
        }
    }

    public static void listarLocacoes(){

        for (int i = 0; i < Lista.locacao.size(); i++) {
            Locacao l = Lista.locacao.get(i);

                System.out.println("[" + i + "]");
                System.out.println("Cliente: " + l.getCliente().getNome());
                System.out.println("Veiculo: " + l.getVeiculo().getPlaca());
                System.out.println("Data locacao: " + l.getData());


                System.out.println("Status: " + (l.isDevolvido() ? "Devolvido" : "Pendente"));
                System.out.println("------------------------");

        }
    }

    public static void listarDevolvidos(){

        for (int i = 0; i < Lista.locacao.size(); i++) {
            Locacao l = Lista.locacao.get(i);

            if(!l.isDevolvido()) {

                System.out.println("[" + i + "]");
                System.out.println("Cliente: " + l.getCliente().getNome());
                System.out.println("Veiculo: " + l.getVeiculo().getPlaca());
                System.out.println("Data locacao: " + l.getData());


                System.out.println("Status: " + (l.isDevolvido() ? "Devolvido" : "Pendente"));
                System.out.println("------------------------");
            }
        }
    }



    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    // GETTERS E SETTERS

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}