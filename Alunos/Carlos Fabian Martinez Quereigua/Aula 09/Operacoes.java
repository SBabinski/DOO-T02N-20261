package calculadoraSwing;

public class Operacoes {

	public static double calcular(double n1, double n2, String operador)
			throws Exception {

		switch (operador) {

		case "+":
			return n1 + n2;

		case "-":
			return n1 - n2;

		case "*":
			return n1 * n2;

		case "/":

			if (n2 == 0) {
				throw new Exception("Não é possível dividir por zero. \n"
						+ "falei pra nao fazer...");
			}

			return n1 / n2;

		default:
			throw new Exception("Operação inválida.");
		}
	}
}