package Objetos;

import java.util.Scanner;

public class Veiculo {

    static Scanner scan = new Scanner(System.in);

    private String tipo_veiculo;
    private String placa;
    private double valor_diaria;

    public Veiculo() {

    }

    public Veiculo(String tipo_veiculo ,String placa, double valor_diaria) {
        setTipoVeiculo(tipo_veiculo);
        setPlaca(placa);
        setValorDiaria(valor_diaria);
    }

    public void setTipoVeiculo(String tipo_veiculo) {

        if (tipo_veiculo == null || tipo_veiculo.isBlank()) {
            System.out.println("Valor invalido");
            System.out.printf("Informe o tipo do veiculos: ");
            tipo_veiculo = scan.nextLine();
        }

        this.tipo_veiculo = tipo_veiculo;
    }

    public void setPlaca(String placa) {

        if (placa == null || placa.isBlank()) {
            System.out.println("Valor invalido");
            System.out.printf("Informe a placa do veiculo: ");
            placa = scan.nextLine();
        }

        this.placa = placa;
    }

    public void setValorDiaria(double valor_diaria) {

        if (valor_diaria <= 0) {
            System.out.println("Valor invalido");
            System.out.printf("Informe o valor da diaria do veiculo: ");
            valor_diaria = scan.nextDouble();
        }

        this.valor_diaria = valor_diaria;
    }

    public String getTipoVeiculo() {
        return tipo_veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public double getValorDiaria() {
        return valor_diaria;
    }

}