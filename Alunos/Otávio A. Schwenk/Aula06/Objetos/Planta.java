package Objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Planta {

    private String planta;
    private double valor;
	private double total;
	private double desconto;
    private int quant;
	private LocalDate data;

    public Planta(){
    }

    public Planta(String planta, double valor, int quant, LocalDate data){
        setPlanta(planta);
		setValor(valor);
		setQuant(quant);
		setTotal(valor, quant);
		setDesconto(total);
		setData(data);
    }

    public String getPlanta() {
		return planta;
	}

    public double getValor() {
		return valor;
	}

    public int getQuant() {
		return quant;
	}

	public double getTotal() {
		return total;
	}

	public double getDesconto(){
		return desconto;
	}

	public LocalDate getData(){
		return data;
	}

    public void setPlanta(String planta) {
		if(planta != null && !planta.isBlank()) {
			this.planta = planta;
		}
	}
	
	public void setValor(double valor) {
		if(valor>=0) {
			this.valor = valor;
		}
	}
	
	public void setQuant(int quant) {
		if(quant>=0) {
			this.quant = quant;
		}
	}

	public void setTotal(double valor, int quant) {
		this.total = valor*quant;
	}

	public void setDesconto(double total){
			this.desconto = total*0.05;
	}

	public void setData(LocalDate data){
		this.data = data;
	}

    public void mostrarPlanta(){
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = data.format(formatador);
		if(desconto==0){
        	System.out.printf("Dia %s %d %s, no valor de R$%.2f = R$%.2f\n", dataFormatada, quant, planta, valor, total);
		}else{
			System.out.printf("Dia %s %d %s, no valor de R$%.2f = R$%.2f (desconto de R$%.2f)\n"
			, dataFormatada, quant, planta, valor, total, desconto);
		}
    }

    @Override
	public String toString() {
		return "Planta [planta=" + planta + ", valor=" + valor + ", quant=" + quant + ", total=" + total + ", desconto=" + desconto + ", data=" + data + "]";
	}

}