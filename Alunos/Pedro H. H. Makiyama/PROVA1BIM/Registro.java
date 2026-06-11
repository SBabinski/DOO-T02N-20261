package PROVA1BIM;
import java.util.ArrayList;

public class Registro {

    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Veiculo> veiculos = new ArrayList<>();
    ArrayList<Locacao> locacoes = new ArrayList<>();

    public void registrarCliente(String nome, int cpf, int cnh){

        Cliente cliente = new Cliente(nome, cpf, cnh);

        clientes.add(cliente);
    }

    public void registrarCarro(String placa, boolean temArCond){

        Carro carro = new Carro(placa, temArCond);

        veiculos.add(carro);
    }

    public void registrarMoto(String placa, int cilindrada){

        Moto moto = new Moto(placa, cilindrada);

        veiculos.add(moto);
    }

    public void registrarLocacao(String cliente, String placa, int diasLocacao, int numeroVaga, String retirada, double valorDiaria){

        Locacao locacao = new Locacao(cliente, placa, diasLocacao, numeroVaga, retirada, valorDiaria);

        locacoes.add(locacao);
    }

    public void realizarDevolucao(String cliente, String placa){

        for (Locacao locac : locacoes){

            if (locac.getCliente().equals(cliente) && locac.getPlaca().equals(placa)){

                locac.setDevolvido(true);
                break;
            }
        }
    }

    public void listarSemDevolucao(){

        for (Locacao locac : locacoes){

            if (locac.getIsDevolvido() == false){

                System.out.println("Cliente: " + locac.getCliente() + ", Placa: " + locac.getPlaca() + ", Dias de Locação: " + locac.getDiasLocacao() + ", Valor da diária: " + locac.getValorDiaria() + ", Data de Retirada: " + locac.getRetirada() + " e Data de Devolução: " + locac.getDevolucao());
            }
        }
    }
}
