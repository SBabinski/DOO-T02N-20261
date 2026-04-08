# **Análise Comparativa entre os Paradigmas de Programação Imperativo e Declarativo**

## **1. Introdução**

Os paradigmas de programação representam diferentes formas de pensar e estruturar a solução de problemas computacionais. Entre os principais modelos estudados, destacam-se o paradigma imperativo e o paradigma declarativo, que diferem fundamentalmente na maneira como instruem o computador a executar tarefas.

Enquanto o paradigma imperativo enfatiza a descrição detalhada dos passos necessários para alcançar um resultado, o paradigma declarativo foca na definição do problema e das regras que o governam, deixando a execução a cargo do sistema.



## **2. O Paradigma Imperativo**

O paradigma imperativo baseia-se na ideia de **sequência de comandos** e **mudança de estado**. Nesse modelo, o programador descreve explicitamente cada etapa do processamento.

Suas principais características incluem:

* **Controle explícito de fluxo:** uso de estruturas como if, for e while.
* **Mutabilidade de variáveis:** valores podem ser alterados ao longo da execução.
* **Execução passo a passo:** o programa segue uma sequência bem definida de instruções.

A linguagem **Java** é um exemplo clássico desse paradigma, pois exige que o desenvolvedor detalhe como cada operação deve ser realizada.


## **3. O Paradigma Declarativo**

No paradigma declarativo, o foco está em descrever **o que deve ser resolvido**, e não como resolver. O programador define regras, relações e restrições, e o sistema encontra a solução.

Principais características:

* **Alto nível de abstração**
* **Menor controle explícito do fluxo**
* **Imutabilidade (em muitos casos)**
* **Base lógica ou matemática**

A linguagem **Prolog** é um dos principais exemplos, sendo amplamente utilizada em aplicações de inteligência artificial e sistemas baseados em lógica.


## **4. Comparação Prática: Cálculo do Fatorial**

Para ilustrar a diferença entre os paradigmas, analisamos o cálculo do fatorial em Java (imperativo) e Prolog (declarativo).

### **4.1 Implementação em Java (Imperativo)**

```java
public class Fatorial {

    public static int calcular(int n) {
        int resultado = 1;

        for (int i = 1; i <= n; i++) {
            resultado = resultado * i;
        }

        return resultado;
    }
}
```

**Análise:**

Neste exemplo, o algoritmo segue uma abordagem passo a passo. Inicialmente, uma variável resultado é criada e modificada a cada iteração do laço for.

O programador controla totalmente o fluxo de execução:

1. Inicializa a variável
2. Define o laço
3. Atualiza o valor a cada repetição
4. Retorna o resultado final

Esse modelo evidencia a **mutabilidade de estado** e o controle explícito da execução.

### **4.2 Implementação em Prolog (Declarativo)**

```prolog
fatorial(0, 1).

fatorial(N, Resultado) :-
    N > 0,
    N1 is N - 1,
    fatorial(N1, SubResultado),
    Resultado is N * SubResultado.
```

**Análise:**

No Prolog, não há laços ou atribuições tradicionais. Em vez disso, o problema é descrito por meio de:

* **Um fato base:** define que o fatorial de 0 é 1
* **Uma regra recursiva:** define a relação matemática do fatorial

O Prolog resolve o problema utilizando **recursão**, **unificação** e **backtracking**, buscando satisfazer as condições declaradas.

O programador não especifica a sequência de execução — apenas define as relações lógicas.


## **5. Considerações Finais**

A comparação entre os paradigmas evidencia diferenças profundas na forma de programar.

O paradigma imperativo, como visto em Java, oferece maior controle sobre a execução e é amplamente utilizado em aplicações gerais, especialmente quando desempenho e controle são essenciais.

Por outro lado, o paradigma declarativo, representado pelo Prolog, proporciona maior abstração e expressividade, sendo mais adequado para problemas baseados em lógica, como inteligência artificial e sistemas especialistas.

Assim, a escolha do paradigma depende do tipo de problema e do nível de controle ou abstração desejado pelo desenvolvedor.
