package fag.objetos;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa{
	
	protected Loja loja;
	protected double salarioBase;
	protected ArrayList<Double> salariosRecebidos;
	
	
	public Funcionario() {
		super();
	}
	public Funcionario(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
		super(nome, idade, endereco);
		setLoja(loja);
		setSalarioBase(salarioBase);
	}

	public Loja getLoja() {
		return loja;
	}
	
	public void setLoja(Loja loja) {
		if(loja != null ) {
		this.loja = loja;
		}
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		if(salarioBase >= 0) {
			this.salarioBase = salarioBase;
		}
	}

	public ArrayList<Double> getSalariosRecebidos() {
		return salariosRecebidos;
	}
	
	public void adicionarSalarioRecebido(double pagamento) {
		if(pagamento >= 0) {
			salariosRecebidos.add(pagamento);
		}
	}
	
	public void setSalariosRecebidos(ArrayList<Double> salariosRecebidos) {
		if(salariosRecebidos != null && !salariosRecebidos.isEmpty()) {
			this.salariosRecebidos = new ArrayList<>(salariosRecebidos);
		}
	}
	
	public void apresentarse() {
		System.out.printf("Nome: %s\n", nome);
		System.out.printf("Idade: %d\n", idade);
		System.out.printf("Loja: %s\n", loja.getNomeFantasia());
		endereco.mostrarEndereco();
		System.out.println("-------------------------------------------------");
	}
	
	public double calcularMedia() {
		double soma = 0;
		
		for (double salario : salariosRecebidos) {
			soma += salario;
		}
	
		return soma / salariosRecebidos.size();
	}
	
	public double calcularBonus() {
		return salarioBase * 0.2;
	}
}
