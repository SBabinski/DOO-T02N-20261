package Objetos;
public class Planta {

    private String planta;
    private double valor;
	private double total;
	private double desconto;
    private int quant;

    public Planta(){
    }

    public Planta(String planta, double valor, int quant, double total, double desconto){
        setPlanta(planta);
		setValor(valor);
		setQuant(quant);
		setTotal(total);
		setDesconto(desconto);
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

	public void setTotal(double total) {
		if(total>=0) {
			this.total = total;
		}
	}

	public void setDesconto(double desconto){
		if(desconto>=0){
			this.desconto = desconto;
		}
	}

    public void mostrarPlanta(){
		if(desconto==0){
        	System.out.printf("%d %s, no valor de R$%.2f = R$%.2f\n", quant, planta, valor, total);
		}else{
			System.out.printf("%d %s, no valor de R$%.2f = R$%.2f (desconto de R$%.2f)\n", quant, planta, valor, total, desconto);
		}
    }

    @Override
	public String toString() {
		return "Planta [planta=" + planta + ", valor=" + valor + ", quant=" + quant + ", total=" + total + ", desconto=" + desconto +"]";
	}

}