package fag.objetos;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Funcionario{

	public Vendedor() {
		
	}
	
	public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
		super(nome, idade, endereco, loja, salarioBase);
		
		this.salariosRecebidos = new ArrayList<Double>(List.of(5000.0, 6000.0, 7000.0));
	}
	
}
