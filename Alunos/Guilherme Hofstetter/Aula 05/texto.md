# Paradigmas de Programação
Um paradigma de programação é a abordagem lógica e conceitual utilizada para estruturar e resolver problemas através de código.

## Paradigma Imperativo
Foca no como resolver o problema. O código descreve o passo a passo exato, alterando o estado do programa através de variáveis e controle de fluxo.
- Abordagem Estruturada:
 - Uso de sequências, laços de repetição e condições lógicas lineares.
- Abordagem Procedural:
 - Agrupamento do código em rotinas ou funções reaproveitáveis.
---
## Paradigma Declarativo
Foca no o que deve ser resolvido. O código descreve as regras e condições do problema, e a linguagem decide como executar o controle de fluxo.
- Abordagem Funcional:
 - Baseada na avaliação de funções puras matemáticas.
- Abordagem Lógica:
 - Baseada em declarações de fatos e regras formais.
---
# POO (programação orientada a objetos)
É um modelo do paradigma imperativo que organiza o software em "objetos", entidades que agrupam dados (atributos) e comportamentos (métodos).

## Pilares:
- Abstração: Representar apenas as características essenciais do objeto, ignorando detalhes desnecessários.
- Encapsulamento: Ocultar e proteger os dados internos do objeto contra acessos externos indevidos.
- Herança: Capacidade de criar novas classes aproveitando os atributos e métodos de classes já existentes.
- Polimorfismo: Capacidade de um mesmo método agir de maneiras diferentes dependendo do objeto.

# Comparação prática Java vs Prolog
## Código em Java:
```public class Verificador {
    public static void main(String[] args) {
        int numero = 5;
        if (numero > 0) {
            System.out.println("O número é positivo.");
        }
    }
}
```
### Explicação:
O código imperativo dita o fluxo exato. Declaramos a variável número na memória e escrevemos a estrutura de controle if para avaliar ativamente o estado da variável e instruir a impressão no console.

## Código em Prolog
```% Regra lógica
positivo(X) :- X > 0.

% Consulta no terminal: ?- positivo(5).
% Saída gerada: true.
```
### Explicação:
Em Prolog, não ditamos os passos. Apenas declaramos uma regra: "algo é positivo se for maior que zero". O usuário faz a consulta (positivo(5)) e a própria linguagem avalia se aquela premissa é verdadeira ou falsa baseada na regra existente.




