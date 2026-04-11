package objetos;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroDeVendas {
		
	double qtd;
	double preco;
	double valorTotal;
	LocalDateTime dataVenda;
	
public RegistroDeVendas(double qtd, double preco, double valorTotal, LocalDateTime dataVenda) {
	 setPreco(preco);
	 setQtd(qtd);
	 setValorTotal(valorTotal);
	 this.dataVenda = dataVenda; 
}

public LocalDateTime getDataVenda() {
	return dataVenda;
}


public double getPreco() {
    return preco;
}
public double getQtd() {
	return qtd;
}
public double getValorTotal() {
	return valorTotal;
}
public void setPreco(double preco) {
    if (preco > 0) {
        this.preco = preco;
    } 
} 
public void setQtd(double qtd) {
        if (qtd > 0) {
            this.qtd = qtd;
        }
}
public void setValorTotal(double valorTotal) {
            if (valorTotal > 0) {
                this.valorTotal = valorTotal;
            } 
}
@Override
public String toString() {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
    return "Registro{" + "Quantidade da planta='" + qtd + '\'' + ", Preco=" + preco + '\'' + ", Valor total ganho="+valorTotal +", Data=" + dataVenda.format(formatter) +
            '}';
}
}