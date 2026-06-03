package objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private Item item;
    private Leitor leitor;
    private LocalDate data;
    private int dias;
    private boolean situacao ;

    public Emprestimo(Item item, Leitor leitor, LocalDate data, int dias) {
        this.item = item;
        this.leitor = leitor;
        this.data = data;
        this.dias = dias;
        this.situacao = false;
    }

    public LocalDate previEntrega() {
        return this.data.plusDays(dias);
    }

    public void registrarDevolucao() {
        this.situacao = true;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         String statusTexto;
    
        if (this.situacao == true) {
            statusTexto = "Realizada";
        } else {
            statusTexto = "Pendente";
        }
        return "Item: " + item.getTitulo() + 
               " | Leitor: " + leitor.getNome() + 
               " | Data Empréstimo: " + data.format(parser) + 
               " | Previsão Devolução: " + previEntrega().format(parser) + 
               " | Status Devolução: " + statusTexto;
    }
}
