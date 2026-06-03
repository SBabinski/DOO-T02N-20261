

    Na ciência da computação, os paradigmas de programação representam diferentes formas de estruturar e resolver problemas
computacionais. Entre eles, destacam-se o paradigma imperativo e o paradigma declarativo, que diferem 
principalmente na forma como descrevem a solução de um problema.

    Enquanto o paradigma imperativo especifica como o computador deve executar uma tarefa passo a passo, o paradigma 
declarativo descreve o que deve ser alcançado, deixando para o mecanismo de execução determinar a melhor forma
de obter o resultado.

    O Java é um exemplo clássico de linguagem que utiliza o paradigma imperativo. Nesse modelo, o programador define
explicitamente cada etapa da execução do programa, incluindo estruturas de controle, variáveis e fluxo de 
execução.
 Por exemplo, para verificar se um número pertence a uma lista, seria necessário percorrer a lista elemento por elemento:

import java.util.Arrays;
import java.util.List;

public class Exemplo {
    public static void main(String[] args) {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        int numero = 3;
        boolean encontrado = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) == numero) {
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            System.out.println("Número encontrado na lista.");
        } else {
            System.out.println("Número não encontrado.");
        }
    }
}
    Nesse exemplo, o programador precisa definir explicitamente o laço de repetição, as condições e o controle de fluxo que levam 
ao resultado final.

    Por outro lado, o Prolog segue o paradigma declarativo, mais especificamente a programação lógica. Nesse modelo, o programador 
descreve fatos e regras que representam o conhecimento sobre um determinado problema. 
    O mecanismo de inferência da linguagem é responsável por determinar se uma consulta pode ser satisfeita com base nessas
 informações.
 
    O mesmo exemplo anterior pode ser representado em Prolog da seguinte forma:
lista(1).
lista(2).
lista(3).
lista(4).
lista(5).

pertence(X) :- lista(X).

    O Prolog responderá true se o valor existir entre os fatos definidos. Nesse caso, o programador não descreve o processo de busca 
passo a passo; ele apenas declara as relações lógicas entre os dados.

    Comparando os dois paradigmas, observa-se que o modelo imperativo oferece maior controle sobre a execução do programa, sendo
amplamente utilizado no desenvolvimento de sistemas complexos e aplicações comerciais. Em contrapartida, o paradigma declarativo 
tende a produzir código mais conciso e expressivo, especialmente em problemas relacionados à lógica, inteligência artificial e 
sistemas baseados em regras.

    Portanto, embora ambos os paradigmas possam resolver o mesmo tipo de problema, eles diferem significativamente na forma como a 
solução é descrita. O paradigma imperativo enfatiza o processo de execução, enquanto o declarativo foca na descrição do 
conhecimento e das relações lógicas, delegando ao sistema a responsabilidade de encontrar a solução.
