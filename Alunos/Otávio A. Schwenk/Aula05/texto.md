# **Paradigmas Imperativo e Declarativo: Diferenças**
## Um pequeno resumo sobre como dois códigos executam uma mesma tarefa.
### Principais tópicos:
- Diferenças entre linguagem Imperativa e Declarativa
    -  Exemplo visual
- Diferenças no funcionamento
- Conclusão
---
# Diferenças entre linguagem Imperativa e Declarativa
## Imperativa:
As linguagens imperativas são aquelas onde os comandos são escritos em forma de ordem, como um imperador, literalmente, daí o nome. Nesse tipo de linguagem, tudo que o compilador deve fazer é passado como uma ordem direta, como Mostre (_print_), Faça (_do_), Repita (_for_), entre outros comandos. O compilador interpreta a ordem e executa exatamente o que foi passado a ele. Todo o funcionamento desta linguagem é focado nos detalhes de **COMO** deve ser feito, descrevendo cada passo e cada ação que deve ser realizada a fim de obter o resultado desejado.  
O programa começa em um estado inicial, onde variáveis são criadas e recebem valores, e a partir daí cada instrução modifica esse estado. Essas variáveis representam espaços na memória, e seus valores podem ser alterados ao longo da execução.  
Também é possível realizar ações sem trabalhar diretamente com o uso de variáveis, como abaixo, onde um texto é mostrado na tela usando um laço de repetição simples.

---
Abaixo segue um exemplo de laço de repetição que mostra cinco vezes a frase "Hello World" em Java:

```JAVA```
```Java
public class Main {
    public static void main(String[] args){
        for(int i = 0; i<5; i++){
            System.out.println("Hello World");
        }
    }
}
```
---
### Análise:
- ```public class Main {}```

Primeiro o programa define a classe "Main", que é obrigatória em todos os programas do Java, pois tudo deve rodar dentro de uma classe. Essa classe é publica, que indica que ela pode ser chamada em qualquer lugar do porgrama.

- ```public static void main(String[] args){}```

Em seguida é chamado esse método, que é o que a JVM deve executar primeiro, iniciando o porgrama.

- ```for(int i = 0; i<5; i++){}```

Depois é iniciado o _for_, que irá repetir o que for desejado quantas vezes forem requeridas, nesse caso, foram requeridas cinco vezes.

- ```System.out.println("Hello World");```

E por último o código mostra "Hello World"", e, como ele está dentro de um laço de repetição, este texto será repetido 5 vezes, conforme indica o laço.

## Declarativa
As linguagens declarativas são aquelas em que o programador descreve **O QUE** deseja que seja feito, sem especificar passo a passo como isso deve acontecer.  
Nesse tipo de linguagem, o foco está em definir regras, relações ou propriedades que descrevem o resultado desejado, e não em controlar o fluxo da execução diretamente. O interpretador ou compilador é responsável por descobrir como satisfazer essas regras e gerar o resultado. Todo o funcionamento desta linguagem é voltado para a lógica do problema, estabelecendo condições e objetivos que devem ser cumpridos.  
O programa não precisa se preocupar com o estado interno das variáveis ou com a ordem exata das operações, mas sim em declarar fatos e regras que descrevem o que é verdadeiro ou desejado. É comum, por exemplo, definir relações ou padrões e deixar que o sistema resolva a melhor forma de chegar à solução, como demonstrado em programas que repetem uma ação recursivamente até atingir um critério, sem a necessidade de laços ou instruções explícitas de controle.

---
Abaixo segue um exemplo de laço de repetição que mostra cinco vezes a frase "Hello World" em Prolog:

```Prolog```
```Prolog
:- initialization(main).

main :-
    repetir(5),
    halt.

repetir(0).
repetir(N) :-
    N > 0,
    write('Hello World'), nl,
    N1 is N - 1,
    repetir(N1).
```
---
### Análise:
- ```:- initialization(main).```

O programa começa com essa diretiva, que indica ao Prolog que ele deve executar automaticamente o predicado main assim que o programa iniciar, sem precisar digitar nada no console.

- ```main :- repetir(5), halt.```

Em seguida, o predicado main é definido. Ele chama o predicado repetir(5), que é responsável por imprimir "Hello World" cinco vezes, e depois executa halt, que encerra o programa.

- ```repetir(0).```

Este é o caso base da recursão. Ele define que quando o valor chegar a 0, o predicado repetir termina, parando a recursão.

- ```repetir(N) :- N > 0, write('Hello World'), nl, N1 is N - 1, repetir(N1).```

Aqui está o caso recursivo. Primeiro, verifica se N é maior que 0. Se for, imprime "Hello World" e pula uma linha (nl). Depois calcula N1 como N - 1 e chama repetir(N1) novamente, repetindo o processo até chegar ao caso base.

O resultado é que o programa imprime "Hello World" cinco vezes, uma por linha, e então termina automaticamente.

---
# Diferenças no funcionamento
Os dois códigos realizam a mesma tarefa de imprimir "Hello World" cinco vezes, mas funcionam de maneiras bem diferentes por causa do estilo da linguagem em que foram escritos.  
No código Java, que é imperativo, o programa descreve passo a passo como o resultado deve ser obtido, criando uma variável de controle, testando uma condição e repetindo ações em um laço específico, alterando o estado da variável a cada passo. Cada instrução depende da execução anterior, e a ordem é fundamental para que o resultado seja correto.  
Já no código Prolog, que é declarativo, o programa define regras e condições que descrevem o resultado desejado, usando recursão para repetir a ação até atingir o caso base, sem precisar controlar explicitamente o fluxo ou o estado das variáveis. O Prolog se concentra em expressar a lógica do problema, permitindo que o interpretador resolva automaticamente como satisfazer essas regras.  
Enquanto o imperativo foca no como fazer, especificando todos os passos e mudanças de estado, o declarativo foca no o que deve ser feito, descrevendo relações e resultados, deixando para o sistema decidir a melhor forma de executar.

---
# Conclusão
Ambas as linguagens, e tipos de linguagens, funcionam de maneiras adversas, diferentes, mas são capazes de exercer a mesma tarefa. O objetivo e o foco do uso de cada uma é complementar, com uma se fortalecendo na "fraqueza" da outra, tornando o mundo da programação mais diverso e abrindo novas portas e possibilidades. Enquanto as linguagens imperativas oferecem controle preciso sobre cada passo da execução e são ideais para algoritmos detalhados e manipulação direta de estados, as linguagens declarativas permitem expressar a lógica e as relações do problema de forma mais abstrata, simplificando a resolução de tarefas complexas. Juntas, elas fornecem ferramentas variadas para diferentes tipos de problemas, permitindo que programadores escolham a abordagem mais adequada, combinando clareza, eficiência e flexibilidade na construção de soluções.