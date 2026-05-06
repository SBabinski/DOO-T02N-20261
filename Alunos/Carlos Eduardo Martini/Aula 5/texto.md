
# Paradigmas de Programação

  

Os paradigmas de programação podem ser compreendidos como um conjunto de diretrizes que estabelecem a organização do código, visando não apenas a funcionalidade, mas também a estética e a legibilidade do projeto. Cada paradigma possui um estilo próprio de escrita e uma lógica de estruturação específica, que orienta o desenvolvedor sobre como resolver problemas computacionais.

  

## 1. Paradigma Imperativo

Este modelo opera de forma sequencial, assemelhando-se a um caminho em linha reta onde o programador descreve exatamente o passo a passo que deve ser executado até a conclusão da tarefa.

  

**Programação Estruturada:** Foca na otimização através de blocos de fluxo e estruturas de controle, como tomadas de decisão (`if/else`) e laços de repetição (`while`, `do`, `for`).

  

**Programação Procedural:** Organiza o código como uma lista de instruções detalhadas. Exemplos clássicos incluem as linguagens **C, C++, Java e Pascal**.

  

### Exemplo em Java (Imperativo/OO)

No Java, definimos variáveis e comandos explícitos para o computador seguir:

  

```java

int idade =  20;

if  (idade >=  18)  {

System.out.println("Acesso permitido.");

}

```

  

## 2. Paradigma Declarativo

Propõe uma perspectiva diferente, focando no resultado final desejado. Nesta abordagem, a prioridade é descrever **o que** deve ser obtido, em vez de detalhar minuciosamente **como** o processo deve ser realizado.

  

### Exemplo em Prolog (Lógico/Declarativo)

No Prolog, declaramos fatos e regras, e o sistema deduz o resultado:

  

```Prolog

idade(joao, 20).

pode_entrar(Pessoa) :- idade(Pessoa, Valor), Valor >= 18.
