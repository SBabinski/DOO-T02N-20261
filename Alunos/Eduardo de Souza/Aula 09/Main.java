package calculadora;

import calculadora.controller.CalculadoraController;
import calculadora.view.CalculadoraView;

public class Main {

	public static void main(String[] args) {

		CalculadoraView view = new CalculadoraView();

		new CalculadoraController(view);
	}
}