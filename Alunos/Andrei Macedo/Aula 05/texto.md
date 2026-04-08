# Paradigmas da Programação

Paradigma de programação é definido como um conjunto de regras, design e padrões específicos, que definem a forma que um programa é escrito para resolver um problema por meio de uma linguagem de programação. Existem diferentes estilos adotados por um desenvolvedor que definem um modelo de paradigma, as duas principais abordagens são os paradigmas imperativos e declarativos, cada qual com suas características próprias.

## Paradigma Imperativo

Existe uma sequência de instruções à serem executadas, se faz necessário que cada instrução diga exatamente o que o programa deve fazer.

### Estruturada

Maneira de programar utilizando estruturas definidas, onde o programa executa linha por linha com fluxo de controle e tomada de decisões, baseando-se em blocos com condicionais que expressam situações e suas consequências; existem também as construções de iteração (do while, for) e as funções que executam um bloco específico. 

### Procedural

Baseado em funções e procedimentos com foco em dividir o programa em blocos separados, onde cada parte faz suas próprias instruções.

## Paradigma Declarativo

O foco não está no fluxo de controle e sim no resultado, e o sistema define a melhor forma de chegar até ele.

### Funcional
 
Baseado em uma sequência de funções matemáticas com dados imutáveis (é criado novos valores, ao invés de alterar), que combinadas, irão resolver um problema, diferente de uma lista de instruções ou objetos.

### Lógica
É um paradigma onde não há uma sequência de instruções que diga exatamente o que o programa deve fazer, o foco consiste na lógica. O programador define o problema (lógica, regras e fatos) à ser resolvido, e o sistema decide da melhor forma como chegar ao resultado. 

## COMPARAÇÃO
```código``` 
```java
public class Soma {
    public static void main(String[] args) {
        int[] numeros = {10, 5, 20, 8};
        int maior = numeros[0]; 

        for (int i = 1; i < numeros.length; i++) { 
            if (numeros[i] > maior) {              
                maior = numeros[i];                
            }
        }
    System.out.println("O maior é: " + maior);
    }
}
```
No Java é necessário elaborar sequências e instruções e uma estratégia de comparação para identificar o maior número do vetor. O laço de repetição testara todos os números do vetor até a última posição, e por fim exibirá o resultado obtido. 

---

```código```
```prolog
pai(joao, jose).
pai(jose, carlos).

avo(X, Y) :- pai(X, Z), pai(Z, Y).
```
No Prolog, não há "passo a passo". Nós declaramos fatos e regras. O objetivo aqui é saber quem é o avô de alguém. O Prolog usa lógica para verificar se a relação é verdadeira ou para encontrar quem satisfaz a regra.

# Conclusão

Ambos os exemplos evidenciam as principais diferenças entre paradigmas, Java sendo orientada a objetos mas também estruturada e procedural; e Prolog sendo definido como programação lógica. O motivo dessa definição pode ser compreendido porque ao invés de dar ordens ao interpretador, o programador fornece regras e as relações entre elas.

