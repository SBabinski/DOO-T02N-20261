# Paradigmas de Programação: Análise Comparativa entre Imperativo e Declarativo

## 1. Contextualização Teórica

Os paradigmas de programação representam filosofias distintas sobre como estruturar soluções computacionais. Eles definem não apenas a sintaxe da linguagem, mas principalmente o modelo mental que o programador adota para resolver problemas.

### 1.1 Paradigma Imperativo
O paradigma imperativo é centrado na execução sequencial de comandos. O programador especifica como o resultado deve ser obtido, utilizando estado mutável das variáveis, controle explícito de fluxo através de estruturas como loops e condicionais, e manipulação direta da memória. Suas principais características incluem programação baseada no modelo von Neumann, variáveis mutáveis, sequência rígida de execução e foco na máquina.

### 1.2 Paradigma Declarativo
O paradigma declarativo inverte essa lógica, focando no "o que" ao invés do "como". O programador define relações entre dados, regras lógicas ou propriedades, e o resultado desejado, deixando para o sistema decidir a implementação. Caracteriza-se por abstração da execução, estado imutável ou relacional, inferência automática e foco no problema em alto nível.

## 2. Estudo de Caso: Geração de Números Pares

Para análise prática, implementamos a tarefa "encontrar os 5 primeiros números pares menores ou iguais a 20" em ambas as abordagens.

### 2.1 Implementação Imperativa (Java)

```java
import java.util.ArrayList;
import java.util.List;

public class ParesImperativo {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        int limite = 20;
        int quantidade = 5;
        
        int contador = 1;
        
        while (numeros.size() < quantidade && contador <= limite) {
            if (contador % 2 == 0) {
                numeros.add(contador);
            }
            contador++;
        }
        
        System.out.printf("Resultado: %s%n", numeros);
        System.out.printf("Tamanho: %d elementos%n", numeros.size());
    }
}
```

**Análise da execução:**
A execução segue uma sequência determinística: inicializa contador em 1, itera até encontrar 5 números pares ou atingir o limite de 20, verificando a paridade de cada número através do operador módulo, adicionando os pares à lista e incrementando manualmente o contador até satisfazer as condições de parada.

**Saída esperada:**


### 2.2 Implementação Declarativa (Prolog)

```prolog
% Base de conhecimento: Números naturais até 20
dominio(1). dominio(2). dominio(3). dominio(4). dominio(5).
dominio(6). dominio(7). dominio(8). dominio(9). dominio(10).
dominio(11). dominio(12). dominio(13). dominio(14). dominio(15).
dominio(16). dominio(17). dominio(18). dominio(19). dominio(20).

% Predicado: Número par
par(X) :- 
    dominio(X), 
    0 is X mod 2.

% Predicado: Sequência ordenada de 5 pares
cinco_pares_ordenados(P1, P2, P3, P4, P5) :-
    par(P1), par(P2), par(P3), par(P4), par(P5),
    P1 < P2, P2 < P3, P3 < P4, P4 < P5.
```

**Consulta e resultado:**


**Mecanismo de inferência:** O Prolog utiliza backtracking automático, testando sistematicamente valores do domínio que satisfazem a regra par(X) e as restrições de ordenação, unificando variáveis até encontrar uma solução completa.

## 3. Análise Comparativa Sistemática

| Critério           | Imperativo (Java)                    | Declarativo (Prolog)                  |
|--------------------|--------------------------------------|---------------------------------------|
| Modelo Mental      | Passo a passo sequencial            | Relações e propriedades              |
| Controle de Fluxo  | Explícito (while, if)               | Automático (backtracking)            |
| Estado             | Mutável (contador++)                | Imutável (unificação)                |
| Abstração          | Baixa (próximo da máquina)          | Alta (próximo do problema)           |
| Legibilidade       | Linear, intuitiva                   | Lógica, requer conhecimento específico |
| Performance        | Otimizada para sequência            | Otimizada para busca lógica          |
| Debugging          | Breakpoints lineares                | Rastreamento de unificações          |
| Reutilização       | Métodos específicos                 | Predicados composicionais            |

## 4. Discussão Crítica

### 4.1 Vantagens do Paradigma Imperativo
O imperativo oferece controle total sobre performance, debugging determinístico através de breakpoints lineares, familiaridade para engenheiros de software e ampla portabilidade através de linguagens como Java.

### 4.2 Vantagens do Paradigma Declarativo
O declarativo permite que especificação e implementação coincidam, expressa naturalmente problemas lógicos e relacionais, facilita composição de regras e suporta paralelização implícita através do motor de inferência.

### 4.3 Limitações de Cada Abordagem
O imperativo sofre com "explosão de estado" em problemas complexos de relacionamento. O declarativo apresenta ineficiência em problemas essencialmente sequenciais ou numéricos intensivos.

## 5. Considerações Finais

Esta análise demonstra que não existe paradigma universalmente superior, mas sim adequação específica ao problema. O imperativo é preferível para algoritmos numéricos e sistemas em tempo real, enquanto o declarativo destaca-se em sistemas especialistas, bancos de dados relacionais e inteligência artificial simbólica. A programação moderna frequentemente adota abordagens híbridas, combinando expressividade declarativa com performance imperativa, como visto em Java Streams, SQL e frameworks reativos.