# Paradigmas de Programação - Imperativo e Declarativo

Os paradigmas de programação são formas diferentes de desenvolver soluções para um problema, entre eles destacam-se o paradigma IMPERATIVO e o DECLARATIVO.
O PARADIGMA IMPERATIVO é baseado em instruções passo a passo, onde o programador define exatamente como o programa deve executar cada tarefa, nesse modelo são utilizados comandos, variáveis e estruturas de controle para guiar o fluxo do programa.
Já o PARADIGMA DECLARATIVO tem uma abordagem diferente, pois foca no resultado final ao invés do processo, já nele o programador descreve o que deseja obter, e a linguagem se encarrega de determinar como chegar até esse resultado.

EXEMPLOS:

```java

// EXEMPLO DE JAVA:

int soma = 0;
for (int i = 1; i <= 5; i++) {
    soma = soma + i;
}
System.out.println(soma);

```

```prolog

// EXEMPLO DE PROLOG:

soma(0, 0).
soma(N, R) :-
    N > 0,
    N1 is N - 1,
    soma(N1, R1),
    R is R1 + N.

```


No exemplo em JAVA acima o código utiliza o PARADIGMA IMPERATIVO, pois mostra passo a passo como resolver o problema, primeiro é criada uma variável chamada soma com valor inicial 0, e em seguida é utilizado um laço de repetição que começa no número 1 e vai até o número 5. A cada repetição, o valor da variável soma é atualizado adicionando o valor atual de " i " Ao final do processo, o programa exibe o resultado da soma de todos os números, mostrando exatamente como o cálculo foi feito.

Já no exemplo em PROLOG o código utiliza o PARADIGMA DECLARATIVO, pois define apenas a regra para resolver o problema. Primeiro é estabelecido um caso base aonde a soma de 0 é igual a 0, depois é criada uma regra que diz que para um número maior que 0, o programa deve calcular a soma do número anterior e adicionar o valor atual. Dessa forma o Prolog vai resolvendo o problema de forma automática seguindo as regras definidas até chegar ao resultado final.
