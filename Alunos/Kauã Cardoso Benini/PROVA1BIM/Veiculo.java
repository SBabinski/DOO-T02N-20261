import java.util.Scanner;

public class Veiculo {

    protected String placa;
    protected double preco;

    public Veiculo() {

    }


    public static void cadastroVeiculo(Scanner scan){

        System.out.println("Qual tipo de veiculo deseja cadastrar?");
        System.out.println("1. Carro");
        System.out.println("2. Moto");
        System.out.println("0. Cancelar");


        int opcao = scan.nextInt();
        switch(opcao){
            case 1:
                cadastroCarro(scan);
                break;
            case 2:
                cadastroMoto(scan);
                break;
            case 0:
                break;
        }

    }

    public static void cadastroCarro(Scanner scan){

        Carro carro = new Carro();

        scan.nextLine();
        System.out.println("Qual a placa do carro que deseja cadastrar?");
        carro.setPlaca(scan.nextLine());

        System.out.println("Qual o valor do carro que deseja cadastrar?");
        carro.setPreco(scan.nextDouble());

        System.out.println("tem ar-condicionado? (true/false)");
        carro.setAr(scan.nextBoolean());

        Lista.veiculos.add(carro);

    }

    public static void cadastroMoto(Scanner scan){
        Moto moto = new Moto();
        scan.nextLine();
        System.out.println("qual a placa da moto que deseja cadastrar?");
        moto.setPlaca(scan.nextLine());

        System.out.println("Qual o valor do motor que deseja cadastrar?");
        moto.setPreco(scan.nextDouble());
        scan.nextLine();

        System.out.println("Quantas cilindradas possui?");
        moto.setClindradas(scan.nextInt());
        scan.nextLine();
        
        Lista.veiculos.add(moto);
    }



    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
