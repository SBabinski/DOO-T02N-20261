import java.util.ArrayList;

public class Banco {

    private ArrayList<Cliente> clientes;
    private ArrayList<Carro> carros;
    private ArrayList<Moto> motos;
    private ArrayList<Locadora> locacao;

    public Banco() {

        clientes = new ArrayList<>();
        carros = new ArrayList<>();
        motos = new ArrayList<>();
        locacao = new ArrayList<>();

    }

    public void listarLocacoes() {

        if (locacao.isEmpty()) {
            System.out.println("Nenhuma locacao cadastrada");
        }

        for (Locadora l : locacao) {
            l.mostrarLocacao();
        }
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Carro> getCarros() {
        return carros;
    }

    public ArrayList<Moto> getMotos() {
        return motos;
    }

    public ArrayList<Locadora> getLocacao() {
        return locacao;
    }
}
