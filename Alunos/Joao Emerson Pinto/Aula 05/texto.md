# Aula 05 - Paradigmas de Programação

## Paradigma Imperativo e Declarativo

### Imperativo

A programação imperativa é baseada na execução de comandos que alteram o estado do programa ao longo do tempo. Nesse paradigma, o código é escrito como uma sequência de instruções que o computador deve seguir para chegar a um resultado.

É semelhante à forma como damos ordens no dia a dia, onde dizemos exatamente o que deve ser feito. O programa executa cada passo conforme foi definido pelo desenvolvedor.

#### Exemplos Imperativos

**Programação estruturada:**

A programação estruturada é baseada em três estruturas principais: sequência, decisão e repetição. Seu objetivo é organizar o código de forma mais clara e compreensível, facilitando manutenção e entendimento.

**Programação procedural:**

A programação procedural organiza o código em procedimentos (funções), permitindo reutilização e melhor divisão das tarefas dentro do sistema.

**Programação orientada a objetos:**

A programação orientada a objetos (POO) é baseada no conceito de objetos, que possuem atributos (dados) e métodos (funções). Os programas são construídos através da interação entre esses objetos, tornando o código mais modular e reutilizável.

---

### Declarativo

A programação declarativa segue uma abordagem diferente. Ao invés de dizer como o problema deve ser resolvido, o programador descreve o que precisa ser feito, deixando para a linguagem encontrar a solução.

Esse paradigma é mais abstrato e geralmente mais próximo da lógica do problema, tornando o código mais limpo e direto.

#### Exemplos Declarativos

**Programação funcional:**

A programação funcional trata a computação como avaliação de funções matemáticas, evitando alterações de estado e utilizando dados imutáveis.

**Programação lógica:**

A programação lógica utiliza regras e fatos para resolver problemas. O sistema analisa essas regras e encontra soluções através de inferência lógica.

---

### Java vs Prolog

Para exemplificar a diferença entre os paradigmas, será apresentado um algoritmo que imprime os números pares de 1 até 10.

#### Java (Imperativo)

```java
public static void main(String[] args) {
    for(int i = 1; i <= 10; i++){
        if(i % 2 == 0){
            System.out.println(i + " é par");
        }
    }
}

O código em Java segue o paradigma imperativo, pois define exatamente como o problema deve ser resolvido.

A variável i é criada e controlada manualmente
O laço for percorre os números de 1 até 10
A condição if verifica se o número é par
O resultado é impresso no terminal

Ou seja, todo o fluxo do programa é controlado diretamente pelo desenvolvedor.

Prolog (Declarativo)
main :-
    process,
    halt.

process :-
    forall(
        (between(1, 10, I), I mod 2 =:= 0),
        (write(I), write(' é par'), nl)
    ).

:- main.

No Prolog, a abordagem é declarativa, ou seja, o foco está em definir regras e condições.

between(1, 10, I) gera valores possíveis
I mod 2 =:= 0 define a condição de número par
forall executa a ação para todos os casos válidos

Não há controle explícito de repetição. O Prolog utiliza um mecanismo chamado backtracking, onde ele testa possibilidades até encontrar todas as soluções válidas.