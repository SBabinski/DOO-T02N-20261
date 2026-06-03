package fag;
import java.util.*;

public class CalculadoraService {
	
	private List<String> historicoVendas = new ArrayList<>();

    public double calcularPrecoTotal(Planta planta) {
    	double valorBruto = planta.getQuantidade() * planta.getPrecoUnitario();
    	double valorFinal = valorBruto;
    	double desconto = 0;
    	
    	if(planta.getQuantidade() > 10) {
    		valorFinal = valorBruto * 0.95;
    		desconto = valorFinal - valorBruto;
    	}
    	
    	String registro = String.format("Qtd: %d, Total: %.2f, Desconto: %.2f", planta.getQuantidade(), valorFinal, desconto);
    	historicoVendas.add(registro);
    	
    	return valorFinal;
    }
    
    public void mostrarHistorico() {
    	System.out.println("HISTÓRICO DE VENDAS");
    	if(historicoVendas.isEmpty()) {
    		System.out.println("Nenhuma venda válida");
    	} else {
    		for(String v : historicoVendas) {
    			System.out.println(v);
    		}
    	}
    }
    
    
    public double processarTroco(double valorRecebido, double valorTotalCompra) {
        double troco = valorRecebido - valorTotalCompra;
        return troco;
    }
}
