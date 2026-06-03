package objetos;

public class Venda {
    int quant;
    double preco;

    public Venda(int quant, double precoTot) {
        this.quant = quant;
        this.preco = precoTot;
    }

    public void MostrarVenda(){
        System.out.printf("Quantidade: %d, Preço Total: %.2f \n",quant,preco);
    }
}
