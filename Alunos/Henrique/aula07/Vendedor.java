package fag;

public class Vendedor extends Pessoa{
    Loja loja;
    double SalarioBase;
    double[] SalarioRecebido;


public Vendedor(String nome, int idade, Loja loja, Endereco endereco, double SalarioBase, double[] SalarioRecebido){
   
	super(nome, idade, endereco);
    this.loja = loja;
    if (SalarioBase > 0) {
        this.SalarioBase = SalarioBase;
    }else {
        System.out.println("Salário base inválido. Por favor tente novamente.");
    }
    if (SalarioRecebido != null && SalarioRecebido.length == 3) {
        this.SalarioRecebido = SalarioRecebido;
    }else {
        System.out.println("Informe ao menos 3 lançamentos de salário.");
    }
    
}

@Override
public void apresentarse() {
	System.out.println("----APRESENTAÇÃO DO VENDEDOR----");
	super.apresentarse();
	System.out.println("Loja: "+loja.nomeFantasia);
}

public double calcularMedia() {
	double soma = 0;
	for (double s : SalarioRecebido) {
		soma += s;
	}
	double media = soma / 3;
	System.out.println("A média é "+ media);
	return media;
}

public double calcularBonus() {
	double bonus = SalarioBase * 0.2;
	System.out.println("O Bônus é: "+bonus);
	return bonus;
}

}