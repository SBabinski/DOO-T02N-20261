public class Locacao {
    public Cliente cliente;  
    public Veiculo veiculo;   
    public String dataRetirada;
    public String dataDevolucao;
    public int qtdDiarias;
    public boolean devolvida;

    public void exibirDadosLocacao() {
        double valorTotal = qtdDiarias * veiculo.valorDiaria;
        System.out.println("Cliente: " + cliente.nome);
        veiculo.exibirInformacoes();
        System.out.println("Total: R$ " + valorTotal);
    }
}