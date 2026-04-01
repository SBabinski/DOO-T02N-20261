# Aula 05 - **Texto sobre paradigmas**

## Paradigma **Imperativo** e **Declarativo**
- *Imperativo*:
  - **programação imperativa** descreve a computação como ações, enunciados ou comandos que mudam o estado (variáveis) de um programa. Semelhante ao comportamento imperativo das linguagens naturais que expressam ordens, programas imperativos são uma sequência de comandos para o computador executar.
  - Exemplos **Imperativas**:
    - **Programação estruturada**:
      - *Programação estruturada* é uma forma de programação de computadores que preconiza que todos os programas possíveis podem ser reduzidos a apenas três estruturas: sequência, decisão e iteração, desenvolvida por Michael A. Jackson no seu livro "Principles of Program Design" de 1975.  
      Tendo, na prática, sido transformada na programação modular, a Programação estruturada orienta os programadores para a criação de estruturas simples em seus programas, usando as subrotinas e as funções. Foi a forma dominante na criação de software anterior à programação orientada por objetos.
    - **Programação procedural**:
      - *Programação procedural* ou programação procedimental é um paradigma de programação que se baseia em procedimentos, que são executados numa sequência. Ela é derivada da programação imperativa, mas acrescentando procedimentos (em inglês, procedure calls), de forma a organizar o código em blocos que possam ser reutilizados.
    - **Programação orientada a objetos**:
      - *Programação orientada a objetos* (POO, ou OOP segundo as suas siglas em inglês) é um paradigma de programação baseado no conceito de "objetos", que podem conter dados na forma de campos, também conhecidos como atributos, e códigos, na forma de procedimentos, também conhecidos como métodos. Uma característica de objetos é que um procedimento de objeto pode acessar, e geralmente modificar, os campos de dados do objeto com o qual eles estão associados (objetos possuem uma noção de "this" (este) ou "self" (próprio)).  
      Em POO, programas de computadores são projetados por meio da composição de objetos que interagem com outros. Há uma diversidade significante de linguagens de POO, mas as mais populares são aquelas baseadas em classes, significando que objetos são instâncias de classes, que, normalmente, também determinam seu tipo. 
- *Declarativo*:
  - *Programação declarativa* é um paradigma de programação baseado em **programação funcional**, **programação lógica** e **programação restritiva**. Tal termo é utilizado para discernir tais linguagens em relação à linguagens de programação imperativa. Esta programação dita que o programador deve modelar um dado problema através de assertivas acerca dos objetos do universo de discurso, ou seja, definindo o relacionamento lógico existente entre a cabeça e o corpo da cláusula, e nunca descrevendo como seus procedimentos funcionam. As soluções declarativas são usualmente mais fáceis de desenvolver e possuem a clareza e limpidez da pura lógica.
  - exemplos **Declarativo**:
    - **Programação funcional**:
      - *Programação funcional* é um padrão de programação que trata a computação como uma avaliação de funções matemáticas, evitando estados ou dados mutáveis. Ela enfatiza a aplicação de funções, em contraste da programação imperativa (que enfatiza mudanças no estado do programa). Enfatizando as expressões ao invés de comandos, as expressões são utilizados para cálculo de valores com dados imutáveis.
    - **Programação lógica**:
      - O sentido da *programação lógica* é trazer o estilo da lógica matemática à programação de computadores. Matemáticos e filósofos encontram na lógica uma ferramenta eficaz para desenvolvimento de teorias. Vários problemas são naturalmente expressos como teorias. Dizer que um problema precisa de solução frequentemente equivale a perguntar se uma nova hipótese é consistente com uma teoria existente ou se é consequência dela. A lógica proporciona uma maneira de demonstrar se uma questão é verdadeira ou falsa.
## Java vs Prolog:
vou desenvolver um algoritimo em **Java** e em **Prolog** que printa no terminal os numeros pares de 1 ate 10  

```Java:```
```java
public static void main(String[], args) {
    for(int i = 1;i<=10;i++){
        if(i%2==0){
            system.out.println(i+" e par")
        }
    }
}
```
O código Java é **imperativo**: você diz como fazer, passo a passo.

**for(int i = 1; i <= 10; i++)**  cria uma variável i e a incrementa a cada iteração, de 1 até 10  
A cada volta do loop, **if(i % 2 == 0)** testa se o resto da divisão por 2 é zero  
**Se for verdade**, imprime o número

O programa controla o fluxo explicitamente: inicializa, testa, executa, incrementa, repete.

```Prolog:```
```prolog
main:-
        process,
        halt.

process:-
    forall(
        (between(1, 10, I), I mod 2 =:= 0),
        (write(I), write(' e par'), nl)
    ).
:- main.
```
O Prolog é **declarativo**: você descreve o que é verdade, e o motor do Prolog descobre como chegar lá.

**between(1, 10, I)**  não é um loop, é um gerador. O Prolog faz backtracking, tentando todos os valores possíveis de I entre 1 e 10  
**I mod 2 =:= 0** é uma restrição. O Prolog só continua se essa condição for satisfeita para o I atual  
**forall** garante que a ação de escrita seja executada para cada combinação que satisfaça as condições  

Não há variável sendo incrementada, nem loop explícito o Prolog simplesmente tenta, falha, recua e tenta de novo até esgotar as possibilidades.

