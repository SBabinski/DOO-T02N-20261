package Objetos;

import java.sql.Date;

public class Locacao {
 
    private Cliente cliente;
    private Veiculo veiculo;
    private Date data_retirada;
    private Date data_devolucao;
    private boolean devolvido;
    private double total_diaria;

    public Locacao() {

    }

    public Locacao(Cliente cliente, Veiculo veiculo, Date data_retirada, 
        Date data_devolucao, boolean devolvido, double total_diaria) {
        
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.data_retirada = data_retirada;
        this.data_devolucao = data_devolucao;
        this.devolvido = devolvido;
        this.total_diaria = total_diaria;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setDataRetirada(Date data_retirada) {
        this.data_retirada = data_retirada;
    }

    public void setDataDevolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public void setTotalDiaria(double total_diaria) {
        if (total_diaria < 0) {
            System.out.println("Valor invalido");
            return;
        }
        this.total_diaria = total_diaria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Date getDataRetirada() {
        return data_retirada;
    }

    public Date getDataDevolucao() {
        return data_devolucao;
    }

    public boolean getDevolvido() {
        return devolvido;
    }

    public double getTotalDiaria() {
        return total_diaria;
    }

    public void infoLocacao() {
        System.out.println("=================================");
        System.out.println("Informacoes da locacao");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Veiculo: " + veiculo.getPlaca());
        System.out.println("Data retirada: " + data_retirada);
        System.out.println("Data devolucao: " + data_devolucao);
        System.out.println("Devolvido: " + (devolvido ? "Sim" : "Nao"));
        System.out.printf("Total diaria R$: %.2f \n", total_diaria);
    }
}