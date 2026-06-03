import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Locacao {
    
    private Cliente objCliente;
    private Veiculo objVeiculo;
    private LocalDate dtaDataRetirada;
    private LocalDate dtaDataDevolucao;
    private Boolean blnStatus;

    public Locacao(Cliente pCliente, Veiculo pVeiculo, LocalDate pDataRetirada, LocalDate pDataDevolucao, Boolean pStatus) {
        this.blnStatus = pStatus;
        this.dtaDataDevolucao = pDataDevolucao;
        this.dtaDataRetirada = pDataRetirada;
        this.objCliente = pCliente;
        this.objVeiculo = pVeiculo;
    }

    public Boolean getBlnStatus() {
        return blnStatus;
    }

    public void setBlnStatus(Boolean blnStatus) {
        this.blnStatus = blnStatus;
    }

    public LocalDate getDtaDataDevolucao() {
        return dtaDataDevolucao;
    }

    public void setDtaDataDevolucao(LocalDate dtaDataDevolucao) {
        this.dtaDataDevolucao = dtaDataDevolucao;
    }

    public LocalDate getDtaDataRetirada() {
        return dtaDataRetirada;
    }

    public void setDtaDataRetirada(LocalDate dtaDataRetirada) {
        this.dtaDataRetirada = dtaDataRetirada;
    }

    public Cliente getObjCliente() {
        return objCliente;
    }

    public void setObjCliente(Cliente objCliente) {
        this.objCliente = objCliente;
    }

    public Veiculo getObjVeiculo() {
        return objVeiculo;
    }

    public void setObjVeiculo(Veiculo objVeiculo) {
        this.objVeiculo = objVeiculo;
    }

    public String ApresentaLocacao(Boolean pStatus, LocalDate pDataDevolucao, LocalDate pDataRetirada, Cliente pCliente, Veiculo pVeiculo){
        
        Boolean blnEhCarro = true;
        if (pVeiculo instanceof Moto) {
            blnEhCarro = false;
        }

        long lngDias = ChronoUnit.DAYS.between(pDataRetirada, pDataDevolucao);

        return String.format("O Cliente %s Alugou um(a) " + (blnEhCarro ? "Carro" : "Moto") + 
        " por um periodo de %s no valor R$:" + (pVeiculo.getFltValorDiaria() + lngDias) + 
        "\nData de Devolução: %s \nData de Retirada: %s \nStatus: " + (pStatus ? "Alocado" : "Devolvido"), 
        pCliente.getStrNomeCliente(), lngDias, pDataDevolucao, pDataRetirada);
    }
}
