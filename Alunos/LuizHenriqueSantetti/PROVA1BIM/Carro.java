public class Carro extends Veiculo {
    boolean temAr;

    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println(" | Ar-condicionado: " + (temAr ? "Sim" : "Nao"));
    }
}