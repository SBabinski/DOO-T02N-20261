public class Veiculo {

    String nome;
    String placa;
    double valorDiaria;

    public Veiculo(String nome, String placa, double valorDiaria) {
        this.nome = nome;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }

    void mostrarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Placa: " + placa);
        System.out.println("Valor diaria: " + valorDiaria);
    }
}