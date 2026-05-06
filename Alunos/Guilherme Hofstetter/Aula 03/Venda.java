public class Venda {
    private int quantidade;
    private double valorVenda;
    private double descontoAplicado;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getDescontoAplicado() {
        return descontoAplicado;
    }

    public void setDescontoAplicado(Double descontoAplicado) {
        this.descontoAplicado = descontoAplicado;
    }
}