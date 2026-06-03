### 1. Conceito

* **Paradigma Imperativo**: O foco está no como fazer. É necessário descrever o passo a passo exato (o fluxo de controle) que o computador deve seguir para alterar o estado do programa e alcançar o resultado, é baseado em comandos, laços de repetição e variáveis.
* **Paradigma Declarativo**: O foco está no o que deve ser feito. O programador descreve a lógica do problema, as regras ou os fatos, sem especificar o fluxo de controle detalhado. O motor da linguagem (ou o compilador) se encarrega de descobrir *como* chegar a esse resultado.

### 2. Filtrando Números Pares

Para mostrar a diferença entre os dois, vamos usar um problema clássico: dada uma lista de números, precisamos extrair apenas os números **pares**.

#### A Abordagem Imperativa
O Java, amplamente utilizado na construção de sistemas, foi concebido com uma forte base imperativa (orientada a objetos), com Java, é resolvido da seguinte maneira:

```
import java.util.ArrayList;
import java.util.List;

public class Filtro {
    public static List<Integer> filtrarPares(List<Integer> numeros) {
        List<Integer> pares = new ArrayList<>(); 
        
        for (int numero : numeros) {             
            if (numero % 2 == 0) {               
                pares.add(numero);               
            }
        }
        
        return pares;
    }
}
```

**Como funciona**:
Este código é um roteiro explícito, ele manda o computador criar uma nova lista vazia na memória, em seguida, manda a máquina a iterar item por item da lista original, para cada item, ele manda verificar se o resto da divisão por 2 é zero, se for, ele ordena que o número seja adicionado à nova lista, modificando o estado da variável **pares**. O controle do fluxo é totalmente manual.

#### A Abordagem Declarativa 
O Prolog (Programming in Logic) é o exemplo clássico do paradigma lógico/declarativo, nele, você não escreve rotinas, mas sim "fatos" e "regras".

```
% Regra Base: Uma lista vazia não tem pares.
pares([], []).

% Regra 1: Se o primeiro elemento (H) é par, ele entra na lista de resultado.
pares([H|T], [H|ResultadoT]) :- 
    0 is H mod 2, 
    pares(T, ResultadoT).

% Regra 2: Se o primeiro elemento (H) é ímpar, ele é ignorado.
pares([H|T], ResultadoT) :- 
    1 is H mod 2, 
    pares(T, ResultadoT).
```

**Como funciona:**
Em vez de dizer ao computador **como** percorrer a lista, você declara **o que** é uma lista de pares através de regras de padrão matemático:
-   A primeira linha declara um fato: o filtro de pares de uma lista vazia `[]` é outra lista vazia `[]`.
-  A segunda regra diz que, ao dividir uma lista entre a "Cabeça" (primeiro item, `H`) e a "Cauda" (o resto, `T`), se `H` for divisível por 2, então a lista resultante deve começar com `H`, seguida pelos pares do resto da lista (`ResultadoT`).
-  A terceira regra afirma que, se `H` for ímpar, a lista resultante será apenas os pares do resto da lista, ignorando o `H`.

Você não cria variáveis que mudam de valor (mutação), nem laços de repetição `for` ou `while`, você apenas define a verdade lógica do sistema, e o motor de inferência do Prolog cuida de aplicar a recursão e o **pattern matching** para gerar a resposta.