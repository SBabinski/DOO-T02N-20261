# Paradigmas: Imperativo e declativo.
## Imperativo:
O paradigma imperativo caracteriza-se pelo foco na descrição detalhada do fluxo de execução. Nele, o desenvolvedor instrui o computador sobre como realizar uma tarefa, especificando uma sequência exata de comandos e estados que alteram os dados para atingir o resultado desejado.Esse e o estilo da programacao imperativa, onde o foco esta em  detalhar cada passo para  o computador: dizendo o que fazer, na ordem em que deve ser feito.

### Exemplo em Java:
```java
int numero = -5;
if (num > 0){
    System.out.println("Positivo");
} else {
    System.out.printl("Negativo");    
}
```
## Declativo:
O paradigma declarativo concentra-se na definição do resultado pretendido em vez do fluxo de execução necessário para alcançá-lo. Diferente do estilo imperativo, o desenvolvedor descreve as propriedades e as relações lógicas do problema, permitindo que a infraestrutura subjacente (como um motor de busca ou compilador) determine a melhor estratégia de execução.

### Exemplo em Prolog:
```prolog
positivo(n) :- n > 0.
negativo (n) :- n < 0.
```

## Conclusão
Paradigma Imperativo: É o estilo de programação onde o foco está em detalhar cada passo para o computador: dizendo exatamente como fazer e a ordem em que as instruções devem ser executadas.

Paradigma Declarativo: É o estilo de programação que foca em descrever o que deve ser alcançado. Em vez de uma sequência de instruções, o programador declara as propriedades e relações que o resultado final deve ter.