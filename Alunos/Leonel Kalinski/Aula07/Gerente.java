package objetos;

public class Gerente extends Funcionarios {
	@Override
	public double calcularBonusSalarial() {
        double bonus = this.salarioBase * 0.35;
        return bonus;
    }
}
