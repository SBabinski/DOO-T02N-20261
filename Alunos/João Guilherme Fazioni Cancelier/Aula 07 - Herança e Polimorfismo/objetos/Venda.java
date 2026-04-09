package objetos;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Venda {
    int quant;
    double preco;
    LocalDate data;

    public Venda(int quant, double precoTot,LocalDate data) {
        this.quant = quant;
        this.preco = precoTot;
        this.data = data;
    }

    public LocalDate getData(){
        return data;
    }

    public YearMonth getMesAno() {
        return YearMonth.from(this.data);
    }
    

    public void MostrarVenda(){
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         String dataFormatada = data.format(formatter);
        System.out.printf("Quantidade: %d, Preço Total: %.2f, Data venda: %s \n",quant,preco,dataFormatada);
    }
}
