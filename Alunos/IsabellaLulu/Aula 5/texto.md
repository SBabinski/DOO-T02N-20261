# Paradigmas Imperativo e Declarativo

Os paradigmas de programação representam diferentes formas de pensar e estruturar soluções computacionais. Entre eles, destacam-se o **imperativo** e o **declarativo**, que se diferenciam principalmente pela forma como a lógica do programa é expressa.

O **paradigma imperativo** é baseado na descrição de *como* um problema deve ser resolvido. Linguagens como Java seguem esse modelo, no qual o programador define passo a passo as instruções que o computador deve executar. Nesse contexto, há controle explícito do fluxo do programa, com uso de variáveis, laços de repetição e estruturas condicionais.

Por outro lado, o **paradigma declarativo** foca em descrever *o que* deve ser resolvido, sem detalhar o processo. Linguagens como Prolog utilizam esse paradigma, onde o programador define fatos e regras lógicas, e o sistema se encarrega de encontrar soluções que satisfaçam essas definições.

## Exemplo: Cálculo da média de 3 valores

### Java (Paradigma Imperativo)

```java
public double calcularMedia(double a, double b, double c) {
    double soma = a + b + c;
    double media = soma / 3;
    return media;
}
```
Neste exemplo, o código descreve claramente cada passo:

1. Soma os três valores
2. Divide o resultado por 3
3. Retorna a média

Ou seja, o programador define as variáveis, realiza a operação matemática passo a passo e armazena o resultado em outra variável. O fluxo é linear e totalmente controlada.

### Prolog (Paradigma Declarativo)

```Prolog
media(A, B, C, M) :-
    M is (A + B + C) / 3.
```

Aqui, o cálculo da média é definido como uma regra lógica. Não há um fluxo de execução explícito, em vez disso, declara-se uma relação entre os valores e o resultado.

## Análise Comparativa 

Ambos os códigos atingem o mesmo objetivo, calcular a média de três valores, porém por abordagens distintas. No Java, há um controle detalhado do processo, evidenciando o *como fazer*. Já no Prolog, o foco está na definição da relação matemática, destacando o *o que fazer*.

Essa diferença evidencia como o paradigma influencia diretamente a forma de pensar do programador, enquanto o **imperativo** exige uma visão procedural e sequencial, o **declarativo** promove uma abordagem mais abstrata e baseada em lógica.


