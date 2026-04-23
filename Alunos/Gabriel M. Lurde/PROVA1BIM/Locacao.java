public class Locacao {

    Cliente cliente;
    Veiculo veiculo;
    int dias;

    String dataRetirada;
    String dataDevolucao;

    boolean devolvido;

    double calcularTotal() {
        return dias * veiculo.valorDiaria;
    }

    void mostrar() {
        System.out.println("Cliente: " + cliente.nome);
        System.out.println("Veiculo: " + veiculo.nome);
        System.out.println("Dias: " + dias);
        System.out.println("Data retirada: " + dataRetirada);
        System.out.println("Data devolucao: " + dataDevolucao);
        System.out.println("Status: " + (devolvido ? "Devolvido" : "Pendente"));
        System.out.println("Total: " + calcularTotal());
        System.out.println("---------------------");
    }

    void mostrarLocacao() {
        mostrar();
    }
}