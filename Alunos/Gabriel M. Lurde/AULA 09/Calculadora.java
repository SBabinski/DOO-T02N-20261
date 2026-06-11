public class Calculadora {

    public double calcular(double n1, double n2, String operacao) throws CalculadoraException {

        if (operacao.equals("+")) {
            return n1 + n2;
        }

        if (operacao.equals("-")) {
            return n1 - n2;
        }

        if (operacao.equals("*")) {
            return n1 * n2;
        }

        if (operacao.equals("/")) {

            if (n2 == 0) {
                throw new CalculadoraException("Nao e possivel dividir por zero.");
            }

            return n1 / n2;
        }

        throw new CalculadoraException("Operacao invalida.");
    }
}