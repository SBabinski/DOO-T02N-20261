# Paradigmas de Programação: Imperativo e Declarativo

## Introdução

A forma como expressamos soluções computacionais não é única — ela varia conforme o **paradigma de programação** adotado. Um paradigma, neste contexto, pode ser entendido como um conjunto de princípios, ou padrões a serem seguidos. Dentre os paradigmas existentes, dois se destacam pela sua abrangência histórica e pela influência que exercem sobre as linguagens modernas: o **paradigma imperativo** e o **paradigma declarativo**.

---

## Paradigma Imperativo

O paradigma imperativo é o mais antigo e, possivelmente, o mais difundido. Nele, o programador descreve como o computador deve executar uma tarefa, por meio de uma sequência explícita de instruções que alteram o estado do programa ao longo do tempo. A execução segue um fluxo de controle bem definido: atribuições de variáveis, estruturas de repetição, condicionais e chamadas de procedimentos.

Linguagens como **C**, **Java**, **Python** e **Pascal** são exemplos clássicos desse paradigma. O modelo mental subjacente é próximo ao funcionamento físico de um processador: registradores, memória e instruções executadas uma a uma.

Uma característica central desse paradigma é a mutabilidade de estado — variáveis têm valores que podem ser alterados ao longo da execução, e o resultado de um programa depende diretamente da ordem em que as operações são realizadas.

---

## Paradigma Declarativo

O paradigma declarativo inverte a perspectiva: em vez de descrever *como* resolver um problema, o programador especifica **o que** deseja obter. O mecanismo de execução fica a cargo do próprio sistema da linguagem, que infere os passos necessários para satisfazer as condições estabelecidas.

Dentro desse paradigma existem subdivisões importantes:

- **Funcional** (Haskell, Erlang, Elixir): baseado em funções matemáticas e ausência de efeitos colaterais.
- **Lógico** (Prolog): baseado em fatos, regras e consultas dentro de um sistema de lógica de predicados.
- **Reativo/Baseado em consultas** (SQL): o programador descreve *o que* quer recuperar, não *como* percorrer os dados.

A **imutabilidade** e a ausência de estado compartilhado são marcas registradas desse paradigma, o que tende a facilitar o raciocínio sobre o comportamento do programa e a verificação de sua corretude.

---

## Comparação Prática: Java vs. Prolog

Para ilustrar concretamente a diferença entre os dois paradigmas, considere o problema clássico de **calcular o fatorial de um número inteiro não-negativo**.

### Solução em Java (Paradigma Imperativo)

```java
public class Fatorial {

    public static int fatorial(int n) {
        int resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado = resultado * i;
        }
        return resultado;
    }

    public static void main(String[] args) {
        System.out.println(fatorial(5)); // Saída: 120
    }
}
```

Neste trecho, o programador descreve **passo a passo** como o cálculo deve ocorrer:

1. Uma variável `resultado` é inicializada com `1`.
2. Um laço `for` percorre os inteiros de `2` até `n`.
3. A cada iteração, `resultado` é **sobrescrito** com o produto acumulado.
4. Ao término do laço, o valor acumulado é retornado.

O estado interno da função muda a cada iteração — `resultado` e `i` assumem novos valores em cada ciclo. O controle do fluxo é explícito e inteiramente responsabilidade do programador. A linguagem executa exatamente o que foi ordenado, sem inferência ou dedução.

---

### Solução em Prolog (Paradigma Declarativo — Lógico)

```prolog
fatorial(0, 1) :- !.
fatorial(N, F) :-
    N > 0,
    N1 is N - 1,
    fatorial(N1, F1),
    F is N * F1.
```

Para calcular o fatorial de 5, realiza-se a consulta:

```prolog
?- fatorial(5, X).
X = 120.
```

Aqui, o código expressa **o que é verdade** sobre o fatorial, e não como calculá-lo mecanicamente:

- A primeira cláusula declara que o fatorial de `0` é `1` (caso base). O operador de corte `!` impede que o motor de inferência tente outras cláusulas após essa unificação ser satisfeita.
- A segunda cláusula declara que, para qualquer `N > 0`, o fatorial de `N` é `N` multiplicado pelo fatorial de `N - 1`.

O motor de inferência do Prolog — baseado em **resolução e backtracking** — é responsável por determinar automaticamente a sequência de passos que satisfaz as condições declaradas. O programador não especificou laços, variáveis mutáveis ou ordem de execução: apenas enunciou relações lógicas entre os dados.

---

## Considerações Finais

A escolha do paradigma não é trivial e tem impacto direto na **expressividade**, **manutenibilidade** e **complexidade conceitual** de um software. O paradigma imperativo oferece controle granular e proximidade com o hardware, sendo altamente adequado para sistemas onde a performance e o gerenciamento explícito de recursos são prioritários. O paradigma declarativo, por sua vez, eleva o nível de abstração, permitindo que o programador se concentre na especificação do problema em vez de nos detalhes de sua solução.

Compreender ambos os paradigmas — e reconhecer em quais cenários cada um brilha — é uma habilidade fundamental para qualquer profissional de Ciência da Computação ou Engenharia de Software. As linguagens modernas, inclusive, tendem a ser **multiparadigma**, incorporando elementos de ambas as abordagens para maximizar a expressividade do programador.

---
