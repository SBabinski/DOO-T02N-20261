import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CalculadoraService {
	
	private List<String> historicoVendas = new ArrayList<>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public double calcularPrecoTotal(Planta planta, LocalDate dataVenda) {
    	double valorBruto = planta.getQuantidade() * planta.getPrecoUnitario();
    	double valorFinal = valorBruto;
    	double desconto = 0;
    	
    	if(planta.getQuantidade() > 10) {
    		valorFinal = valorBruto * 0.95;
    		desconto = valorFinal - valorBruto;
    	}
    	
        String dataFormatada = dataVenda.format(formatter);
        String registro = String.format("[%s] Qtd: %d, Total: %.2f, Desconto: %.2f", 
                                        dataFormatada, planta.getQuantidade(), valorFinal, desconto);
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
    
    public void buscarPorData(LocalDate dataBusca) {
        String dataFormatada = dataBusca.format(formatter);
        int quantidadeVendas = 0;
        
        System.out.println("\n--- Vendas do dia: " + dataFormatada + " ---");
        
        for(String registro : historicoVendas) {
        
            if(registro.startsWith("[" + dataFormatada + "]")) {
                System.out.println(registro);
                quantidadeVendas++;
            }
        }
        
        if (quantidadeVendas == 0) {
            System.out.println("Nenhuma venda encontrada para esta data.");
        } else {
            System.out.println("Total de transações neste dia: " + quantidadeVendas);
        }
    }
    
    
    public double processarTroco(double valorRecebido, double valorTotalCompra) {
        double troco = valorRecebido - valorTotalCompra;
        return troco;
    }
}
