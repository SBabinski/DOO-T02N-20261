import java.util.Date;

public class Locacao {

    private Cliente cliente;
    private Veiculos veiculo;
    private int dias;
    private Date data;
    private boolean devolvido;

    public Locacao(Cliente cliente, Veiculos veiculo, int dias) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dias = dias;
        this.data = new Date();
        this.devolvido = false;
    }

    public double calcularTotal() {
        return dias * veiculo.getValorDiaria();
    }

    public void devolver() {
        devolvido = true;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void mostrar() {

        System.out.println("\n=== LOCAÇÃO ===");
        cliente.mostrar();
        veiculo.mostrar();
        System.out.println("Dias: " + dias);
        System.out.println("Total: R$ " + calcularTotal());
        System.out.println("Status: " + (devolvido ? "Devolvido" : "Ativo"));
    }
}