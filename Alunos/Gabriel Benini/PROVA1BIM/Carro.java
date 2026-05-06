import java.util.Scanner;

public class Carro extends Veiculo {

    static Scanner scan = new Scanner(System.in);

    private boolean arCondicionado;

    public Carro() {

        super();

    }

    public Carro(String placa, int valorDiaria, boolean arCondicionado) {

        super(placa, valorDiaria);
        this.arCondicionado = false;

    }

    public void cadastrarCarro() {

        System.out.println("Insira o número da placa: ");
        setPlaca(scan.next());
        System.out.println("Insira o valor da diária: ");
        setValorDiaria(scan.nextInt());
        System.out.println("O veículo possui ar-condicionado: ");
        setArCondicionado(scan.nextBoolean());
        System.out.println("Cadastrado com sucesso!");
        System.out.println();

    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }
}
