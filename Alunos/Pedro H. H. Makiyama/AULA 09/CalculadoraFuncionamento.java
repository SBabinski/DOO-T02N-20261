public class CalculadoraFuncionamento {

    double num1 = Double.NaN;
    double num2 = Double.NaN;
    String operador = null;

    public double operacao(){

        switch (operador){

            case "+":

                return num1 + num2;

            case "-":

                return num1 - num2;

            case "X":
                
                return num1 * num2;

            case "/":

                return num1 / num2;

            default:

                System.out.println("Erro!\n");
                return -1;
        }
    }
}
