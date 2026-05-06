package objetos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {
    private int id;
    private static int contador = 0; 
    private String titulo;
    private LocalDate dataPubli;

    public Item( String titulo, LocalDate dataPubli) {
        this.id = ++ contador;
        this.titulo = titulo;
        this.dataPubli = dataPubli;
    }

    public Item(String string, String string2, double d) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataPubli() {
        return dataPubli;
    }

    public void setDataPubli(LocalDate dataPubli) {
        this.dataPubli = dataPubli;
    }
     public  void exibir(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = getDataPubli().format(formatter);
        System.out.printf("ID: %d - Titulo: %s - Data de publicação: %s",getId(),getTitulo(),dataFormatada );
        
    }

     public double getValor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValor'");
     }
}
