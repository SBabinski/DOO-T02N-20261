public class Planta {
    private int quantidade;
    private double precoUnitario;

    public Planta(int quantidade, double precoUnitario) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) throw new IllegalArgumentException("Qtd não pode ser negativa");
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
}

