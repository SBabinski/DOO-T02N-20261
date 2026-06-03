import java.util.ArrayList;
import java.util.List;

public class Calculadora{

private List <String> historico = new ArrayList<>();

public double calcularOperacoes (double numero1, double numero2, String operador) throws CalculadoraException {

double resultado;

if (operador.equals("÷") && numero2 == 0){
    throw new 
    CalculadoraException ("Erro! Divisao por zero nao existe");

}

switch (operador){

    case "+" :
        resultado = numero1 + numero2;
        break;


case "-":
    resultado = numero1 - numero2;
    break;

case "÷":
    resultado = numero1 / numero2;
    break;

case "×":
    resultado = numero1 * numero2;
    break;

    default: throw new 
    CalculadoraException ("Operador invalido");

}


historico.add(numero1 + " " + operador + " " + numero2 + "=" + resultado);

return resultado;

}

public String getHistoricoFormatado (){
    return String.join ("\n",historico);

}
}