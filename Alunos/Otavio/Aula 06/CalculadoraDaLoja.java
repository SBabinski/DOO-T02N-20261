import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraDaLoja {

    private static final double DESCONTO_PERCENTUAL = 0.05;

    public Venda realizarVenda(int quantidade, double precoUnitario) {
        double valorBruto = quantidade * precoUnitario;
        double desconto = calcularDesconto(quantidade, valorBruto);
        double valorFinal = valorBruto - desconto;
        return new Venda(quantidade, valorBruto, desconto, valorFinal, LocalDate.now());
    }

    private double calcularDesconto(int quantidade, double valorBruto) {
        if (quantidade > 10) {
            return valorBruto * DESCONTO_PERCENTUAL;
        }
        return 0;
    }

    public double calcularTroco(double valorPago, double valorCompra) {
        return valorPago - valorCompra;
    }

    public List<Venda> buscarVendasPorMesEDia(List<Venda> vendas, int mes, int dia) {
        List<Venda> resultado = new ArrayList<>();
        for (Venda v : vendas) {
            if (v.getData().getMonthValue() == mes && v.getData().getDayOfMonth() == dia) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    public List<Venda> buscarVendasPorMes(List<Venda> vendas, int mes) {
        List<Venda> resultado = new ArrayList<>();
        for (Venda v : vendas) {
            if (v.getData().getMonthValue() == mes) {
                resultado.add(v);
            }
        }
        return resultado;
    }
}
