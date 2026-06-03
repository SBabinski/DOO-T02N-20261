
import java.time.LocalDate;
import java.util.Scanner;

public class Venda {
    static Scanner scan = new Scanner(System.in);

    private int quantidade;
    private float valor;
    private float desconto = 0;
    private LocalDate dataCompra = null;
    
    public Venda(){

    }
    public Venda(int quantidade,float valor,float desconto){
        setDesconto(desconto);
        setQuantidade(quantidade);
        setValor(valor);
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        if(quantidade>0){
            this.quantidade = quantidade;
        }else{
            System.out.println("quantidade invalida, tente novamente");
            int escolha = scan.nextInt();
            scan.nextLine();
            setQuantidade(escolha);
        }
        
    }
    public LocalDate getDataCompra() {
        return dataCompra;
    }
    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public float getDesconto() {
        return desconto;
    }
    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }
    @Override
    public String toString() {
        return "Venda [quantidade=" + quantidade + ", valor=" + valor + ", desconto=" + Math.abs(desconto) + ", dia="+dataCompra+"]";
    }
    
    
}
