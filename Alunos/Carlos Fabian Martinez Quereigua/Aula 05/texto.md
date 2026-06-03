# O que é um paradigma ?
É um modelo padrão ou exemplo a ser seguido, também podemos definir como com maneira existente de se pensar.

## Tipos de paradigmas

### Paradigma imperativo
#### Caraterísticas:
- Programação estruturada 
- Programação procedural
- Programação orientada a objetos
			
### Paradigma declarativo

#### Caraterísticas
- programação funcional
- programação logica

## Programação orientada a objetos
Surgiu como método para modelar o mundo, através de objetos, com propriedades e métodos.
Permite encapsulamento de dados e a reutilização de código.
	
### Pilares
- Herança
- Polimorfismo
- Encapsulamento
- Abstração
### Benefícios
- Reutilização de código
- Modularidade
- Flexibilidade e escalabilidade
- Organização e coesão de código

## Java
Linguagem de programação multiparadigma que segue principalmente o modelo orientado a objetos, permitindo criar aplicações estruturadas, portáveis e seguras, com suporte a concorrência e recursos funcionais a partir do Java 8.

### Caraterísticas
- Multiparadigma
- Procedural
- Estruturado
- POO
- Programação concorrente
- Funcional (a partir do java 8)
	
### Código em java

```java
public static void main(string []args){
	boolean sinal = true;
	if(sinal){
		System.out.println("Hello World!");
	}
	else{
		System.out.println("Bye world!");
	}
}
```
### Explicação do código
Neste código em java, temos um método main onde existe a instanciação da variavel "sinal" com valor "true", logo após temos um "if", onde verificamos se o "sinal" é igual a "true", sendo este positivo teremos a impressão de um "Hello world!", caso contrario teremos a impressão de um "Bye world!".

## Prolog
Prolog funciona com base em lógica declarativa, onde você define fatos e regras que descrevem um problema, e o interpretador tenta provar se algo é verdadeiro a partir dessas definições usando um processo chamado unificação e backtracking, testando diferentes possibilidades automaticamente até encontrar uma solução ou concluir que não existe.

### Caraterísticas
- Paradigma declarativo
- Baseado em lógica (lógica de predicados)
- Uso de fatos e regras
- Inferência automática
- Não orientado a objetos

### Código em Prolog
```prolog 
sinal(true).

main :-
    ( sinal(true) ->
        write('Hello World!')
    ;
        write('Bye world!')
    ),
    nl.
```
### Explicação do código em Prolog
Esse código em Prolog define um fato sinal(true) que funciona como uma variável booleana e uma regra main que simula um if/else: se sinal(true) for verdadeiro, imprime "Hello World!", caso contrário imprime "Bye world!".

## Diferencias entre Java e Prolog
Java e Prolog diferem principalmente no paradigma: Java é imperativo e orientado a objetos, onde você controla o fluxo passo a passo com estruturas como if/else, enquanto Prolog é declarativo e baseado em lógica, onde você define fatos e regras e o próprio motor decide como chegar ao resultado; assim, em Java você diz como fazer, e em Prolog você descreve o que é verdadeiro.