package objetos;

public class Vendedor extends Funcionarios {
	@Override
    public double calcularBonusSalarial() {
        double bonus = this.salarioBase * 0.2;
        return bonus;
    }
    
}
