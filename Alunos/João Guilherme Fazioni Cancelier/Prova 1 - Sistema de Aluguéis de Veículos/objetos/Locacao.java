package objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Locacao {
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRe;
    private LocalDate dataDevo;
    private boolean situacao ;
    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRe) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRe = dataRe;
        this.situacao = false;
    }
    
    public long calculaDiaria() {
        LocalDate dataFinal;

        if (dataDevo != null) {
            dataFinal = dataDevo;
        } else {
            dataFinal = LocalDate.now();
        }

        long dias = ChronoUnit.DAYS.between(dataRe, dataFinal);

        if (dias == 0) {
            return 1;
        } else {
            return dias;
        }
    }
    public double calculaValoTotal(){
        return calculaDiaria() * veiculo.getValDia();
    }
    
    public void registrarDevolucao() {
        this.dataDevo = LocalDate.now();

        this.situacao = true;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataRe() {
        return dataRe;
    }

    public void setDataRe(LocalDate dataRe) {
        this.dataRe = dataRe;
    }

    public LocalDate getDataDevo() {
        return dataDevo;
    }

    public void setDataDevo(LocalDate dataDevo) {
        this.dataDevo = dataDevo;
    }

    public boolean isSituacao() {
        return situacao;
    }


    @Override
    public String toString() {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dataDevoFormatada;
        if (this.dataDevo != null) {
            dataDevoFormatada = this.dataDevo.format(parser);
        } else {
            dataDevoFormatada = "Ainda não devolvido";
        }

        String statusTexto;
        if (this.situacao == true) {
            statusTexto = "Realizada";
        } else {
            statusTexto = "Pendente";
        }

        return "Veiculo: " + veiculo.getPlaca() +
            " | Cliente: " + cliente.getNome() +
            " | Data de retirada: " + dataRe.format(parser) +
            " | Data de devolução: " + dataDevoFormatada +
            " | Valor total: R$ " + String.format("%.2f", calculaValoTotal()) +
            " | Status Devolução: " + statusTexto;
    }

}
