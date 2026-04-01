# Paradigmas de Programação: Imperativo e Declarativo

Os paradigmas de programação representam diferentes formas de estruturar e desenvolver soluções computacionais. Entre eles, destacam-se o **paradigma imperativo** e o **paradigma declarativo**, que se diferenciam principalmente pela forma como lidam com a execução das instruções.

## Paradigma Imperativo

O paradigma imperativo é dirigido por comandos e caracteriza-se por executar o programa como uma **sequência de instruções**, realizadas passo a passo. Nesse modelo, cada linha de código define não apenas o que deve ser feito, mas também **como deve ser feito**.

Um exemplo simples fora da programação seria o preparo de um chá:

* Vá até a cozinha
* Pegue uma panela
* Adicione água
* Ferva a água
* Coloque o chá na xícara
* Despeje a água quente

Nesse caso, todas as etapas são descritas detalhadamente, mostrando exatamente **como** atingir o objetivo.

Dentro do paradigma imperativo, destacam-se algumas abordagens:

* **Programação estruturada**: utiliza estruturas como `if`, `else`, `for` e `while`.
* **Programação procedural**: organiza o código em funções ou procedimentos.
* **Programação orientada a objetos**: organiza o sistema em classes e objetos.

Exemplo em Java:

```java id="ex1"
int soma = 0;
for (int i = 1; i <= 5; i++) {
    soma += i;
}
System.out.println(soma);
```

Neste exemplo, o código descreve passo a passo como a soma é realizada.

## Paradigma Declarativo

O paradigma declarativo é focado na **lógica do problema e no resultado desejado**, e não no processo detalhado. Nesse modelo, o foco está no **“o que fazer”**, e não no “como fazer”.

Utilizando o mesmo exemplo do chá, no paradigma declarativo seria algo como:

* Quero uma xícara de chá pronta

Ou seja, o objetivo é definido, mas não é necessário explicar todas as etapas para chegar até ele.

As principais abordagens desse paradigma são:

* **Programação funcional**
* **Programação lógica**

Exemplo em Prolog:

```prolog id="ex2"
soma(0, 0).
soma(N, Resultado) :-
    N > 0,
    N1 is N - 1,
    soma(N1, R1),
    Resultado is R1 + N.
```

Nesse caso, o código define regras, e o sistema é responsável por encontrar a solução.

## Comparação entre os Paradigmas

A principal diferença entre os paradigmas está na forma de abordagem:

* O **imperativo** descreve detalhadamente cada etapa da execução.
* O **declarativo** define apenas o resultado desejado.

Enquanto o paradigma imperativo foca no controle do fluxo, o declarativo foca na lógica e na abstração.

## Conclusão

Os paradigmas imperativo e declarativo oferecem formas distintas de resolver problemas. O imperativo dá maior controle ao programador, enquanto o declarativo permite maior simplicidade ao focar apenas no objetivo final.

Compreender essas diferenças é essencial para o desenvolvimento de sistemas mais eficientes e bem estruturados.
