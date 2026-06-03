package fag.objetos;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Funcionario{
	
	public Gerente() {
		
	}
	
	public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
		super(nome, idade, endereco, loja, salarioBase);
		
		this.salariosRecebidos = new ArrayList<Double>(List.of(10000.0, 12000.0, 11000.0));
	}
	
	@Override
	public double calcularBonus() {
		return salarioBase * 0.35;
	}
}
