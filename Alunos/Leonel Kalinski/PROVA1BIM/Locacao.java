package objetos;
import java.time.LocalDate;

public class Locacao {
	private Clientes cliente;
	private Veiculos veiculos;
	private LocalDate retirada;
	private int dias;
	private boolean devolvido;
	public Locacao(Clientes cliente, Veiculos veiculos, int dias) {
	
		this.cliente = cliente;
		this.veiculos = veiculos;
		this.retirada = LocalDate.now();
		this.devolvido = false;
		this.dias = dias;
		
	}
	
	public void devolver() {
        this.devolvido = true;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public LocalDate getDataPrevista() {
        return retirada.plusDays(dias);
    }
    public int valTotal() {
		return  veiculos.getValdia()*dias; 
	}
	public void exibir() {
		System.out.println("Clientes: " + cliente.getNome());
		System.out.println("Veiculos: " + veiculos.getPlaca());
		System.out.println("Data da retirada: " + retirada);
		System.out.println("Data da devolucao: " + getDataPrevista());
		System.out.println("Valor Total: " + valTotal());
		System.out.println("Devolvido: " + (devolvido? "sim":"nao"));
        System.out.println("------------------------");

	}
}
