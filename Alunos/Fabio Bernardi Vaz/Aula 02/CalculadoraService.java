public class CalculadoraService {

    public double calcularPrecoTotal(Planta planta) {
        return planta.getQuantidade() * planta.getPrecoUnitario();
    }
    public double processarTroco(double valorRecebido, double valorTotalCompra) {
        double troco = valorRecebido - valorTotalCompra;
        return troco;
    }
}
