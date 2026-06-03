public class Locacao {
    public Clientes cliente;
    public Veiculos veiculo;
    public String dataRetirada; 
    public String dataDevolucao; 
    public boolean devolvido; 
    public int quantidadeDiarias;

    public Locacao(Clientes cliente, Veiculos veiculo, String dataRetirada){
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
    }

    public void calcularValorTotal(){
        double valorTotal = quantidadeDiarias * veiculo.valorDiaria;
        System.out.println("Valor toral a pagar: R$"+valorTotal);
    } 

    public void visualizarDados(){
        System.out.println("dono do veiculo "+veiculo+": "+cliente.nome+" (cpf: "+cliente.cpf+")");
        System.out.println("Placa do veiculo: "+veiculo.placa);
        System.out.println("Data Retirada: " +dataRetirada+ " | Data Devolução: " +dataDevolucao);

        if(devolvido == true){
        System.out.println("Situação: devolvido");
    }else{
        System.out.println("Situação: não devolvido");
    }
    }
}
