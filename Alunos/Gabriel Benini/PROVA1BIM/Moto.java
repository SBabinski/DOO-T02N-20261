import java.util.Scanner;

public class Moto extends Veiculo {

    static Scanner scan = new Scanner(System.in);

    private int cilindrada;

    public Moto() {

        super();

    }

    public Moto(String placa, int valorDiaria, int cilindrada) {

        super(placa, valorDiaria);
        this.cilindrada = cilindrada;

    }

    public void cadastrarMoto() {

        System.out.println("Insira o número da placa: ");
        setPlaca(scan.next());
        System.out.println("Insira o valor da diária: ");
        setValorDiaria(scan.nextInt());
        System.out.println("Insira a cilindrada da moto: ");
        setCilindrada(scan.nextInt());
        System.out.println("Cadastrado com sucesso!");
        System.out.println();

    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
}