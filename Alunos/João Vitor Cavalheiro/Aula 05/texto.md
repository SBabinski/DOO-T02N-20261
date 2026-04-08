# Imperativo

O paradigma imperativo é um dos modelos mais tradicionais de programação, baseado na ideia de descrever passo a passo as instruções que o computador deve executar para resolver um problema. Nesse paradigma, o foco está em como a solução será construída, por meio de comando sequenciais que manipulam dados ao longo da execução. Assim, o programa segue uma lógica bem definida, onde cada instrução altera o estado do sistema até que o resultado desejado seja alcançado.

## Programação Estrutural

Com o tempo, surgiu a necessidade de tornar esses programas mais organizados e compreensíveis, dando origem à programação estruturada. Essas abordagem mantém os princípios do paradigma imperativo porém introduz uma forma mais organizada de desenvolver o código, evitando práticas que dificultavam a leitura, como o uso excessivo de desvio incondicionais, como o ***goto***, que permitia saltos arbitrários no código e tornavam o fluxo de execução difícil de acompanhar.

>goto: é um comando de controle de fluxo de baixo nível, presente em diversas linguagens de programação, que instrui o computador a "ir para" outra linha ou rótulo específico do código, ignorando a ordem sequencial normal de execução.

A programação estruturada baseia-se na utilização de três estruturas fundamentais:

1. Sequência
2. Decisão
3. Repetição

A estrutura de decisão, geralmente representada pelo comando **if**, permite que o programa escolha diferentes caminhos de execução com base em uma condição. Já a estrutura de repetição com o comando **while**, possibilita que um bloco de código seja executado várias vezes enquanto uma determinada condição for verdade.

## Programação Procedural
A partir dessa evolução, surge a programação procedural, que amplia ainda mais a organização do código ao dividi-lo em partes menores chamadas procedimentos ou funções, Essa abordagem permite que tarefas específicas sejam agrupadas em blocos reutilizáveis, tornando o programa mais modular e eficiente, além de melhorar sua legibilidade.

 Outro ponto importante é que a programação procedural melhora a manutenção do sistema, pois alterações podem ser realizadas em partes específicas do código sem a necessidade de modificar o programa. Isso reduz a chance de erros e torna o processo de atualização mais rápido e seguro. Dessa forma, a reutilização de código também é favorecida, já que funções já existentes podem ser reaproveitadas em diferentes partes do sistema ou até em outros projetos, aumentando a produtividade no desenvolvimento.


## Programação Orientada a Objetos

A programação orientada a objetos é um paradigma que organiza o código com base em objetos, que representam entidades do mundo real. Esses objetos são formados por dados e comportamentos, permitindo uma estrutura mais próxima da realidade. Essa abordagem tem como principais características o encapsulamento, a herança e o polimorfismo, que contribuem para a criação de sistemas mais organizados, reutilizáveis e fáceis de manter. Além disso, a programação orientada a objetos facilita a manutenção e a evolução do software, pois permite modificar partes do sistema sem afetar diretamente outras, promovendo maior flexibilidade e reaproveitamento de código.

# Declarativo

O paradigma declarativo é um modelo de programação no qual o foco está em descrever o que deve ser feito, em vez de detalhar como a solução será executada. Nesse paradigma, o programador especifica o resultado desejado, enquanto a linguagem ou sistema se encarrega de determinar os passos necessários para alcançá-lo. Essa abordagem reduz a complexidade do código, pois evita a necessidade de controlar explicitamente o fluxo de execução, como ocorre no paradigma imperativo.

Além disso, o paradigma declarativo tende a produzir códigos mais legíveis e expressivos, facilitando o entendimento e a manutenção do sistema. Ele é amplamente utilizado em situações onde o objetivo é trabalhar com consultas, regras ou transformações de dados, permitindo que o desenvolvedor foque mais na lógica do problema do que nos detalhes de implementação.

## Programação Funcional

A programação funcional é uma abordagem dentro do paradigma declarativo que utiliza funções como base para a construção dos programas. Nessa forma de programação, as funções são tratadas como elementos principais, podendo ser reutilizadas, combinadas e até mesmo passadas como parâmetros. Isso proporciona maior flexibilidade no desenvolvimento e favorece a criação de soluções mais elegantes.

Outra característica importante é a preferência por dados imutáveis, ou seja, que não sofrem alterações após serem criados. Isso reduz efeitos colaterais e torna o comportamento do programa mais previsível. Como resultado, o código tende a ser mais seguro, fácil de testar e de manter, especialmente em sistemas mais complexos.

## Programação Lógica

A programação lógica é outra abordagem do paradigma declarativo, baseada na definição de fatos e regras para a resolução de problemas. Nesse modelo, o programador descreve as condições e relações existentes, e o sistema é responsável por encontrar as soluções possíveis a partir dessas informações, utilizando mecanismos de inferência.

Essa forma de programação é especialmente útil em aplicações que envolvem raciocínio automático, inteligência artificial e resolução de problemas complexos. Por permitir uma representação mais abstrata do problema, a programação lógica facilita o desenvolvimento de soluções que dependem mais de regras e relações do que de instruções detalhadas, tornando o código mais conciso e focado na lógica do domínio.

# Comparação Java/Prolog

Agora será realizada uma demonstração entre dois paradigmas de programação distintos. Para isso, será feita a comparação entre um código desenvolvido em Java, que segue o paradigma imperativo, e um código em Prolog, baseado no paradigma declarativo. O objetivo é evidenciar as diferenças na forma de resolver um mesmo problema, destacando as características de cada abordagem.

## Java

```
public class Soma {
    public static void main(String[] args){
        int x=8;
        int y=5;
        int z=9;
        int soma;
        if(x>5){
            soma+=x;
        }
        if(y>5){
            soma+=y;
        }
        if(z>5){
            soma+=z;
        }
        System.out.println("O resultado da soma dos números maiores que 5 é:"+soma);
    }
}
```

Acima está um código em Java, inicialmente eu defino os valores das variáveis e o código compara as  variáveis inteiras para ver se são maiores que 5 e caso sejam elas são somadas a variável soma e por fim eu imprimo o resultado na tela para mostrar ao usuário.

## ProLog

```
:- initialization(main).

valor(x, 8).
valor(y, 5).
valor(z, 9).

soma_valor(V, V) :- V > 5.
soma_valor(V, 0) :- V =< 5.

soma(Soma) :-
    valor(x, X),
    valor(y, Y),
    valor(z, Z),
    soma_valor(X, SX),
    soma_valor(Y, SY),
    soma_valor(Z, SZ),
    Soma is SX + SY + SZ.

main :-
    soma(S),
    write('O resultado da soma dos numeros maiores que 5 e: '), write(S), nl.
```

Acima está um código em Prolog define os valores das variáveis e utiliza uma regra para verificar quais são maiores que 5. Os valores que não atendem a essa condição são desconsiderados na soma. Em seguida, o programa realiza o cálculo apenas com os números válidos e exibe o resultado final na tela.

## Conclusão

Ao comparar os dois códigos, é possível observar a diferença entre os paradigmas de programação. O Java segue o paradigma imperativo, no qual o programador define passo a passo como a solução deve ser executada, utilizando estruturas como o if e controle direto das variáveis.

Já o Prolog utiliza o paradigma declarativo, onde o foco está em descrever as regras e condições do problema, deixando a execução a cargo do sistema. Assim, enquanto o Java mostra como resolver, o Prolog descreve o que deve ser feito.

Dessa forma, ambos alcançam o mesmo resultado, porém com abordagens diferentes: uma mais detalhada e controlada, e outra mais abstrata e baseada em lógica.