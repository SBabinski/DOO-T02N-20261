package Objetos;

import java.time.LocalDate;

public class RegistroVenda {

    private LocalDate data;
    private double valor;

    public RegistroVenda(LocalDate data, double valor) {
        this.data = data;
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
}