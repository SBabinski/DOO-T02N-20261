package objetos;

public class RegistroDeVendas {
		
	double qtd;
	double preco;
	double valorTotal;
	
public RegistroDeVendas(double qtd, double preco, double valorTotal) {
	 setPreco(preco);
	 setQtd(qtd);
	 setValorTotal(valorTotal);
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
    return "Registro{" + "Quantidade da planta='" + qtd + '\'' + ", Preco=" + preco + '\'' + ", Valor total ganho="+valorTotal +'}';
}
}