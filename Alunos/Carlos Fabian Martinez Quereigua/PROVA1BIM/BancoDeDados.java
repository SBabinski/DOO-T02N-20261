package fag.objetos;

import java.util.ArrayList;

public class BancoDeDados {
	
	public static ArrayList<Carro> carros = new ArrayList<Carro>();
	public static ArrayList<Moto> motos = new ArrayList<Moto>();
	public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public static ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();	
	public static Locacao[] locacoes = new Locacao[10];
	
	public static void inicializar() {
		populaMotos();
		populaCarros();
		populaClientes();
		
	}

	private static void populaClientes() {
		Cliente c1 = new Cliente("João", "111.222.333-45", "12345678911");
		Cliente c2 = new Cliente("Maria", "666.777.888-90", "98765432199");
		
		clientes.add(c1);
		clientes.add(c2);
	}

	private static void populaCarros() {
		Carro c = new Carro("HESOYAM", 200.0, true);
		
		carros.add(c);		
	}

	private static void populaMotos() {
		Moto m1 = new Moto("EAZACMI", 120.0, 160);
		
		motos.add(m1);
	}
}
