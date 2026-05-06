# Paradigmas de Programação: Imperativo e Declarativo

## Introdução

Os paradigmas de programação representam diferentes formas de estruturar e desenvolver soluções computacionais. Entre os principais paradigmas estudados, destacam-se o **imperativo** e o **declarativo**, que se diferenciam principalmente pela maneira como instruem o computador a resolver um problema.

## Paradigma Imperativo

O paradigma imperativo é baseado na execução sequencial de comandos. Nesse modelo, o programador descreve detalhadamente **como o problema deve ser resolvido**, especificando cada passo necessário para alcançar o resultado.

Esse paradigma utiliza conceitos como:

* variáveis
* estruturas de controle (if, for, while)
* atribuições de valores

Linguagens como Java seguem esse modelo, onde o fluxo de execução é explicitamente definido. O programa é executado linha por linha, e cada instrução altera o estado do sistema.

## Paradigma Declarativo

O paradigma declarativo, por outro lado, foca no **resultado desejado**, e não no processo para alcançá-lo. Nesse modelo, o programador descreve **o que deve ser feito**, deixando para o sistema a responsabilidade de determinar como executar.

Esse paradigma é comum em linguagens como Prolog, onde o foco está em regras e fatos. O programador define relações lógicas, e o sistema resolve automaticamente as consultas com base nessas definições.

## Comparação entre Java e Prolog

### Exemplo em Java (Imperativo)

```java
public class Soma {
    public static int somar(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        int resultado = somar(5, 3);
        System.out.println(resultado);
    }
}

Neste exemplo, o Java define explicitamente:

* como a soma será realizada (`a + b`)
* quando ela será chamada
* como o resultado será exibido

O fluxo é controlado passo a passo pelo programador.


### Exemplo em Prolog (Declarativo)

```prolog
soma(A, B, Resultado) :- Resultado is A + B.
```

Consulta:

```prolog
?- soma(5, 3, R).
```

Neste caso, o Prolog define apenas a **regra lógica** da soma. O sistema é responsável por:

* interpretar a regra
* executar o cálculo
* retornar o resultado

O programador não controla o fluxo, apenas descreve a relação entre os valores.

## Análise Comparativa

A principal diferença entre os paradigmas está na forma de abordagem:

* No paradigma imperativo, o foco está no **controle do fluxo e na sequência de instruções**.
* No paradigma declarativo, o foco está na **descrição do problema e nas relações lógicas**.

Enquanto Java exige que o programador detalhe cada passo da execução, Prolog permite uma abordagem mais abstrata, delegando ao sistema a responsabilidade pela resolução.

## Conclusão

Ambos os paradigmas possuem vantagens e aplicações específicas. O paradigma imperativo oferece maior controle sobre a execução, sendo amplamente utilizado no desenvolvimento de sistemas tradicionais. Já o paradigma declarativo proporciona maior nível de abstração, sendo útil em problemas baseados em lógica e inferência.

A compreensão dessas diferenças é fundamental para escolher a abordagem mais adequada conforme o contexto do problema.

