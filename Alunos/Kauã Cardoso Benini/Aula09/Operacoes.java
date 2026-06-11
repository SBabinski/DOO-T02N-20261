
public class Operacoes {

    public static double calcular(
            double n1,
            double n2,
            String operador
    ) throws CalculadoraException {

        switch (operador) {

            case "+":
                return n1 + n2;

            case "-":
                return n1 - n2;

            case "*":
                return n1 * n2;

            case "/":

                if (n2 == 0) {

                    throw new CalculadoraException(
                            "Não é possível dividir por zero!"
                    );
                }

                return n1 / n2;

            default:

                throw new CalculadoraException(
                        "Operação inválida!"
                );
        }
    }
}